/*
    Aufgabe 3) while-Schleifen
*/
@SuppressWarnings("StringConcatenationInLoop")
public final class Aufgabe3 {
	private Aufgabe3() {
	}

	/* Annahme: Implementierung in einzelnen Methoden innerhalb derselben Klasse erlaubt */
	public static void main(String[] args) {
		final String text = "Ich gehe zum Bagger und starte den Bagger.",
					 text2 = "Ich gehe in die Garage.";

		// a)

		// 1:

		printTask();
		substringUntilGG(text);
		substringUntilGG(text2);

		// 2: printTask(substringUntilGG2(text) + "\n\t" + substringUntilGG2(text2));

		// b)

		printTask(charAt(text) + "\n\t" + charAt(text2));

		//  c)

        /*
            ]26, 260] => 26 < i <= 260
        */

		int sum = 0, i = (26 + 2);

		while (i <= 260) {
			if (i % 13 == 0) {
				sum += i;
			}
			i += 2;
		}

		printTask(sum);

		//  d)

		printTask(reverseWithoutG(text) + "\n\t" + reverseWithoutG(text2));

		//  e)

		printTask(countVowels(text) + "\n\t" + countVowels(text2));

	}

	/* ******************************** */
	/* ****** SUBSTRING UNTIL GG ****** */
	/* ******************************** */

	public static void substringUntilGG(String s) {
		int i = -1;

		System.out.print('\t');

		while (i++ < s.length() - 1) {
			if (s.charAt(i) == 'g' &&
				    s.charAt(i + 1) == 'g') {
				break;
			}
			System.out.print(s.charAt(i));
		}

		System.out.print('\n');
	}

	/* ******************************** */
	/* ************ CHAR AT *********** */
	/* ******************************** */

	public static int charAt(String s) {
		int i = 0,
			charAt = -1;

		while (i++ < s.length() - 1) {
			if (s.charAt(i) == 't') {
				charAt = i;
				break;
			}
		}

		return charAt;
	}

	/* ******************************** */
	/* ****** REVERSE WITHOUT G ******* */
	/* ******************************** */

	public static String reverseWithoutG(String s) {
		int i = s.length() - 2;
		String out = "";

		while (i >= 0) {
			if (s.charAt(i) != 'g') {
				out += s.charAt(i);
			}
			i -= 2;
		}

		return out;
	}

	/* ******************************** */
	/* ********* COUNT VOWELS ********* */
	/* ******************************** */

	/* verwendete Quelle: https://de.wikipedia.org/wiki/Buchstabenh%C3%A4ufigkeit */

	public static int countVowels(String s) {
		int vowelCount = 0, i = -1;
		s = s.toLowerCase();

		while (i++ < s.length() - 1) {
			if (s.charAt(i) == 'e' ||
				    s.charAt(i) == 'i' ||
				    s.charAt(i) == 'a' ||
				    s.charAt(i) == 'u' ||
				    s.charAt(i) == 'o') {
				vowelCount++;
			}
		}

		return vowelCount;
	}

	/* ******************************** */
	/* ********** PRINT TASK ********** */
	/* ******************************** */

	public static char currentTask = 'a';

	public static void printTask() {
		System.out.print(currentTask++ + ")");
	}

	public static void printTask(Object solution) {
		System.out.println(currentTask++ + ")\t" + solution);
	}
}