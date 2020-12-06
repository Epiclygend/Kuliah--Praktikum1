package MahasiswaDB;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * MahasiswaDB
 */
public class MahasiswaDB {
    final static Collection<Mahasiswa> MAHASISWA_COLLECTION = new Collection<Mahasiswa>();
    final static List<Command> MENU = Arrays.asList(
            new Command("Exit", MahasiswaDB::exit), 
            new Command("Tambah Mahasiswa", MahasiswaDB::createMahasiswa),
            new Command("Hapus Mahasiswa", MahasiswaDB::deleteMahasiswa), 
            new Command("Cari Mahasiswa (by Gender)", MahasiswaDB::findByGender), 
            new Command("Cari Mahasiswa (by NIM)", MahasiswaDB::findByNim), 
            new Command("Tampil Mahasiswa", MahasiswaDB::showData)
        );

    public static void main(String[] args) {
        MahasiswaDB.toMainMenu();
    }

    public static void toMainMenu() {
        MahasiswaDB.showMenu();

        try {
            final int selection = Utils.inputInteger("Pilih menu: ");

            MahasiswaDB.MENU.get(selection).action.accept(MahasiswaDB::toMainMenu, MahasiswaDB::exitProgram);
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
        for (int i = 0; i < MENU.size(); i++)
            System.out.println(i + ". " + MENU.get(i).title);
    }

    public static void createMahasiswa(Runnable next, Runnable exit) {
        final Mahasiswa newMahasiswa = MahasiswaCLI.createMahasiswa().mahasiswaInstance;

        MAHASISWA_COLLECTION.add(newMahasiswa);
        System.out.println("Berhasil ditambahkan!");
        newMahasiswa.print();

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
    
    static public class Command {
        final String title;
        final BiConsumer<Runnable, Runnable> action;
        public Command(String title, BiConsumer<Runnable, Runnable> action) {
            this.title = title;
            this.action = action;
        }
    }
}
