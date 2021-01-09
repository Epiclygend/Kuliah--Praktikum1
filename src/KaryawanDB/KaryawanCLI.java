package KaryawanDB;

import KaryawanDB.Karyawan.Golongan;
import Utils.Input;
import Utils.Input.RangeInput;

public class KaryawanCLI {
    Karyawan karyawan;

    public KaryawanCLI(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public static KaryawanCLI create() {
        final KaryawanCLI instance = new KaryawanCLI(new Karyawan())
            .setKodeKaryawan()
            .setNama()
            .setAlamat()
            .setTanggalLahir()
            .setGolongan()
            .setStatusMenikah();

        if (instance.karyawan.statusMenikah)
            instance.setJumlahAnak();
        
        return instance;
    }

    public KaryawanCLI setKodeKaryawan() {
        this.karyawan.kode = Input.string("Masukkan Kode Karyawan\t= ").toUpperCase();
        return this;
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
        while (true) {
            try {
                System.out.println("Masukkan Tanggal Lahir");
                this.karyawan.setTanggalLahir(
                    Input.integer("\tYYYY\t= "),
                    new RangeInput(1, 12).get("\tMM\t= "),
                    new RangeInput(1, 31).get("\tDD\t= ")
                );

                System.out.println("\tApakah anda yakin tanggal ini sudah benar?");
                if (Input.confirm("\t" + karyawan.getTanggalLahir()))
                    return this;

            } catch (Exception e) {
                System.err.println("Terjadi kesalahan! Silahkan coba lagi...");
            }
        }
    };
    
    public KaryawanCLI setGolongan () {
        while (true) {
            String input = Input.string("Masukkan Golongan\t= ").toUpperCase();

            try {
                this.karyawan.golongan = Golongan.valueOf(input);
                break;
            } catch (Exception e) {
                System.err.println("Pilihan anda tidak tersedia, Silahkan coba lagi...");
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
