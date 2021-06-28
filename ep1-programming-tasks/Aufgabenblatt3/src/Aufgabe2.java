/*
    Aufgabe 2) Überladen von Methoden
*/
@SuppressWarnings("StringConcatenationInLoop")
public class Aufgabe2 {

    /* Vorbedingung: text != null */
    private static void addSeparator(String text, char separator) {
        String output = "";

        for(int i = 0; i < text.length(); i++) {
            output += "" + separator + text.charAt(i); // Aufruf der Methode Character.toString(separator) nicht explizit in der Angabe erlaubt
        }

        System.out.println(output.substring(1));
    }

    /* Vorbedingung: number > 0 */
    private static void addSeparator(int number, char separator) {
        addSeparator(Integer.toString(number), separator);
    }

    /* Vorbedingungen: text != null und separators != null */
    private static void addSeparator(String text, String separators) {
        for(int i = 0; i < separators.length(); i++) {
            addSeparator(text, separators.charAt(i));
        }
    }

    /* Vorbedingung: text != null */
    private static void addSeparator(String text) {
        addSeparator(text, '$');
    }

    public static void main(String[] args) {
        String text0 = "A";
        String text1 = "AB";
        String text2 = "Hello!";
        String text3 = "-Java-";
        String text4 = " TEST ";

        addSeparator(text0, '?');
        addSeparator(text1, ',');
        addSeparator(text2, ':');
        addSeparator(text3, '-');
        addSeparator(text4, '+');

        addSeparator(1, '$');
        addSeparator(35, '*');
        addSeparator(657, ':');
        addSeparator(2048, '#');
        addSeparator(26348, '+');
        addSeparator(-26348, '+');

        addSeparator(text1, "+#$");
        addSeparator(text2, ":*&!");

        addSeparator(text0);
        addSeparator(text1);
        addSeparator(text2);
    }
}
