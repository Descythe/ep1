/*
    Aufgabe 2) for-Schleifen
*/
public final class Aufgabe2 {
	private Aufgabe2() {}

	public static void main(String[] args) {

		// a)

        /*
            [10, 110[ => 10 <= i < 110
        */

		int sum = 0;

		for (int i = 10; i < 110; i++) {
			sum += i;
		}

		printTask(sum);

		// b)

        /*
            ]12, 120] => 12 < i <= 120
        */

		printTask();

		for (int i = (12 + 6); i <= 120; i += 6) {
			if (i % 4 == 0) {
				System.out.println("\t" + i);
			}
		}

		// c)

        /*
            ]170, 34[ => 170 > i > 34
        */

		printTask();

		for (int i = (170 - 2); i > 34; i -= 2) {
			if (i % 17 == 0) {
				System.out.println("\t" + i);
			}
		}

		// d)

        /*
            [-10, 10] => (-10 <= i <= 10) => ungerade Zahlen: -9 <= i <= 9
        */

		int product = 1;

		for (int i = (-10 + 1); i <= (10 - 1); i += 2) {
			product *= i;
		}

		printTask(product);

		// e)

		final String s = "Schiffe für den Fischfang.";
		int f = 0;

		for (int i = 0; i <= s.length() - 1; i++) {
			if (s.charAt(i) == 'f') f++;
		}

		printTask(f);
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