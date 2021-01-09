package KaryawanDB;

import Utils.TextFormatter;

public class KaryawanCollection extends Collection<Karyawan> {
    public void printAll() {
        TextFormatter.drawSeparator();
        System.out.println("KODE\t\t" + "NAMA\t\t" + "GOL\t\t" + "USIA\t\t" + "STATUS NIKAH\t" + "JML. ANAK");
        TextFormatter.drawSeparator();
        getCollection().forEach(karyawan -> {
            System.out.println(
                karyawan.kode + "\t" +
                karyawan.nama + "\t" +
                karyawan.golongan + "\t" +
                karyawan.getAge() + "\t" +
                karyawan.getStatusMenikah() + "\t" +
                karyawan.jumlahAnak + "\t"
            );
        });
    }
}
