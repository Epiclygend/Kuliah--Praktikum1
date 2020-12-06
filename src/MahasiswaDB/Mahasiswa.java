package MahasiswaDB;

import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Mahasiswa implements HasID {
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

    public static void validateGender(int input) throws InvalidGenderChoice {
        final List<Integer> choices = Arrays.asList(0, 1);

        if (!choices.contains(input)) 
            throw new InvalidGenderChoice();
    }

    public static Date parseTglLahir(int DD, int MM, int YYYY) {
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, DD);
        calendar.set(Calendar.MONTH, MM);
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
