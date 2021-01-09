package KaryawanDB;

public class Karyawan implements HasId {
    public enum GolonganDarah {
        A, B, AB, O;
    }

    public String kode;
    public String nama;
    public String alamat;
    public String tanggalLahir;
    public GolonganDarah golonganDarah;
    public boolean statusMenikah;
    public byte jumlahAnak;

    @Override
    public String getId() {
        return this.kode;
    }

    public String getStatusMenikah() {
        return statusMenikah ? "Sudah Menikah" : "Belum Menikah";
    }

    public void print() {
        System.out.println(golonganDarah.toString());        
    }
}
