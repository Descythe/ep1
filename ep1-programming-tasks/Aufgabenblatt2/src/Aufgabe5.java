/*
    Aufgabe 5) Grafische Aufbereitung v. "GuessingGame" und Verwendung v. Methoden
*/

import java.awt.*;
import java.util.Scanner;

public final class Aufgabe5 {
	private Aufgabe5() {
	}

	public static void main(String[] args) {
		int pixelWidth = 450;
		int pixelHeight = 150;
		StdDraw.setCanvasSize(pixelWidth, pixelHeight);
		StdDraw.setXscale(0, pixelWidth);
		StdDraw.setYscale(0, pixelHeight);

		drawUserInterface(2);

		// Implementieren Sie hier Ihr "GuessingGame"

		int number = generateRandomNumber();
		int guess = 0,
			trial = 1;

		String message = "Welche Nummer von [-100, 100] wurde generiert?\n";

		while (guess != number && trial <= 8) {
			printMessage(message);
			printMessage("Versuch: #" + trial + "\n");

			drawUserInterface(trial);

			int input = readInteger();
			if (input != Integer.MIN_VALUE) {
				if (input >= -100 && input <= 100) {
					guess = input;
					message = guess + (guess < number ? " ist zu klein." : (guess > number ? " ist zu groß." : ": Glückwunsch!")) + "\n";

					if (guess == number) {
						drawUserInterface(trial, "You WON!!!", Color.GREEN);
					} else if (trial == 8) {
						drawUserInterface(trial, "You LOST!!!", Color.RED);
						message += "Zu viele Versuche gebraucht";
					}

					trial++;
				} else {
					message = "Zahl ist außerhalb des erlaubten Bereiches: [-100, 100]\n";
				}
			} else {
				message = "";
			}
		}
		printMessage(message);
	}

	private static int generateRandomNumber() {
		return (int) ((Math.random() * 201) - 100);
	}

	private static Scanner scanner = new Scanner(System.in);

	private static int readInteger() {
		if (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			} else {
				printMessage(scanner.next() + " ist keine ganze Zahl\n");
			}
		} else {
			printMessage("Ungültige Eingabe\n");
		}

		return Integer.MIN_VALUE;
	}

	private static void printMessage(String message) {
		System.out.print(message);
	}

	private static void drawUserInterface(int trial) {
		drawUserInterface(trial, "", StdDraw.BLACK);
	}

	private static void drawUserInterface(int trial, String message, Color color) {
		StdDraw.clear();

		for (int i = 1; i <= 8; i++) {
			if (i > (8 - (trial - 1))) {
				StdDraw.picture(50 * i, 150 - 80, "./img_bulb_off.jpg");
			} else {
				StdDraw.picture(50 * i, 150 - 80, "./img_bulb_on.jpg");
			}
		}

		StdDraw.setPenColor(color);
		Font font = new Font("Arial", Font.BOLD, 24);
		StdDraw.setFont(font);
		StdDraw.text((double) 450 / 2, (double) 150 - 26, message);
	}
}

/*

    Zusatzfrage(n):

    1. Wie können die eingegebenen Daten (bzw. deren Datentypen) bei der Verwendung des Scanners unterschieden werden?

    Durch den Aufruf der Methode hasNext[DATENTYP], welcher einen Boolean zurückliefert.

    2. Muss eine ungültige Eingabe aus dem Input-Stream des Scanners entfernt werden?

    Ja, da sie sonst im Input-Stream vorhanden bleibt.

*/
