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
public class TokoBangunan {
    public static String konsultasiBahanBangunan(float luasBangunan, BahanBangunan ...bahanBangunan) {
        float totalHarga = 0;
        float keramikDibutuhkan = 0;
        float boxKeramikDibutuhkan = 0;
        
        for (BahanBangunan bahan : bahanBangunan) {
            if (bahan instanceof Keramik) {
                keramikDibutuhkan += luasBangunan / ((Keramik) bahan).getLuas();
                boxKeramikDibutuhkan += keramikDibutuhkan / ((Keramik) bahan).isiPerBox;
                totalHarga += keramikDibutuhkan * bahan.getHarga();
            } else
                totalHarga += bahan.getHarga();
        }
        
        return  "luas bangunan: " + luasBangunan + " m^2\n"
                + "keramik dibutuhkan: " + keramikDibutuhkan + "\n"
                + "box keramik dibutuhkan: " + boxKeramikDibutuhkan + "\n"
                + "total harga: Rp" + totalHarga + "\n";
    }
}
