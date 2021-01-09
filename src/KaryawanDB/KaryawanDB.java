package KaryawanDB;

import java.util.Optional;
import java.util.function.Function;
import Utils.Command;
import Utils.Input;
import Utils.Menu;
import Utils.TextFormatter;

public class KaryawanDB {
    final public static KaryawanCollection karyawanCollection = new KaryawanCollection();
    final public static Menu MENU = new Menu(
        new Command("Tambah Data", KaryawanDB::addKaryawan),
        new Command("Hapus Data", KaryawanDB::deleteKaryawan),
        new Command("Cari Data", KaryawanDB::findKaryawan),
        new Command("Lihat Data", KaryawanDB::viewKaryawan),
        new Command("Exit", KaryawanDB::exit)
    );
    final public static String SELECT_MENU_MSG = "Silahkan Pilih Menu Berikut: (Mohon masukkan angka saja)";
    final public static Function<String, String> KARYAWAN_NOTFOUND_MSG = (id) -> "Karyawan dengan kode karyawan '" + id + "' tidak ditemukan!";

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        TextFormatter.drawSeparator();
        System.out.println(SELECT_MENU_MSG);
        System.out.println();
        MENU.showMenu();
        System.out.println();
        
        final Command selection = MENU.getMenuSelection();

        TextFormatter.drawSeparator();
        System.out.println(selection.title.toUpperCase());
        TextFormatter.drawSeparator();
        selection.action.accept(KaryawanDB::mainMenu, KaryawanDB::exit);
    }

    public static void mainMenu(Runnable next, Runnable exit) {
        mainMenu();
    }

    public static void addKaryawan(Runnable next, Runnable exit) {
        while (true) {
            try {
                karyawanCollection.add(KaryawanCLI.create().karyawan);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                System.out.println("Silahkan coba lagi...");
            }
        }

        construcNextAction(next, exit, new Command("Tambah Data Lagi", KaryawanDB::addKaryawan));
    }

    public static void deleteKaryawan(Runnable next, Runnable exit) {
        final String id = Input.string("Masukkan Kode Karyawan untuk dihapus\t= ");
        final Optional<Karyawan> existKaryawan = karyawanCollection.find(id);

        if (existKaryawan.isPresent()) {
            existKaryawan.get().print();
            if (Input.confirm("Apakah anda yakin akan menghapus data ini ?")) {
                karyawanCollection.delete(id);
            }    
        } else System.out.println(KARYAWAN_NOTFOUND_MSG.apply(id));

        construcNextAction(next, exit, new Command("Hapus Data Lagi", KaryawanDB::deleteKaryawan));
    }

    public static void findKaryawan(Runnable next, Runnable exit) {
        final String id = Input.string("Masukkan Kode Karyawan untuk dicari\t= ");
        final Optional<Karyawan> existKaryawan = karyawanCollection.find(id);

        if (existKaryawan.isPresent()) {
            TextFormatter.drawSeparator();
            existKaryawan.get().print();
            TextFormatter.drawSeparator();
        } else System.out.println(KARYAWAN_NOTFOUND_MSG.apply(id));

        construcNextAction(next, exit, new Command("Cari Data Lagi", KaryawanDB::findKaryawan));
    }

    public static void viewKaryawan(Runnable next, Runnable exit) {
        karyawanCollection.printAll();

        construcNextAction(next, exit);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void exit(Runnable next, Runnable exit) {
        System.exit(0);
    }

    private static void construcNextAction(Runnable next, Runnable exit, Command... additionalCommands) {
        final Menu nextAction = new Menu(new Command("Kembali ke Menu Utama", KaryawanDB::mainMenu));

        for (Command command : additionalCommands) {
            nextAction.register(command);
        }

        TextFormatter.drawSeparator();
        System.out.println();
        System.out.println(SELECT_MENU_MSG);
        nextAction.showMenu();
        System.out.println();

        final Command selection = nextAction.getMenuSelection();

        TextFormatter.drawSeparator();
        System.out.println(selection.title.toUpperCase());
        TextFormatter.drawSeparator();
        selection.action.accept(next, exit);
    }
}
