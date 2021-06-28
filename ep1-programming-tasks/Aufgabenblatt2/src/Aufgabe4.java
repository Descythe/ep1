/*
    Aufgabe 4) Verschachtelung von Schleifen und Methoden - Münchhausen-Zahlen
*/
@SuppressWarnings("SpellCheckingInspection")
public final class Aufgabe4 {
	private Aufgabe4() {
	}

	/*  number ≥ 0 */
	private static boolean isMuenchhausenNumber(int number) {
		int input = number,
			muenchhausenNumber = 0;

		while (input > 0) {
			int currentDigit = input % 10;
			muenchhausenNumber += (currentDigit == 0) ? 0 : Math.pow(currentDigit, currentDigit); // Math.pow(0, 0) == 1, falsch!
			input /= 10;
		}

		return (muenchhausenNumber == number);
	}

	/*  Vorbedingung(en): start > 0, end > 0 und start ≤ end */
	private static int countMuenchhausenNumbers(int start, int end) {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (isMuenchhausenNumber(i)) {
				count++;
			}
		}
		return count;
	}

	/* Vorbedingung(en): start > 0, end > 0 und start ≤ end */
	private static void printMuenchhausenNumbers(int start, int end) {
		for (int i = start; i <= end; i++) {
			if (isMuenchhausenNumber(i)) {
				System.out.print(i + ((i < end) ? " " : ""));
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("0\t\t\t -> \t" + isMuenchhausenNumber(0));
		assert (isMuenchhausenNumber(0));
		System.out.println("1\t\t\t -> \t" + isMuenchhausenNumber(1));
		assert (isMuenchhausenNumber(1));
		System.out.println("3435\t\t -> \t" + isMuenchhausenNumber(3435));
		assert (isMuenchhausenNumber(3435));
		System.out.println("438579088\t -> \t" + isMuenchhausenNumber(438579088));
		assert (isMuenchhausenNumber(438579088));

		System.out.println("2\t\t\t -> \t" + isMuenchhausenNumber(2));
		assert (!isMuenchhausenNumber(2));
		System.out.println("36\t\t\t -> \t" + isMuenchhausenNumber(36));
		assert (!isMuenchhausenNumber(36));
		System.out.println("1432\t\t -> \t" + isMuenchhausenNumber(1432));
		assert (!isMuenchhausenNumber(1432));

		assert (countMuenchhausenNumbers(1, 10000) == 2);
		printMuenchhausenNumbers(1, 10000);
	}
}

/*
    Zusatzfrage(n)

    1. Wie viele Parameter können einer Methode übergeben werden?

		"Ein Methoden-Deskriptor ist nur dann gültig, wenn er Methodenparameter
		mit einer Gesamtlänge von 255 oder weniger repräsentiert, ...
		Die Gesamtlänge wird durch Addition der Beiträge der einzelnen Parameter berechnet,
		wobei ein Parameter vom Typ long oder double zwei Einheiten zur Länge beiträgt
		und ein Parameter eines anderen Typs eine Einheit." [1]

		Daher kann eine Methode maximal 255 Parameter übergeben werden.

    2. Bei einer void-Methode dürfen keine Parameter übergeben werden! Ist diese Aussage richtig?

        Nein, diese Aussage ist nicht richtig, da sich das keyword void auf den Rückgabewert und nicht auf die Parameter bezieht!

    3. Kann eine Methode mehr als einen Rückgabewert haben?

		Nein, der Rückgabewert kann aber natürlich ein Objekt oder ein Array mit mehreren Werten / Zuständen sein.

    4. Eine ganzzahlige Variable i wird innerhalb einer Methode deklariert. Kann auf diese Variable auch außerhalb der Methode zugegriffen werden?

        Nein, sie ist nur innerhalb ihres Scopes (der Methode) definiert.

    5. Haben die beiden Aufrufe func(i+1); und func(i++); semantisch die gleiche Bedeutung?

		Nein, da bei dem ersten i inkrementiert wird und dann die Methode aufgerufen wird, be dem zweiten zuerst die
		Methode aufgerufen wird und dann erst i um 1 inkrementiert wird (postinkrement).

	[1] https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3.3, aufgerufen am 13.11.2020
		(übersetzt mit https://www.deepl.com/translator)
*/
