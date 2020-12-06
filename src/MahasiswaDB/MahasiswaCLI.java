package MahasiswaDB;

import MahasiswaDB.Mahasiswa.InvalidGenderChoice;

public class MahasiswaCLI {
    final public Mahasiswa mahasiswaInstance;

    public MahasiswaCLI(Mahasiswa mahasiswa) {
        this.mahasiswaInstance = mahasiswa;
    }

    public static MahasiswaCLI createMahasiswa() {
        final MahasiswaCLI instance = new MahasiswaCLI(new Mahasiswa());

        instance.setNimMahasiswa();
        instance.setNamaMahasiswa();
        instance.setGenderMahasiswa();
        instance.setTanggalLahirMahasiswa();

        return instance;
    }

    public void setNimMahasiswa() {
        mahasiswaInstance.nim = Utils.inputString("Masukkan NIM\t= ");
    }

    public void setNamaMahasiswa() {
        mahasiswaInstance.nama = Utils.inputString("Masukkan Nama\t= ");
    }

    public void setGenderMahasiswa() {
        mahasiswaInstance.gender = Utils.inputInteger("Masukkan Gender (0: Pria; 1: Wanita)= ");

        try {
            Mahasiswa.validateGender(mahasiswaInstance.gender);
        } catch (InvalidGenderChoice e) {
            System.err.println(e.getMessage());
            this.setGenderMahasiswa();
        }
    }

    public void setTanggalLahirMahasiswa() {
        System.out.println("Masukkan Tanggal Lahir:");

        final int DD = Utils.inputInteger("DD\t= ");
        final int MM = Utils.inputInteger("MM\t= ");
        final int YYYY = Utils.inputInteger("YYYY\t= ");

        mahasiswaInstance.tglLahir = Mahasiswa.parseTglLahir(DD, MM, YYYY);

        final String confirmMessage = "Apakah anda yakin tanggal ini benar? " + mahasiswaInstance.getFormattedTglLahir();
        if (!Utils.inputConfirm(confirmMessage))
            this.setTanggalLahirMahasiswa();
    }
}
