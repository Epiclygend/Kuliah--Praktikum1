package KaryawanDB;

public class KaryawanDB {
    public static void main(String[] args) {
        final Karyawan newKaryawan = KaryawanCLI.create().karyawan;

        newKaryawan.print();
    }
}
