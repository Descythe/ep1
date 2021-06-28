import java.util.Scanner;

/*
    Aufgabe 1) Verzweigungen
*/
@SuppressWarnings({"ConstantConditions", "DuplicateExpressions"})
public class Aufgabe1 {
	private Aufgabe1() {}

	/* Annahme: Implementierung in einzelnen Methoden innerhalb derselben Klasse erlaubt */
	public static void main(String[] args) {

		// a)

		printTask();

		removeBetweenPoints(".Der erste Satz. Der zweite Teil!");
		removeBetweenPoints("Zwei. Punkte im Satz.");
		removeBetweenPoints("Mehr. als. zwei. Punkte gefunden!");
		removeBetweenPoints("Ein Punkt vorhanden.");
		removeBetweenPoints("Kein Punkt vorhanden!");

		// b)

		printTask();

		String s = "Donaudampfschiff";

		occurrencesInString(s, 'k');
		occurrencesInString(s, 'n');
		occurrencesInString(s, 'f');

		// c)

		int month = 2,   /* month = m, [(m ∈ ℕ) ∧ ((m ≥ 0) ∧ (m ≤ 12))] */
			year = 2004, /* Annahme: [year ∈ ℕ] */
			days = 31;

		switch (month) {
			case 2: {
				if (year % 4 == 0 && (!(year % 100 == 0) || (year % 400 == 0))) {
					days = 29;
				} else {
					days = 28;
				}
				break;
			}
			case 4: case 6:
			case 9: case 11: {
				days = 30;
				break;
			}
			default: {
				if (month < 0 || month >= 13) {
					System.err.println("Ungültiges Datum!");
					return;
				}
			}
		}

		System.out.println("Der " + month + ". Monat des Jahres " + year + " hat " + days + " Tage!");

		/*

		days = switch (month) {
			case 4, 6, 9, 11 -> 30;
			case 2 -> {
				if (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0))) {
					yield 29;
				} else {
					yield 28;
				}
			}
			default -> {
				if (year < 0 || month < 0 || month >= 13) {
					yield 0;
				}
				yield 31;
			}
		};

		if (days == 0) {
			System.out.println("Ungültiges Datum!");
		} else {
			System.out.println("Der " + month + ". Monat des Jahres " + year + " hat " + days + " Tage!");
		}

		*/
	}

	/* ******************************** */
	/* ***** REMOVE BETWEEN POINTS **** */
	/* ******************************** */

	public static void removeBetweenPoints(String s) {
		String out = s;
		int length = s.length(),
			firstPoint = s.indexOf('.'),
			lastPoint = s.lastIndexOf('.');

		if (firstPoint != -1) {
			if (lastPoint != -1 && firstPoint != lastPoint) {
				out = s.substring(0, firstPoint) + s.substring(Math.min(lastPoint + 1, length), length);
			}
		}

		System.out.println("\t" + out);
	}

	/* ******************************** */
	/* ***** OCCURRENCES IN STRING **** */
	/* ******************************** */

	public static void occurrencesInString(String s, char c) {
		int i = s.indexOf(c);

		if (i == -1) {
			System.out.println("\tKein Zeichen gefunden");
		} else {
			if (s.indexOf(c, i + 1) != -1) {
				System.out.println("\tZwei oder mehr Zeichen gefunden");
			} else {
				System.out.println("\tGenau ein Zeichen gefunden");
			}
		}
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