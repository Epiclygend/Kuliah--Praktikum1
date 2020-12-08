package MahasiswaDB;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * MahasiswaDB
 */
public class MahasiswaDB {
    final static Collection<Mahasiswa> MAHASISWA_COLLECTION = new Collection<Mahasiswa>();
    final static List<Command> MENU = Arrays.asList(
            new Command("Exit", MahasiswaDBCommand::exit),
            new Command("Tambah Mahasiswa", MahasiswaDBCommand::createMahasiswa),
            new Command("Hapus Mahasiswa", MahasiswaDBCommand::deleteMahasiswa),
            new Command("Cari Mahasiswa (by NIM)", MahasiswaDBCommand::findByNim),
            new Command("Cari Mahasiswa (by Gender)", MahasiswaDBCommand::findByGender),
            new Command("Tampil Mahasiswa", MahasiswaDBCommand::showData)
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
        Utils.drawSeparator();
        System.out.println("Silahkan pilih menu berikut: (Mohon masukkan angka saja)");
        for (int i = 0; i < MENU.size(); i++)
            System.out.println(i + ". " + MENU.get(i).title);
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
                final List<Mahasiswa> searchResult = findMahasiswa.searchByNim(nimSearch);
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

        public static void findByGender(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("CARI MAHASISWA DENGAN GENDER");
            final int genderSearch = Utils.inputInteger("Masukkan kode gender untuk dicari (0/1)\t= ");

            try {
                final List<Mahasiswa> searchResult = findMahasiswa.searchByGender(genderSearch);
                searchResult.forEach(Mahasiswa::print);
                printTotalListed(searchResult.size());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            next.run();
        }

        public static void findByNim(Runnable next, Runnable exit) {
            Utils.drawSeparator();
            System.out.println("CARI MAHASISWA DENGAN NIM");
            final String nimSearch = Utils.inputString("Masukkan NIM untuk dicari\t= ");

            try {
                final List<Mahasiswa> searchResult = findMahasiswa.searchByNim(nimSearch);
                searchResult.forEach(Mahasiswa::print);
                printTotalListed(searchResult.size());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            next.run();
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

        private static void printTotalListed(int count) {
            Utils.drawSeparator();
            System.out.println(Utils.padLeft("Jumlah Mahasiswa: " + count));
        }
    }

    public static class findMahasiswa {
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
