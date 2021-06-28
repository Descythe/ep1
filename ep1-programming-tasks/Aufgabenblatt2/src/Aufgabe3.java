/*
    Aufgabe 3) Erste Methoden
*/
@SuppressWarnings("StringConcatenationInLoop")
public final class Aufgabe3 {
	private Aufgabe3() {
	}

	public static void main(String[] args) {
		printAlphabet();
		printNumbersInInterval(1, 10);

		assert (calcSum(0, 0) == 0);
		assert (calcSum(0, 1) == 1);
		assert (calcSum(1, 2) == 3);
		assert (calcSum(1, 10) == 55);
		assert (calcSum(23, 356) == 63293);

		assert (getCirclePerimeter(0) == 0);
		assert (getCirclePerimeter(1) == 2 * Math.PI);
		assert (getCirclePerimeter(7) == 14 * Math.PI);

		assert (!isFirstCharacterGreater('A', 'B'));
		assert (!isFirstCharacterGreater('+', '4'));
		assert (!isFirstCharacterGreater('5', '5'));
		assert (isFirstCharacterGreater('C', '?'));
		assert (isFirstCharacterGreater('t', 'g'));
		assert (isFirstCharacterGreater('8', '3'));

		assert (removeEachSecondChar("").equals(""));
		assert (removeEachSecondChar("A").equals("A"));
		assert (removeEachSecondChar("AB").equals("A"));
		assert (removeEachSecondChar("Hello World!").equals("HloWrd"));
		assert (removeEachSecondChar("Das ist ein kurzer Testtext!").equals("Dsitenkre etet"));
	}

	private static void printChar(char c) {
		System.out.print(c);
	}

	private static void printAlphabet() {
		char c = 'A';
		final int charsInAlphabet = 26;

		for (int i = 0; i < charsInAlphabet; i++) {
			printChar((char) (c + i));
			System.out.print(((i < charsInAlphabet - 1) ? " " : ""));
		}

		System.out.print("\n");
	}

	/* Vorbedingung(en): start ≤ end */
	private static void printNumbersInInterval(int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(i + ((i < end) ? " " : ""));
		}

		System.out.print("\n");
	}

	/* Vorbedingung(en): start ≤ end */
	private static int calcSum(int start, int end) {
		int sum = 0;

		for (int i = start; i <= end; i++) {
			sum += i;
		}

		return sum;
	}

	/* Vorbedingung(en): radius ≥ 0 */
	private static double getCirclePerimeter(int radius) {
		return 2 * Math.PI * radius;
	}

	private static boolean isFirstCharacterGreater(char charOne, char charTwo) {
		return (((int) charOne) > ((int) charTwo));
	}

	private static String removeEachSecondChar(String text) {
		String output = "";

		for (int i = 0; i <= text.length() - 1; i += 2) {
			output += text.charAt(i);
		}

		return output;
	}
}
