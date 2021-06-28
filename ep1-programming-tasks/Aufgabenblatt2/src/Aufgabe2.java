/*
    Aufgabe 2) Verschachtelte Schleifen und Verzweigungen
*/
@SuppressWarnings("DuplicatedCode")
public final class Aufgabe2 {
	private Aufgabe2() {}

	public static void main(String[] args) {
		int figHeight = 15;
		createFigur(figHeight);
	}

	private static void createFigur(int figHeight) {
		if (figHeight < 0 || figHeight % 2 == 0 || figHeight > 53) {
			System.out.println("Die Höhe darf nur positive ungerade Werte kleiner gleich 53 annehmen!");
			return;
		}

		for (int i = 0, c = 0; i < figHeight; i++) {
			for (int n = 0; n < ((figHeight - c) / 2); n++) {
				System.out.print(' '); /* spaces */
			}

			boolean ltHalfFigHeight = i < (figHeight / 2),
					halfFigHeight = i == (figHeight / 2);

			if (ltHalfFigHeight) {
				System.out.print('/');
			} else if (halfFigHeight) {
				System.out.print('|');
			} else {
				System.out.print('\\');
			}

			char ch = 'A'; /* create characters */

			for (int n = 0; n < c; n++) {
				if (n < (c / 2)) {
					System.out.print(ch++);
				} else {
					System.out.print(--ch);
				}
			}

			if (ltHalfFigHeight) {
				System.out.print('\\');
			} else if (halfFigHeight) {
				System.out.print('|');
			} else {
				System.out.print('/');
			}

			System.out.print('\n'); /* add new line */
			c += (i >= (figHeight / 2)) ? -2 : 2;
		}
	}

	/*
	private static void createFigurOneLoop(int figHeight) {
		int characters = figHeight * (figHeight + 1);
		char ch = 'A';

		for (int i = 0, n = 0, c = 0; i < characters; i++, n = i % (figHeight + 1)) {
			int spaces = ((figHeight - c) / 2);

			if (n < spaces) {
				System.out.print(' ');
			}

			boolean ltHalfFigHeight = i < (characters / 2) - (figHeight + 1) / 2,
					gtHalfFigHeight = i > characters / 2 + (figHeight + 1) / 2;

			if (n == spaces) {
				if (ltHalfFigHeight) {
					System.out.print('/');
				} else if (gtHalfFigHeight) {
					System.out.print('\\');
				} else {
					System.out.print('|');
				}
			}

			if (n >= (spaces + 1) && n < ((figHeight + 1) - (spaces + 1))) {
				if (n <= spaces + (c / 2)) {
					System.out.print(ch++);
				}

				if (n > spaces + (c / 2)) {
					System.out.print(--ch);
				}
			}

			if (n == (spaces + 1 + c)) {
				if (ltHalfFigHeight) {
					System.out.print('\\');
				} else if (gtHalfFigHeight) {
					System.out.print('/');
				} else {
					System.out.print('|');
				}
			}

			if (n == figHeight) {
				System.out.print('\n');
				c += (i >= characters / 2) ? -2 : 2;
			}
		}
	}
	*/
}
/*

Zusatzfrage(n):

1. Kann das Beispiel mit einer einzelnen Schleife gelöst werden? Wenn ja, wie wurden Sie bei ¨
der Implementierung vorgehen?

Ja, z.B. siehe Kommentar.

*/
