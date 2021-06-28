import java.awt.*;

/*
    Aufgabe 4) StdDraw Bibliothek
*/
public final class Aufgabe4 {
    private Aufgabe4() {}
    
    public static void main(String[] args) {
        /* Initialisierung der Zeichenfläche */

        StdDraw.setCanvasSize(400, 400);
        StdDraw.setScale(0, 400);
        StdDraw.setPenRadius(0.005);

        /* grüne und rote Linien */

        for(int i = 1; i <= 10; i++) {
            if(i % 3 == 0) {
                StdDraw.setPenColor(Color.RED);
            } else {
                StdDraw.setPenColor(Color.GREEN);
            }
            StdDraw.line(0, 400, i * 40, 0);
        }

        /* blauer Kreis mittig auf orangenem Quadrat */

        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.filledRectangle((60 + 40), 100, 40, 40);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledCircle((60 + 40), 100, 20);

        /* nicht ausgefüllte cyanfarbene Kreise */

        StdDraw.setPenColor(Color.CYAN);
        StdDraw.circle((400 - 250), (400 - 50), 50);
        StdDraw.circle((400 - 50), (400 - 250), 50);

        /* 2 gelbe Ellipsen in einem magentafarbenem Quadrat */

        StdDraw.setPenColor(Color.MAGENTA);
        StdDraw.rectangle((400 - 100), (400 - 100), 100, 100);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledEllipse((400 - 100), (400 - 100), 100, 40);
        StdDraw.filledEllipse((400 - 100), (400 - 100), 40, 100);
    }
}
