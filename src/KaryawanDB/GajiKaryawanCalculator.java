package KaryawanDB;

import java.util.HashMap;
import java.util.Map;
// import java.util.function.Function;

import KaryawanDB.Karyawan.Golongan;

public class GajiKaryawanCalculator {
    final static HashMap<Golongan, Integer> GAJI_POKOK_RULE = new HashMap<>(Map.of(
        Golongan.A, 5000000,
        Golongan.B, 6000000,
        Golongan.C, 7000000,
        Golongan.D, 8000000
    ));
    final static float TUNJANGAN_SUAMI_ISTRI_PERCENTAGE = 10/100f;
    final static float TUNJANGAN_PEGAWAI_PERCENTAGE = 15/100f;
    final static float TUNJANGAN_ANAK_PERCENTAGE = 5/100f;
    final static float POTONGAN_GAJI_PERCENTAGE = 25/1000f;

    final Karyawan karyawan;

    public GajiKaryawanCalculator(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    public int getGajiPokok() {
        return GAJI_POKOK_RULE.getOrDefault(karyawan.golongan, 0);
    }

    public int getTunjanganSuamiIstri() {
        final Float count = getGajiPokok() * TUNJANGAN_SUAMI_ISTRI_PERCENTAGE;
        return count.intValue();
    }

    public int getTunjanganPegawai() {
        if (karyawan.getAge() > 30) {
            final Float count = getGajiPokok() * TUNJANGAN_PEGAWAI_PERCENTAGE;
            return count.intValue();
        }
        return 0;
    }

    public int getTunjanganAnak() {
        final Float count = getGajiPokok() * TUNJANGAN_ANAK_PERCENTAGE * karyawan.jumlahAnak;
        return count.intValue();
    }

    public int getGajiKotor() {
        return getGajiPokok() + getTunjanganSuamiIstri() + getTunjanganPegawai() + getTunjanganAnak();
    }

    public int getPotongan() {
        final Float count = getGajiKotor() * POTONGAN_GAJI_PERCENTAGE;
        return count.intValue();
    }

    public int getGajiBersih() {
        return getGajiKotor() - getPotongan();
    }
}
