/*
    Aufgabe 4) Rekursion
*/
public class Aufgabe4 {

    /* Vorbedingungen: text != null und text.length() > 0 */
    private static int countCharsSmaller(String text, char value) {
        if(!text.isEmpty()) {
            return ((value >= text.charAt(0)) ? 1 : 0) + countCharsSmaller(text.substring(1), value);
        } else {
            return 0;
        }
    }

    /* Vorbedingungen: text != null, text.length() > 0 und start ≤ end */
    private static String removeCharsInString(String text, char start, char end) {
        if(!text.isEmpty()) {
            if(text.charAt(0) > start &&
               text.charAt(0) < end) {

                return removeCharsInString(text.substring(1), start, end);

            } else {
                return text.charAt(0) + removeCharsInString(text.substring(1), start, end);
            }
        } else {
            return "";
        }
    }

    /* Vorbedingungen: text != null und es kommt im gesamten String höchstens eine Ziffer vor */
    private static String shiftDigitRight(String text) {
        if(text.length() >= 2) {
            char c = text.charAt(0);

            if(c >= '0' && c <= '9') {
                return text.charAt(1) + shiftDigitRight(c + text.substring(2));
            }

            return c + shiftDigitRight(text.substring(1));
        } else {
            return text;
        }
    }

    public static void main(String[] args) {
        System.out.println(countCharsSmaller("DAS (ist) ]ein] Test!", (char) 100));
        System.out.println(countCharsSmaller("a!", (char) 200));
        System.out.println(countCharsSmaller("Ein Test", (char) 100));
        System.out.println();

        System.out.println(removeCharsInString("testtrompete", 'd', 'n'));
        System.out.println(removeCharsInString("test", 's', 'u'));
        System.out.println(removeCharsInString("t", 't', 't'));
        System.out.println(removeCharsInString("angabe", 'a', 'z'));
        System.out.println();

        System.out.println(shiftDigitRight("az3kj"));
        System.out.println(shiftDigitRight("kjdn{nd8xngs+d#k"));
        System.out.println(shiftDigitRight(""));
        System.out.println(shiftDigitRight("4"));
        System.out.println(shiftDigitRight("ji)oiepk(2"));
        System.out.println(shiftDigitRight("ohneziffer"));

        assert (countCharsSmaller("DAS (ist) ]eine] Test!", (char) 100) == 12);
        assert (countCharsSmaller("a!", (char) 200) == 2);
        assert (countCharsSmaller("Ein Test", (char) 100) == 3);

        assert (removeCharsInString("testtrompete", 'd', 'n').equals("tsttropt"));
        assert (removeCharsInString("test", 's', 'u').equals("es"));
        assert (removeCharsInString("t", 't', 't').equals("t"));
        assert (removeCharsInString("angabe", 'a', 'z').equals("aa"));

        assert (shiftDigitRight("az3kj").equals("azkj3"));
        assert (shiftDigitRight("kjdn{nd8xngs+d#k").equals("kjdn{ndxngs+d#k8"));
        assert (shiftDigitRight("").equals(""));
        assert (shiftDigitRight("4").equals("4"));
        assert (shiftDigitRight("ji)oiepk(2").equals("ji)oiepk(2"));
        assert (shiftDigitRight("ohneziffer").equals("ohneziffer"));
    }
}
