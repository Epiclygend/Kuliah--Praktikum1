package MahasiswaDB;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Mahasiswa implements Collectable {
    String nim;
    String nama;
    Date tglLahir;
    int gender = 0;

    @Override
    public String getId() {
        return this.getId();
    }

    public String getGenderString() {
        return gender == 0 ? "Pria" : "Wanita";
    }

    public String getFormattedTglLahir() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final LocalDate toLocalDate = LocalDate.ofInstant(tglLahir.toInstant(), ZoneId.systemDefault());

        return toLocalDate.format(formatter);
    }

    public void print() {
        Utils.drawSeparator();
        System.out.println("NIM\t\t: " + nim);
        System.out.println("Nama\t\t: " + nama);
        System.out.println("Tanggal Lahir\t: " + getFormattedTglLahir());
        System.out.println("Jenis Kelamin\t: " + getGenderString());
    }

    public static void validateGender(int input) throws InvalidGenderChoice {
        final List<Integer> choices = Arrays.asList(0, 1);

        if (!choices.contains(input))
            throw new InvalidGenderChoice();
    }

    public static Date parseTglLahir(int DD, int MM, int YYYY) {
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, DD);
        calendar.set(Calendar.MONTH, MM - 1);
        calendar.set(Calendar.YEAR, YYYY);

        final Instant instant = calendar.toInstant();

        return Date.from(instant);
    }

    public static class InvalidGenderChoice extends Exception {
        private static final long serialVersionUID = 1L;

        public InvalidGenderChoice() {
            super("Invalid Gender Choice! Please input 0 or 1 only...");
        }
    }
}
