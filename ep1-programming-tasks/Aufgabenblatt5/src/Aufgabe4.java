/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import java.awt.*;

public final class Aufgabe4 {
    private Aufgabe4() {

    }

    private static final int canvasSize = 500;

    private static Color[][] genLandscape(int size) {
        Color[][] landscape = new Color[size][size];
        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                landscape[i][j] = (Math.random() <= 0.1) ? Color.GRAY : Color.GREEN;
            }
        }
        return landscape;
    }

    private static void drawLandscape(Color[][] landscape) {
        for (int i = 0; i < landscape.length; i++) {
            for (int j = 0; j < landscape[i].length; j++) {
                StdDraw.setPenColor(landscape[i][j]);
                StdDraw.filledSquare(
                    j * canvasSize / (double)(landscape.length - 1),
                    i * canvasSize / (double)(landscape.length - 1),
                    canvasSize / (2.0 * landscape.length)
                );
            }
        }
    }

    private static void simLiquidFlow(Color[][] landscape, int x, int y) {
        if(y < 0 || x < 0) return;
        if (landscape[y][x] == Color.BLACK) return;
        if (landscape[y][x] == Color.GRAY) {
            if(y + 1 < landscape.length) landscape[y + 1][x] = Color.ORANGE;
            landscape[y][x] = Color.BLACK;
            simLiquidFlow(landscape, x - 1, y - 1); // links
            simLiquidFlow(landscape, x + 1, y - 1); // rechts
        } else {
            landscape[y][x] = Color.ORANGE;
            if (Math.random() >= 0.5) {
                simLiquidFlow(landscape, x + 1, y - 1);
            } else {
                simLiquidFlow(landscape, x - 1, y - 1);
            }
        }
    }

    private static void simSpreadingFire(Color[][] landscape, int x, int y) {
        if (landscape[y][x] == Color.GREEN) {
            landscape[y][x] = Color.RED;
        } else {
            if (landscape[y][x] == Color.ORANGE) {
                spreadFireInLiquid(landscape, x, y);
            }
            return;
        }

        if(y - 1 >= 0 && Math.random() >= 0.4) {
            simSpreadingFire(landscape, x, y - 1);
        }
        if(y + 1 < landscape.length && Math.random() >= 0.4) {
            simSpreadingFire(landscape, x, y + 1);
        }
        if(x - 1 >= 0 && Math.random() >= 0.4) {
            simSpreadingFire(landscape, x - 1, y);
        }
        if(x + 1 < landscape[y].length && Math.random() >= 0.4) {
            simSpreadingFire(landscape, x + 1, y);
        }
    }

    private static void spreadFireInLiquid(Color[][] landscape, int x, int y) {
        if(y < 0 || y >= landscape.length || x < 0 || x >= landscape[y].length) return;
        if (landscape[y][x] == Color.ORANGE) {
            landscape[y][x] = Color.RED;
            spreadFireInLiquid(landscape, x + 1, y + 1);
            spreadFireInLiquid(landscape, x + 1, y - 1);
            spreadFireInLiquid(landscape, x - 1, y + 1);
            spreadFireInLiquid(landscape, x - 1, y - 1);

            spreadFireInLiquid(landscape, x, y - 1);
            spreadFireInLiquid(landscape, x, y + 1);
            spreadFireInLiquid(landscape, x - 1, y);
            spreadFireInLiquid(landscape, x + 1, y);
        }
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(0, canvasSize);
        StdDraw.enableDoubleBuffering();

        int size = 100;
        Color[][] landscape = genLandscape(size);

        simLiquidFlow(landscape, size / 2, size - 1);
        drawLandscape(landscape);
        StdDraw.show();
        StdDraw.pause(1000);

        landscape[75][25] = Color.GREEN;
        simSpreadingFire(landscape, 25, 75);
        drawLandscape(landscape);
        StdDraw.show();
    }
}
