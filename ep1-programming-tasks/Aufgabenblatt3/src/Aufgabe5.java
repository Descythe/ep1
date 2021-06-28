import java.awt.*;

/*
    Aufgabe 5) Kreuzmuster mit Rechtecken => Rekursiv vs. Iterativ
*/
@SuppressWarnings("SameParameterValue")
public class Aufgabe5 {

    private static int count = 0;

    private static void drawPatternRecursive(int x, int y, int l, boolean c) {
        count++; /* zählt rekursive Aufrufe */
        c = !c;

        if((l / 2) >= 16) {
            drawPatternRecursive(x + l / 4, y + l / 4, (l / 2), c);
            drawPatternRecursive(x - l / 4, y - l / 4, (l / 2), c);
            drawPatternRecursive(x - l / 4, y + l / 4, (l / 2), c);
            drawPatternRecursive(x + l / 4, y - l / 4, (l / 2), c);
        }

        StdDraw.setPenColor((c) ? Color.BLUE : Color.ORANGE);
        StdDraw.filledRectangle(x, y, l * 0.5, 0.025 * l);
        StdDraw.filledRectangle(x, y, 0.025 * l, l * 0.5);
    }

    private static void drawPatternIterative(int width) {
        int l = 16;
        boolean c = true;

        while(l <= width) {
            StdDraw.setPenColor((c) ? Color.BLUE : Color.ORANGE);

            for (int i = (width - l) / 2; i > -(width / 2); i -= l) {
                for (int j = (width - l) / 2; j > -(width / 2); j -= l) {
                    StdDraw.filledRectangle(i, j, l * 0.5, l * 0.025);
                    StdDraw.filledRectangle(i, j, l * 0.025, l * 0.5);
                }
            }

            c = !c;
            l *= 2;
        }
    }

    public static void main(String[] args) {
        final int windowSize = 512;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(windowSize, windowSize);
        StdDraw.setScale(-(windowSize / 2.0), (windowSize / 2.0));

        /* ******************************* */
        /* ****** Iterative Methode ****** */
        /* ******************************* */

        drawPatternIterative(windowSize);

        /* ******************************* */
        /* ****** Rekursive Methode ****** */
        /* ******************************* */

        // drawPatternRecursive(0, 0, windowSize, true);

        int sum = 0, i = 0, length = windowSize;

        while(length >= 16) {
            sum += Math.pow(4, i++);
            length /= 2;
        }

        assert (count == 0 || count == sum);

        StdDraw.show();
    }
}

/*

Zusatzfrage(n):

1. Wie oft wird die Methode drawPatternRecursive aufgerufen, wenn als Abbruchbedingung
die Auflösungsgrenze von l<16 gewählt wird?

2. Wie viele Kreuze werden auf der letzten Rekursionsstufe (die kleinsten Kreuze) gezeichnet?

3. Wie müssen Sie Ihr Programm abändern, um das Muster in Abbildung 1b zu erzeugen?

 */


