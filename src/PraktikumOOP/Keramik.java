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
public final class Keramik extends BahanBangunan {
    float sisi1;
    float sisi2;
    private int hargaPerBox = 1;
    int isiPerBox = 1;
    
    public Keramik(float sisi1, float sisi2, int hargaPerBox, int isiPerBox) {
        this.sisi1 = sisi1;
        this.sisi2 = sisi2;
        this.isiPerBox = isiPerBox;
        this.hargaPerBox = hargaPerBox;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": {\n"
                + "\tsisi1: " + sisi1 + ",\n"
                + "\tsisi2: " + sisi2 + ",\n"
                + "\tluas: " + getLuas() + ",\n"
                + "\tharga: " + getHarga() + ",\n"
                + "\tisiPerBox: " + isiPerBox + "\n"
                + "}";
    }

    /**
     * @return harga
     */
    public float getHarga() {
        return hargaPerBox / this.isiPerBox;
    }

    /**
     * Otomatis memasukkan properti harga sesuai harga per biji
     * 
     * @param harga
     */
    public void setHarga(int harga) {
        this.hargaPerBox = harga;
        this.isiPerBox = 1;
    }

    /**
     * @return hargaPerBox
     */
    public float getHargaPerBox() {
        return hargaPerBox;
    }

    /**
     * Otomatis memasukkan properti harga sesuai harga per biji
     * 
     * @param hargaPerBox
     */
    public void setHargaPerBox(int hargaPerBox) {
        this.hargaPerBox = hargaPerBox;
    }

    /**
     * @return luas
     */
    public float getLuas() {
        return sisi1 * sisi2;
    }
}
