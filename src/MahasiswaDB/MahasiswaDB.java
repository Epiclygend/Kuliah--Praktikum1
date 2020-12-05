package MahasiswaDB;

/**
 * MahasiswaDB
 */
public class MahasiswaDB {
    final Collection<Mahasiswa> mahasiswaCollection = new Collection<Mahasiswa>();

    public static void main(String[] args) throws MahasiswaDB.Command.Exit {
        try {
            while (true) {
                MahasiswaDB.showMenu();

                switch (Utils.inputInteger("Pilih menu: ")) {
                    case 0:
                        throw new Command.Exit();

                    default:
                        System.out.println("Invalid Command! Please input available menu!");
                        break;
                }
            }
        } catch (Command.Exit e) { System.err.println(e.getMessage()); }
    }

    public static void showMenu() {
        System.out.println("Silahkan pilih menu berikut: (Mohon masukkan angka saja)");
        System.out.println("1. Tambahkan Mahasiswa");
        System.out.println("2. Hapus Mahasiswa");
        System.out.println("3. Cari Mahasiswa");
        System.out.println("4. Tampilkan Semua Data Mahasiswa");
        System.out.println("0. Exit");
    }

    public static class Command {
        public static Mahasiswa createMahasiswa() {
            final Mahasiswa data = new Mahasiswa();

            data.nim = Utils.inputString("Input NIM");
            data.nama = Utils.inputString("Input Nama");
            do
                data.gender = Utils.inputInteger("Input Gender (0/1)");
            while (Mahasiswa.validateGender(data.gender));

            return data;
        }

        public static void deleteMahasiswa() {

        }

        public static void findByGender() {

        }

        public static void findByNim() {

        }

        public static void showData() {

        }

        public static class Exit extends Exception {
            private static final long serialVersionUID = 7769507698565750826L;

            public Exit() {
                super("Program Exited!");
            }
        }
    }
}