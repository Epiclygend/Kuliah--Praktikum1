package KaryawanDB;

public class KaryawanCollection extends Collection<Karyawan> {
    public void printAll() {
        getCollection().forEach(karyawan -> karyawan.print());
    }
}
