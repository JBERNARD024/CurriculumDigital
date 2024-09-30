/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.Icon;

/**
 *
 * @author joaob
 *
 */

public class Recursos {
    
    /**
     * @param obj
     * @param fileName
     */
    public static void writeObject(Serializable obj, String fileName) {
        try {
            FileOutputStream fs = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fs);
            out.writeObject(obj);
            out.close();
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    public static Object readObject(String fileName) {
        try {
            FileInputStream fs = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fs);
            Object obj = in.readObject();
            in.close();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param icon
     * @return
     * @throws IOException
     */
    public static byte[] iconToByteArray(Icon icon) throws IOException {
        //tela de desenho
        BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        //desenhar a imagem
        g.setColor(Color.white);
        g.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        icon.paintIcon(null, g, 0, 0);
        //output stream em memória
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //guardar a imagem na memória
        ImageIO.write(bi, "jpg", out);
        g.dispose();
        //retornar os bytes da memoria
        return out.toByteArray();
    }

    /**
     *
     * @param chain
     * @return
     */ 
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
