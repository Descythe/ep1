/*
    Aufgabe 3) Zweidimensionale Arrays und StdDraw - Bildverarbeitung "Finding Waldo"
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@SuppressWarnings({"ConstantConditions", "ManualArrayCopy"})
public final class Aufgabe3 {
    private Aufgabe3() {

    }

    // converts RGB image into a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int)(tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }

        }
        return imgArray;
    }

    // converts RGB image into a 3D color array
    private static int[][][] convertImg2ColorArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] imgArray = new int[3][height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[0][row][col] = tempColor.getRed();
                imgArray[1][row][col] = tempColor.getGreen();
                imgArray[2][row][col] = tempColor.getBlue();
            }
        }
        return imgArray;
    }

    // draws the image array specified by color channels imgArrayR, imgArrayG and imgArrayB into the canvas
    private static void drawImage(int[][] imgArrayR,int[][] imgArrayG,int[][] imgArrayB) {
        // draw color image on the StdDraw window
        StdDraw.enableDoubleBuffering();
        for (int y = 0; y < imgArrayR.length; y++) {
            for (int x = 0; x < imgArrayR[y].length; x++) {
                StdDraw.setPenColor(imgArrayR[y][x],imgArrayG[y][x],imgArrayB[y][x]);
                StdDraw.filledSquare(x,imgArrayR.length-y,0.5);
            }
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();
    }

    // detect waldo by template matching and return its bounding box values

    /*

    Vorbedingungen: imgArrayGrayscale != null, imgArrayGrayscale.length > 0, fur alle gültigen i gilt,
    dass imgArrayGrayscale[i].length dem selben konstanten Wert größer 0 entspricht;
    templateArray != null, templateArray.length > 0 und ungerade, fur alle gültigen i gilt,
    dass templateArray[i].length dem selben konstanten und ungeraden Wert größer 0 entspricht

     */

    private static int[] detectWaldo(int[][] imgArrayGrayscale, int[][] templateArray) {
        int minimalSad = Integer.MAX_VALUE;
        int[] minimalSadCoordinates = null; // {x1, y1, x2, y2}

        for (int i = 0; i < imgArrayGrayscale.length - templateArray.length; i++) {
            for (int j = 0; j < imgArrayGrayscale[i].length - templateArray[0].length; j++) {
                int currentSad = sad(
                    slice2DArray(
                        imgArrayGrayscale,
                        templateArray.length,
                        templateArray[0].length,
                        i,
                        j
                    ),
                    templateArray
                );
                if(currentSad < minimalSad) {
                    minimalSad = currentSad;
                    minimalSadCoordinates = new int[]{i, j, i + templateArray.length - 1, j + templateArray[0].length - 1};
                }
            }
        }

        return minimalSadCoordinates;
    }

    private static int[][] slice2DArray(int[][] workArray, int height, int width, int heightOffset, int widthOffset) {
        int[][] resultArray = new int[height][width];

        for (int i = heightOffset; i < height + heightOffset; i++) {
            for (int j = widthOffset; j < width + widthOffset; j++) {
                resultArray[i - heightOffset][j - widthOffset] = workArray[i][j];
            }
        }

        return resultArray;
    }

    private static int sad(int[][] picture1, int[][] picture2) {
        int sad = 0;

        for (int i = 0; i < picture1.length; i++) {
            for (int j = 0; j < picture1[i].length; j++) {
                sad += Math.abs(picture2[i][j] - picture1[i][j]);
            }
        }

        return sad;
    }

    public static void main(String[] args) {
        // waldo1
        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/lht2cy0GFclxbl2/download"; //waldo1.png
        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/f9onCE9vf89ZYLJ/download"; //template1.png

        // waldo2
        // String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/3HYvf4xBkiZUYr1/download"; //waldo2.png
        // String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/spG8LoK4x6HqOkf/download"; //template2.png

        // waldo3
        // String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/9RmCwGkOjgwwkzh/download"; //waldo3.png
        // String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/CDVrqihS7t9lfvm/download"; //template3.png


        BufferedImage img = null;
        // try to open image file
        try {
            URL url_img_waldo = new URL(linkWaldo);
            img = ImageIO.read(url_img_waldo);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        BufferedImage template = null;
        // try to open template image file
        try {
            URL url_img_template = new URL(linkTemplate);
            template = ImageIO.read(url_img_template);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // set StdDraw window size based on the image size
        int width = img.getWidth();
        int height = img.getHeight();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        // extract color channels R,G,B
        int[][][] imgArray = convertImg2ColorArray(img);
        int[][] imgArrayR = imgArray[0];
        int[][] imgArrayG = imgArray[1];
        int[][] imgArrayB = imgArray[2];
        // convert input image and template image to grayscale, because detection of Waldo works by gray value comparison
        int[][] imgArrayGrayscale = convertImg2Array(img);
        int[][] templateArray = convertImg2Array(template);

        int[] boundingBox = detectWaldo(imgArrayGrayscale,templateArray);

        System.out.println(Arrays.toString(boundingBox));

        if (boundingBox != null) {
            for (int i = 0; i < imgArrayR.length; i++) {
                for (int j = 0; j < imgArrayR[i].length; j++) {
                    if(!(j >= boundingBox[1] && j <= boundingBox[3] && i >= boundingBox[0] && i <= boundingBox[2])){
                        imgArrayR[i][j] = Math.max(imgArrayR[i][j] - 150, 0);
                        imgArrayG[i][j] = Math.max(imgArrayG[i][j] - 150, 0);
                        imgArrayB[i][j] = Math.max(imgArrayB[i][j] - 150, 0);
                    }
                }
            }
        }

        drawImage(imgArrayR,imgArrayG,imgArrayB);

    }
}





