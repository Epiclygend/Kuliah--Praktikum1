package MahasiswaDB;

import java.util.Arrays;
import java.util.List;

public class Mahasiswa implements HasID {
    String nim;
    String nama;
    String tglLahir;
    int gender;

    @Override
    public String getId() {
        return this.getId();
    }

    public String getGenderString() {
        return gender == 0 ? "Pria" : "Wanita";
    }

    public static boolean validateGender(int input) {
        final List<Integer> choices = Arrays.asList(0, 1);

        return choices.contains(input);
    }
}
