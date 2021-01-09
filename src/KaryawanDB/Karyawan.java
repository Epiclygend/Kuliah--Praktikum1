package KaryawanDB;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import Utils.TextFormatter;

public class Karyawan implements HasId {
    public static enum Golongan {
        A, B, C, D;
    }

    public String kode = UUID.randomUUID().toString();
    public String nama = "-";
    public String alamat = "-";
    private LocalDate tanggalLahir = LocalDate.MIN;
    public Golongan golongan = Golongan.A;
    public boolean statusMenikah = false;
    public byte jumlahAnak = 0;

    public GajiKaryawanCalculator gajiCalculator = new GajiKaryawanCalculator(this);
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
        TextFormatter.drawSeparator();
        System.out.println("DATA KARYAWAN");
        TextFormatter.drawSeparator();
        System.out.println("Kode Karyawan\t= " + kode);
        System.out.println("Nama Karyawan\t= " + nama);
        System.out.println("Alamat\t\t= " + alamat);
        System.out.println("Tanggal Lahir\t= " + getTanggalLahir());
        System.out.println("Golongan Darah\t= " + golongan);
        System.out.println("Status Menikah\t= " + getStatusMenikah());
        System.out.println("Jumlah Anak\t= " + jumlahAnak);
        TextFormatter.drawSeparator();
        System.out.println("Gaji Pokok\\tt= Rp " + gajiCalculator.getGajiPokok());
        System.out.println("Tunjangan Istri/Suami\t= Rp " + gajiCalculator.getTunjanganSuamiIstri());
        System.out.println("Tunjangan Pegawai\t= Rp " + gajiCalculator.getTunjanganPegawai());
        System.out.println("Tunjangan Anak\t\t= Rp " + gajiCalculator.getTunjanganAnak());
        TextFormatter.drawSeparator();
        System.out.println("Gaji Kotor\t= Rp " + gajiCalculator.getGajiKotor());
        System.out.println("Potongan\t= Rp " + gajiCalculator.getPotongan());
        TextFormatter.drawSeparator();
        System.out.println("Gaji Bersih\t= Rp " + gajiCalculator.getGajiBersih());
    }
}
