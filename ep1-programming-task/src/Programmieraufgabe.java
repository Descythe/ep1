import java.awt.*;

public class Programmieraufgabe {
    public static void main(String[] args) {
        drawPattern(4,60);
        // drawPattern(8,35);
        // drawPattern(10,90);
        // drawPattern(16,18);
    }

    /* Annahme: n > 1, a ≥ 0 */
    private static void drawPattern(int n, int a) {
        int canvasSize = 500;

        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(-(canvasSize), canvasSize);

        /* Zeichnung (schwarze Kreise) */

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.BLACK);

        double minRadius = 0.1 * 0.5 * canvasSize;
        double radius = (250 - minRadius) / (n - 1);

        for(int i = 0; i < n; i++) {
            StdDraw.circle(0, 0, (canvasSize / 2.0) - (i * radius));
        }

        /* Zeichnung (rote Linie) */

        StdDraw.setPenColor(Color.RED);
        StdDraw.line(0, 0, 0, -(canvasSize / 2.0));

        /* Zeichnung (Kreisbögen) */

        StdDraw.setPenRadius(0.02);
        for(int i = 0; i < n; i++) {
            if(-(30) + (i * a) % 360 < 270 && 30 + (i * a) % 360 > 270) {
                StdDraw.setPenColor(Color.RED);
            } else {
                StdDraw.setPenColor(Color.BLUE);
            }
            StdDraw.arc(0, 0, minRadius + (i * radius), -(30) + (i * a), 30 + (i * a));
        }
    }
}



