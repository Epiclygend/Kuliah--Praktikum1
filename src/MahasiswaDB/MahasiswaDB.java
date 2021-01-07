package MahasiswaDB;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import Utils.Command;
import Utils.Menu;
import Utils.Utils;

/**
 * MahasiswaDB
 */
public class MahasiswaDB {
    final static Collection<Mahasiswa> MAHASISWA_COLLECTION = new Collection<Mahasiswa>();
    final static Menu MAIN_MENU = new Menu(
        new Command("Exit", MahasiswaDBCommand::exit),
        new Command("Tambah Mahasiswa", MahasiswaDBCommand::createMahasiswa),
        new Command("Hapus Mahasiswa", MahasiswaDBCommand::deleteMahasiswa),
        new Command("Cari Mahasiswa", MahasiswaDBCommand::findMahasiswa),
        new Command("Tampil Mahasiswa", MahasiswaDBCommand::showData)
    );
    final static Menu FIND_MAHASISWA_MENU = new Menu(
        new Command("Back to main menu", FindMahasiswa::backToMainMenu),
        new Command("by NIM", FindMahasiswa::byNim),
        new Command("by Gender", FindMahasiswa::byGender)
    );

    public static void main(String[] args) {
        MahasiswaDB.toMainMenu();
    }

    public static void toMainMenu() {
        Utils.drawSeparator();
        System.out.println("Silahkan pilih menu berikut: (Mohon masukkan angka saja)");
        MAIN_MENU.showMenu();

        MAIN_MENU.getMenuSelection().action.accept(MahasiswaDB::toMainMenu, MahasiswaDB::exitProgram);
    }

    public static void exitProgram() {
        System.out.println("Program Exited!");
    }

    private static void printTotalListed(int count) {
        Utils.drawSeparator();
        System.out.println(Utils.padLeft("Jumlah Mahasiswa: " + count));
    }

    static public class MahasiswaDBCommand {
        public static void createMahasiswa(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("TAMBAH DATA MAHASISWA");
            Utils.drawSeparator();
            final Mahasiswa newMahasiswa = MahasiswaCLI.createMahasiswa().mahasiswaInstance;

            MAHASISWA_COLLECTION.add(newMahasiswa);
            System.out.println("Berhasil ditambahkan!");
            newMahasiswa.print();

            next.run();
        }

        public static void deleteMahasiswa(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("HAPUS DATA MAHASISWA");
            final String nimSearch = Utils.inputString("Masukkan NIM untuk dihapus\t= ");

            try {
                final List<Mahasiswa> searchResult = FindMahasiswa.searchByNim(nimSearch);
                searchResult.forEach(mahasiswa -> {
                    mahasiswa.print();

                    if (Utils.inputConfirm("Apakah kamu akan menghapus data ini?")) {
                        MAHASISWA_COLLECTION.deleteById(mahasiswa.getId());
                        Utils.drawSeparator();
                        System.out.println(mahasiswa.getId() + " BERHASIL DIHAPUS!");
                        Utils.drawSeparator();
                    }
                });
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            next.run();
        }

        public static void findMahasiswa(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("CARI MAHASISWA");
            Utils.drawSeparator();
            FIND_MAHASISWA_MENU.showMenu();
            
            FIND_MAHASISWA_MENU.getMenuSelection().action.accept(MahasiswaDB::toMainMenu, MahasiswaDB::exitProgram);
        }

        public static void showData(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("DATA MAHASISWA");
            MAHASISWA_COLLECTION.printAll();
            printTotalListed(MAHASISWA_COLLECTION.length());
            next.run();
        }

        public static void exit(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            exit.run();
        }
    }

    public static class FindMahasiswa {
        public static void backToMainMenu(Runnable next, Runnable exit) {
            next.run();
        }

        public static void byGender(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("CARI MAHASISWA DENGAN GENDER");
            final int genderSearch = Utils.inputInteger("Masukkan kode gender untuk dicari (0/1)\t= ");
            Utils.drawSeparator();

            try {
                final List<Mahasiswa> searchResult = FindMahasiswa.searchByGender(genderSearch);
                searchResult.forEach(Mahasiswa::print);
                printTotalListed(searchResult.size());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            next.run();
        }

        public static void byNim(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("CARI MAHASISWA DENGAN NIM");
            final String nimSearch = Utils.inputString("Masukkan NIM untuk dicari\t= ");
            Utils.drawSeparator();

            try {
                final List<Mahasiswa> searchResult = FindMahasiswa.searchByNim(nimSearch);
                searchResult.forEach(Mahasiswa::print);
                printTotalListed(searchResult.size());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            next.run();
        }

        public static List<Mahasiswa> searchByGender(int genderSearch) throws MahasiswaNotFound {
            Predicate<? super Mahasiswa> byGenderFilter = mahasiswa -> mahasiswa.gender == genderSearch;
            return search(byGenderFilter);
        }

        public static List<Mahasiswa> searchByNim(String nimSearch) throws MahasiswaNotFound {
            Predicate<? super Mahasiswa> byNimFilter = mahasiswa -> mahasiswa.nim.toUpperCase()
                    .equals(nimSearch.toUpperCase());
            return search(byNimFilter);
        }

        private static List<Mahasiswa> search(Predicate<? super Mahasiswa> filter) throws MahasiswaNotFound {
            final List<Mahasiswa> result = Arrays.asList(MAHASISWA_COLLECTION.filter(filter).toArray(Mahasiswa[]::new));

            if (result.size() > 0) {
                Utils.drawSeparator();
                System.out.println("DATA MAHASISWA DITEMUKAN!");
                return result;
            } else
                throw new MahasiswaNotFound();
        }

        public static class MahasiswaNotFound extends Exception {
            private static final long serialVersionUID = 1L;

            public MahasiswaNotFound() {
                super("Mohon maaf, Data yang dicari tidak ditemukan!");
            }
        }
    }
}
