/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
@SuppressWarnings("DuplicatedCode")
public class Aufgabe1 {

	private static String sort(String input) {
		String output = input; /* Erstelle eine Kopie des Parameters input */
		int i = 0; /* Initialisiere den Integer i mit dem Wert 0 */

		while (i < output.length()) {
			/* wenn i = 0 oder der aktueller Character ≥ dem vorherigen ist, erhöhe den Index um 1 */
			if (i == 0 || output.charAt(i) >= output.charAt(i - 1)) {
				i++;
			} else { /* wenn nicht: erstelle einen neuen String, welcher die Characters vertauscht */
				output = output.substring(0, i - 1) + output.charAt(i) +
					     output.charAt(i - 1) + output.substring((i--) + 1);
			}
		}
		return output;
	}

	/*

	private static int length(String s) {
		return s.length();
	}

	private static int sum(int n, int s) {
		return n + s;
	}

	private static boolean greaterOrEqual(char c1, char c2) {
		return c1 >= c2;
	}

	private static String substring(String s, int n1, int n2) {
		return s.substring(n1, n2);
	}

	 */

	public static void main(String args[]) {
		System.out.println(sort("ab"));
		System.out.println(sort("ba"));
		System.out.println(sort("aa"));
		System.out.println(sort("cba"));
		System.out.println(sort("abababab"));
		System.out.println(sort("abcfghed"));
		System.out.println(sort("abnasnasab"));
		System.out.println(sort("najskaghkkjsfvjhbavbdfsan"));
		System.out.println(sort("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

		assert (sort("ab").equals("ab"));
		assert (sort("ba").equals("ab"));
		assert (sort("aa").equals("aa"));
		assert (sort("cba").equals("abc"));
		assert (sort("abababab").equals("aaaabbbb"));
		assert (sort("abcfghed").equals("abcdefgh"));
		assert (sort("abnasnasab").equals("aaaabbnnss"));
		assert (sort("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
		assert (sort("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));
	}
}


