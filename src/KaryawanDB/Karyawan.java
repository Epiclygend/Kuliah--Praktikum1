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
        System.out.println("Nama\t= " + nama);
        System.out.println("alamat\t= " + alamat);
        System.out.println("Tanggal Lahir\t= " + tanggalLahir);
        System.out.println("Golongan Darah\t= " + golonganDarah);
        System.out.println("Status Menikah\t= " + getStatusMenikah());
        System.out.println("Jumlah Anaka\t= " + jumlahAnak);
    }
}
