//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
//::                                                                         ::
//::     Antonio Manuel Rodrigues Manso                                      ::
//::                                                                         ::
//::     I N S T I T U T O    P O L I T E C N I C O   D E   T O M A R        ::
//::     Escola Superior de Tecnologia de Tomar                              ::
//::     e-mail: manso@ipt.pt                                                ::
//::     url   : http://orion.ipt.pt/~manso                                  ::
//::                                                                         ::
//::     This software was build with the purpose of investigate and         ::
//::     learning.                                                           ::
//::                                                                         ::
//::                                                               (c)2023   ::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//////////////////////////////////////////////////////////////////////////////
package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created on 19/09/2023, 21:22:39
 *
 * @author manso - computer
 */
public class Converter {

    /**
     *
     * @param data
     * @return
     */
    public static String byteArrayToHex(byte[] data) {
        // return new BigInteger(data).toString(16).toUpperCase();
        final StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }

    public static byte[] hexToByteArray(String hex) {
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(hex.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
        //return new BigInteger(data, 16).toByteArray();
    }

    public static byte[] objectToByteArray(Object data) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(data);
            return bos.toByteArray();
        }
    }

    public static Object byteArrayToObject(byte[] bytes) throws Exception {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    public static String objectToHex(Object obj) {
        try {
            return byteArrayToHex(objectToByteArray(obj));
        } catch (IOException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR objectToHex";
        }
    }

    public static Object hexToObject(String hex) {
        try {
            return byteArrayToObject(hexToByteArray(hex));
        } catch (Exception ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR hexToObject";
        }
    }
    
     // Transforma um objeto em uma string compactada com GZIP (em Base64)
    public static String objectToGzipString(Object obj) throws IOException {
        // Passo 1: Serializar o objeto em bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj); // Serializa o objeto
        }
        byte[] serializedData = bos.toByteArray();

        // Passo 2: Compactar os bytes usando GZIP
        ByteArrayOutputStream compressedBos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOut = new GZIPOutputStream(compressedBos)) {
            gzipOut.write(serializedData); // Compacta os dados serializados
        }
        byte[] compressedData = compressedBos.toByteArray();

        // Passo 3: Converter os bytes compactados em uma string Base64
        return Base64.getEncoder().encodeToString(compressedData);
    }

    // Transforma uma string compactada com GZIP (em Base64) de volta em um objeto
    public static Object gzipStringToObject(String gzipString) throws IOException, ClassNotFoundException {
        // Passo 1: Converter a string Base64 de volta para bytes compactados
        byte[] compressedData = Base64.getDecoder().decode(gzipString);

        // Passo 2: Descompactar os dados GZIP
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream decompressedBos = new ByteArrayOutputStream();
        try (GZIPInputStream gzipIn = new GZIPInputStream(bis)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipIn.read(buffer)) > 0) {
                decompressedBos.write(buffer, 0, len); // Descompacta os dados
            }
        }
        byte[] decompressedData = decompressedBos.toByteArray();

        // Passo 3: Desserializar os bytes descompactados de volta para o objeto
        ByteArrayInputStream deserializationBis = new ByteArrayInputStream(decompressedData);
        try (ObjectInputStream ois = new ObjectInputStream(deserializationBis)) {
            return ois.readObject(); // Lê o objeto deserializado
        }
    }
}
