/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public final class Aufgabe2 {
    private Aufgabe2() {

    }

    /* ******************************** */
    /* ******** UNTERAUFGABE 1 ******** */
    /* ******************************** */

    private static double[][] genMeanFilter(int n) {
        if(n >= 1 && n % 2 != 0) {
            double[][] meanMatrix = new double[n][n];

            for (int i = 0; i < meanMatrix.length; i++) {
                for (int j = 0; j < meanMatrix[i].length; j++) { // can be replaced with Arrays.fill() ...
                    meanMatrix[i][j] = 1 / Math.pow(n, 2); // Kehrwert der Gesamtanzahl der Array-Elemente
                }
            }

            return meanMatrix;
        } else {
            return null;
        }
    }

    /* ******************************** */
    /* ******** UNTERAUFGABE 2 ******** */
    /* ******************************** */

    /*

        Vorbedingungen: workArray != null, workArray.length > 0, fur alle gültigen i gilt,
                        dass workArray[i].length dem selben konstanten Wert größer 0 entspricht;
                        filterArray != null, filterArray.length > 0 und ungerade, fur alle gültigen i gilt,
                        dass filterArray[i].length dem selben konstanten und ungeraden Wert größer 0 entspricht.

     */

    public static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        double[][] outArray = new double[workArray.length][workArray[0].length];

        for (int i = 0; i < outArray.length; i++) {
            outArray[i] = workArray[i].clone();
        }

        int filterHeightOffset = (filterArray.length - 1) / 2;
        int filterWidthOffset = (filterArray[0].length - 1) / 2;

        for (int i = 0; i < workArray.length; i++) {
            for (int j = 0; j < workArray[i].length; j++) {
                if (i < filterHeightOffset ||
                    i > workArray.length - 1 - filterHeightOffset ||
                    j < filterWidthOffset ||
                    j > workArray[i].length - 1 - filterWidthOffset
                ) {
                    outArray[i][j] = 0;
                } else {
                    outArray[i][j] = 0;
                    for (int k = 0; k < filterArray.length; k++) {
                        for (int l = 0; l < filterArray[k].length; l++) {
                            outArray[i][j] += workArray[i - filterHeightOffset + k][j - filterWidthOffset + l] * filterArray[k][l];
                        }
                    }
                }
            }
        }
        return outArray;
    }
    
    private static void print(double[][] workArray) {
        if(workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f",workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genMeanFilter(3);
        print(myFilter1);
        double[][] myFilter2 = genMeanFilter(5);
        print(myFilter2);

        double[][] myArray1 = {{0,0,0,0,0}, {0,1,1,1,0}, {0,1,1,1,0}, {0,1,1,1,0}, {0,0,0,0,0}};
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        print(myResultArray);
        myResultArray = applyFilter(myArray1, myFilter2);
        print(myResultArray);

        double[][] myArray2 = {{0,0,0,0,0}, {0,0,0,0,0}, {0,1,1,1,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        print(myArray2);
        //TODO: Erstellen Sie einen Shiftfilter, wenden Sie ihn auf myArray2 an und geben Sie das Ergebnis mittels print() aus

        print(applyFilter(myArray2, new double[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 0}}));
    }
    
    
}
