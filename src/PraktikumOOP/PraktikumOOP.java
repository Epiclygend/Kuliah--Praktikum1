/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PraktikumOOP;

/**
 *
 * @author ANH
 */
public class PraktikumOOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Keramik keramik_A = new Keramik(.3f, .3f, 54000, 10);
        Keramik keramik_B = new Keramik(.4f, .4f, 65000, 5);
        Keramik keramik_C = new Keramik(.4f, .3f, 60000, 8);
        
        System.out.println("====== INFO KERAMIK ======");
        System.out.println(keramik_A);
        System.out.println(keramik_B);
        System.out.println(keramik_C);
        System.out.println();
        
        System.out.println("====== HASIL KONSULTASI ======");
        System.out.println("Keramik A");
        System.out.println(TokoBangunan.konsultasiBahanBangunan(100, keramik_A));
        System.out.println("Keramik B");
        System.out.println(TokoBangunan.konsultasiBahanBangunan(100, keramik_B));
        System.out.println("Keramik C");
        System.out.println(TokoBangunan.konsultasiBahanBangunan(100, keramik_C));
        System.out.println();
    }
    
}
