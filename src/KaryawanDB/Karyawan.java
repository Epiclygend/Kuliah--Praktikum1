package KaryawanDB;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Karyawan implements HasId {
    public enum GolonganDarah {
        A, B, AB, O;
    }

    public String kode = "";
    public String nama = "";
    public String alamat = "";
    private LocalDate tanggalLahir = LocalDate.MIN;
    public GolonganDarah golonganDarah = GolonganDarah.A;
    public boolean statusMenikah = false;
    public byte jumlahAnak = 0;

    private static DateTimeFormatter TANGGAL_LAHIR_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String getId() {
        return this.kode;
    }

    public String getStatusMenikah() {
        return statusMenikah ? "Sudah Menikah" : "Belum Menikah";
    }

    public void setTanggalLahir(int YYYY, int MM, int DD) {
        tanggalLahir = LocalDate.of(YYYY, MM, DD);
    }

    public String getTanggalLahir() {
        return tanggalLahir.format(TANGGAL_LAHIR_FORMATTER);
    }

    public int getAge() {
        return Period.between(tanggalLahir, LocalDate.now()).getYears();
    }

    public void print() {
        System.out.println("Nama\t\t= " + nama);
        System.out.println("Alamat\t\t= " + alamat);
        System.out.println("Tanggal Lahir\t= " + getTanggalLahir());
        System.out.println("Golongan Darah\t= " + golonganDarah);
        System.out.println("Status Menikah\t= " + getStatusMenikah());
        System.out.println("Jumlah Anaka\t= " + jumlahAnak);
    }
}
