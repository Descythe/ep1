import java.awt.*;

/*
    Aufgabe 1) Schleifen - Optische Täuschung
*/
public final class Aufgabe1 {
	private Aufgabe1() {}

	public static void main(String[] args) {
		/* Initialisierung der Zeichenfläche */

		int windowSize = 520;
		int center = windowSize / 2;

		StdDraw.setCanvasSize(windowSize, windowSize);
		StdDraw.setScale(0, windowSize);

		/* Zeichnung */

		StdDraw.setPenRadius(0.0075); /* schwarze Linien */
		StdDraw.setPenColor(Color.BLACK);

		for (int i = 0; i <= 520; i += 52) {
			StdDraw.line(center, center, i, windowSize);    /* top */
			StdDraw.line(center, center, i, 0);         /* bottom */
			StdDraw.line(center, center, 0, i);         /* left */
			StdDraw.line(center, center, windowSize, i);    /* right */
		}

		StdDraw.setPenRadius(0.02); /* rote Linien */
		StdDraw.setPenColor(Color.RED);

		int lineDistance = 520 / 6;

		for (int i = lineDistance / 2; i <= 520; i += lineDistance) {
			StdDraw.line(i, 0, i, windowSize);
			StdDraw.line(0, i, windowSize, i);
		}
	}
}
