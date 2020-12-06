package MahasiswaDB;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * MahasiswaDB
 */
public class MahasiswaDB {
    final static Collection<Mahasiswa> mahasiswaCollection = new Collection<Mahasiswa>();
    final static List<BiConsumer<Runnable, Runnable>> menus = Arrays.asList(
        Command::exit,
        Command::createMahasiswa,
        Command::deleteMahasiswa,
        Command::findByGender,
        Command::findByNim,
        Command::showData
    );

    public static void main(String[] args){
        MahasiswaDB.toMainMenu();
    }

    public static void toMainMenu() {
        MahasiswaDB.showMenu();

        try {
            MahasiswaDB.menus.get(Utils.inputInteger("Pilih menu: ")).accept(MahasiswaDB::toMainMenu, MahasiswaDB::exitProgram);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Command! Please input available menu!");
            MahasiswaDB.toMainMenu();
        }
    }

    public static void exitProgram() {
        System.out.println("Program Exited!");
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
        public static void createMahasiswa(Runnable next, Runnable exit) {
            final Mahasiswa data = new Mahasiswa();

            data.nim = Utils.inputString("Input NIM");
            data.nama = Utils.inputString("Input Nama");
            do
                data.gender = Utils.inputInteger("Input Gender (0/1)");
            while (Mahasiswa.validateGender(data.gender));

            MahasiswaDB.mahasiswaCollection.add(data);

            next.run();
        }

        public static void deleteMahasiswa(Runnable next, Runnable exit) {
            next.run();
        }

        public static void findByGender(Runnable next, Runnable exit) {
            next.run();
        }

        public static void findByNim(Runnable next, Runnable exit) {
            next.run();
        }

        public static void showData(Runnable next, Runnable exit) {
            next.run();
        }

        public static void exit(Runnable next, Runnable exit) {
            exit.run();
        }
    }
}