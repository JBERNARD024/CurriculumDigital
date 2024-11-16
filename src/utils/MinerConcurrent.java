/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author joaob
 */

//Nesta classe está representada o mineiro paralelo
public class MinerConcurrent {

    public static class ThrMiner extends Thread {

        AtomicInteger nonce;
        AtomicInteger ticket;
        int dificulty;
        String data;
        String zeros;
        int MAX_NONCE = (int) 1E9;
        AtomicBoolean found;

        public ThrMiner(AtomicInteger nonce, AtomicInteger ticket, int dificulty, String data, AtomicBoolean found) {
            this.nonce = nonce;
            this.ticket = ticket;
            this.dificulty = dificulty;
            this.data = data;
            this.zeros = String.format("%0" + dificulty + "d", 0);
            this.found = found;
        }

        public void run() {
            while (nonce.get() < MAX_NONCE && !found.get()) {
                int temp_nonce = ticket.getAndIncrement();
                String hash = Hash.getHash(temp_nonce + data);
                if (hash.endsWith(zeros)) {
                    nonce.set(temp_nonce);
                    found.set(true); 
                }
            }
        }
    }

    //Função que devolve o valor do Nonce, com recurso a todos os processadores do sistema onde está a correr
    public static int getNonce(String data, int dificulty) throws InterruptedException{
        AtomicInteger temp_nonce = new AtomicInteger(0);
        AtomicInteger nonce = new AtomicInteger(0);
        AtomicBoolean found = new AtomicBoolean(false);
        int cores = Runtime.getRuntime().availableProcessors();
        ThrMiner[] threads = new ThrMiner[cores];

        // Iniciar as threads
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThrMiner(temp_nonce, nonce, dificulty, data, found);
            threads[i].start();
        }

        // Esperar todas as threads terminarem
        for (ThrMiner thread : threads) {
            thread.join();
        }

        //Devolve o valor do nonce calculado por todas as threads
        return nonce.get();
    }
}
