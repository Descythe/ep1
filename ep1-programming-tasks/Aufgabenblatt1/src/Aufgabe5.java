import java.awt.*;

/*
    Aufgabe 5) Schleifen und Zeichnen innerhalb des StdDraw-Fensters
*/
@SuppressWarnings("ConstantConditions")
public final class Aufgabe5 {
    private Aufgabe5() {}
    
    public static void main(String[] args) {
        int n = 5;
        if(n % 2 == 0) {
            System.out.println("Ungültiger Wert!");
            return;
        }

        /* Initialisierung der Zeichenfläche */

        int windowSize = 300;
        double circleRadius = (((double)windowSize) / n) / 2;

        StdDraw.setCanvasSize(windowSize, windowSize);
        StdDraw.setScale(0, windowSize);

        /* Zeichnung */

        StdDraw.setPenRadius(0.006); /* blaue Kreise */
        StdDraw.setPenColor(Color.BLUE);

        for(double i = circleRadius * 3; i <= windowSize - (circleRadius * 2); i += circleRadius * 2) {
            StdDraw.circle(i, circleRadius, circleRadius);
            StdDraw.circle(i, windowSize - circleRadius, circleRadius);
        }

        StdDraw.setPenRadius(0.002); /* rote Kreise */
        StdDraw.setPenColor(Color.RED);

        for(double i = circleRadius; i <= windowSize; i += circleRadius * 2) {
            StdDraw.circle(circleRadius, i, circleRadius);
            StdDraw.circle(windowSize - circleRadius, i, circleRadius);
        }

        StdDraw.setPenRadius(0.006); /* grüne Kreise */
        StdDraw.setPenColor(Color.GREEN);

        double startingPoint = ((double) windowSize / 2) - ((double) (n / 2) * Math.sqrt(2 * Math.pow(circleRadius, 2)));

        for(int i = 0; i <= n - 1; i++) {
            double offset = startingPoint + (i * Math.sqrt(2 * Math.pow(circleRadius, 2)));
            StdDraw.circle(offset, offset, circleRadius);
            StdDraw.circle(300 - offset, offset, circleRadius);
        }
    }
}
