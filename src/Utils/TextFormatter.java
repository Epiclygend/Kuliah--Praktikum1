package Utils;

public class TextFormatter {
    final static int TEXT_MAXLENGTH = 100;

    public static void drawSeparator(int count) {
        System.out.println("-".repeat(count));
    }

    public static void drawSeparator() {
        drawSeparator(TEXT_MAXLENGTH);
    }

    public static String padLeft(String text, String replacer, int maxLen) {
        final int textLen = text.length();

        if (textLen >= maxLen) {
            return text;
        }

        final int replacerCount = maxLen - textLen;

        return replacer.repeat(replacerCount) + text;
    }

    public static String padLeft(String text, String replacer) {
        return padLeft(text, replacer, TEXT_MAXLENGTH);
    }

    public static String padLeft(String text) {
        return padLeft(text, " ");
    }
}
