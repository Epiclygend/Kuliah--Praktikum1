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
    final public static Function<String, String> KARYAWAN_NOTFOUND_MSG = (id) -> "Apakah anda yakin akan menghapus data ini ?" + id;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        TextFormatter.drawSeparator();
        System.out.println("Silahkan Pilih Menu Berikut: (Mohon masukkan angka saja)");
        MENU.showMenu();
        
        final Command selection = MENU.getMenuSelection();

        TextFormatter.drawSeparator();
        System.out.println(selection.title.toUpperCase());
        selection.action.accept(KaryawanDB::mainMenu, KaryawanDB::exit);
    }

    public static void addKaryawan(Runnable next, Runnable exit) {
        karyawanCollection.add(KaryawanCLI.create().karyawan);

        next.run();
    }

    public static void deleteKaryawan(Runnable next, Runnable exit) {
        final String id = Input.string("Masukkan Kode Karyawan untuk dihapus\t= ");
        final Optional<Karyawan> existKaryawan = karyawanCollection.find(id);

        if (existKaryawan.isPresent()) {
            if (Input.confirm("Apakah anda yakin akan menghapus data ini ?")) {
                karyawanCollection.delete(id);
            }    
        } else System.out.println(KARYAWAN_NOTFOUND_MSG.apply(id));

        next.run();
    }

    public static void findKaryawan(Runnable next, Runnable exit) {
        final String id = Input.string("Masukkan Kode Karyawan untuk dicari\t= ");
        final Optional<Karyawan> existKaryawan = karyawanCollection.find(id);

        if (existKaryawan.isPresent()) {
            TextFormatter.drawSeparator();
            existKaryawan.get().print();
            TextFormatter.drawSeparator();
        } else System.out.println(KARYAWAN_NOTFOUND_MSG.apply(id));

        next.run();
    }

    public static void viewKaryawan(Runnable next, Runnable exit) {
        karyawanCollection.printAll();
        next.run();
    }

    public static void exit() {
        System.exit(0);
    }

    public static void exit(Runnable next, Runnable exit) {
        System.exit(0);
    }
}
