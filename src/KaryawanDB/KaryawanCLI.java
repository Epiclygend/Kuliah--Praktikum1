package KaryawanDB;

import KaryawanDB.Karyawan.GolonganDarah;
import Utils.Input;

public class KaryawanCLI {
    Karyawan karyawan;

    public KaryawanCLI(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public static KaryawanCLI create() {
        return new KaryawanCLI(new Karyawan())
            .setNama()
            .setAlamat()
            .setTanggalLahir()
            .setGolonganDarah()
            .setStatusMenikah()
            .setJumlahAnak();
    }

    public KaryawanCLI setNama() {
        this.karyawan.nama = Input.string("Masukkan Nama\t= ");
        return this;
    }

    public KaryawanCLI setAlamat () {
        this.karyawan.alamat = Input.string("Masukkan Alamat\t= ");
        return this;
    };

    public KaryawanCLI setTanggalLahir () {
        this.karyawan.tanggalLahir = Input.string("Masukkan Tanggal Lahir = ");
        return this;
    };
    
    public KaryawanCLI setGolonganDarah () {
        while (true) {
            String input = Input.string("Masukkan Golongan Darah = ");

            try {
                this.karyawan.golonganDarah = GolonganDarah.valueOf(input);
                break;
            } catch (Exception e) {
                continue;
            }
        }

        return this;
    };

    public KaryawanCLI setStatusMenikah () {
        this.karyawan.statusMenikah = Input.integer("Masukkan Status Menikah (0: Belum; 1: Sudah) = ") > 0;
        return this;
    };

    public KaryawanCLI setJumlahAnak () {
        this.karyawan.jumlahAnak = Input.integer("Masukkan jumlah anak\t= ").byteValue();
        return this;
    };
}
