/*
    Aufgabe 3) Rekursion
*/
@SuppressWarnings("SameParameterValue")
public class Aufgabe3 {

    /* Vorbedingungen: start ≤ end und divider > 0 */
    private static void printNumbersAscending(int start, int end, int divider) {
        if(start <= end) {
            if (start % divider == 0) {
                System.out.print(start + " ");
                printNumbersAscending(start + divider, end, divider);
            } else {
                printNumbersAscending(start + (start % divider), end, divider);
            }
        }
    }

    /* Vorbedingungen: start ≤ end und divider > 0 */
    private static void printNumbersDescending(int start, int end, int divider) {
        if(start < end) {
            printNumbersDescending(++start, end, divider);
            if(start % divider != 0) {
                System.out.print(start + " ");
            }
        }
    }

    /* Vorbedingung: number > 0 */
    private static int calcCrossSum(int number) {
        if(number != 0) {
            return calcCrossSum(number / 10) + number % 10;
        } else {
            return 0;
        }
    }

    /* Vorbedingung: text != null */
    private static String duplicateLetterInString(String text, char letter) {
        if(!text.isEmpty()) {
            if(text.charAt(0) == letter) {
                text = letter + text;
                return text.substring(0, 2) + duplicateLetterInString(text.substring(2), letter);
            } else {
                return text.charAt(0) + duplicateLetterInString(text.substring(1), letter);
            }
        } else {
            return "";
        }
    }

    public static void main(String[] args) {

        printNumbersAscending(10, 20, 2);
        System.out.println();
        printNumbersDescending(5, 15, 3);
        System.out.println();

        System.out.println(calcCrossSum(1));
        System.out.println(calcCrossSum(102));
        System.out.println(calcCrossSum(1234));
        System.out.println(calcCrossSum(10000));
        System.out.println(calcCrossSum(93842));
        System.out.println(calcCrossSum(875943789));
        System.out.println(calcCrossSum(-875943789));
        assert (calcCrossSum(1) == 1);
        assert (calcCrossSum(102) == 3);
        assert (calcCrossSum(1234) == 10);
        assert (calcCrossSum(10000) == 1);
        assert (calcCrossSum(93842) == 26);
        assert (calcCrossSum(875943789) == 60);
        assert (calcCrossSum(-875943789) == -60);

        System.out.println(duplicateLetterInString("", 'a'));
        System.out.println(duplicateLetterInString("hallo", 'a'));
        System.out.println(duplicateLetterInString("Hello World!", ' '));
        System.out.println(duplicateLetterInString("Es ist die Erde", 'e'));
        System.out.println(duplicateLetterInString("3HALLO4", 'L'));
        System.out.println(duplicateLetterInString("a1b2c3d4e5", 'g'));
        assert (duplicateLetterInString("Hello World!", ' ').equals("Hello  World!"));
        assert (duplicateLetterInString("hallo", 'a').equals("haallo"));
        assert (duplicateLetterInString("Es ist die Erde", 'e').equals("Es ist diee Erdee"));
        assert (duplicateLetterInString("3HALLO4", 'L').equals("3HALLLLO4"));
        assert (duplicateLetterInString("a1b2c3d4e5", 'g').equals("a1b2c3d4e5"));
    }
}

