/*
    Aufgabe 1) Zweidimensionale Arrays - Diverse Methoden
*/

import java.util.Arrays;

@SuppressWarnings({"CommentedOutCode", "ForLoopReplaceableByForEach"})
public final class Aufgabe1 {
    private Aufgabe1() {

    }
    /* ******************************** */
    /* ******** UNTERAUFGABE 1 ******** */
    /* ******************************** */

    /* Vorbedingung: n > 0 */
    private static int[][] genFilledArray(int n) {
        int[] shiftArray = new int[(n * 2) - 1]; // z.B. 5: [1, 2, 3, 4, 5, 4, 3, 2, 1]
        for(int i = 0, c = 1;
            i < shiftArray.length;
            i++, c += (i < n) ? 1 : -1) {

            shiftArray[i] = c;
        }

        int[][] output = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                output[i][j] = shiftArray[j + i];
            }
        }

        /*

        mit System.arraycopy:

        for(int i = 0; i < n; i++) {
            System.arraycopy(shiftArray, i, output[i], 0, n);
        }

        */

        return output;
    }

    /*

    private static int[][] genFilledArray(int n) {
        int[][] output = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0, c = (i + 1);
                 j < n;
                 j++, c += (i + j < n) ? 1 : -1) {

                output[i][j] = c;
            }
        }
        return output;
    }

    */

    /* ******************************** */
    /* ******** UNTERAUFGABE 2 ******** */
    /* ******************************** */

    /*
        Vorbedingungen: workArray != null und workArray.length > 0, dann gilt auch für alle
                        gültigen i, dass workArray[i].length > 0
    */
    private static void shiftLinesInArray(int[][] workArray) {
        int[] temp = workArray[workArray.length - 1];
        for (int i = workArray.length - 1; i > 0; i--) {
            workArray[i] = workArray[i - 1];
        }
        workArray[0] = temp;
    }

    /*

    mit System.arraycopy:

    private static void shiftLinesInArray(int[][] workArray) {
        int[] temp = workArray[workArray.length - 1];
        System.arraycopy(workArray, 0, workArray, 1, workArray.length - 1);
        workArray[0] = temp;
    }

    */

    /* ******************************** */
    /* ******** UNTERAUFGABE 3 ******** */
    /* ******************************** */

    /*

        Vorbedingungen: inputArray != null und inputArray.length > 0, dann gilt auch für alle
                        gültigen i, dass inputArray[i].length > 0

    */
    private static int[][] extendArray(int[][] inputArray) {
        int maxLength = 0;
        for (int[] a: inputArray) {
            maxLength = Math.max(a.length, maxLength);
        }

        int[][] outputArray = new int[inputArray.length][maxLength];
        boolean leftFill = true;

        for (int i = 0; i < outputArray.length; i++, leftFill = !leftFill) {
            int fillWithZero = maxLength - inputArray[i].length;
            for (int j = 0; j < maxLength; j++) {
                if(leftFill) {
                    outputArray[i][j] = (j < fillWithZero) ? 0 : inputArray[i][j - fillWithZero];
                } else {
                    outputArray[i][j] = (j > inputArray[i].length - 1) ? 0 : inputArray[i][j];
                }
            }
        }
        return outputArray;
    }

    /* ******************************** */
    /* ******** UNTERAUFGABE 4 ******** */
    /* ******************************** */

    /*

    Vorbedingungen: inputArray != null, inputArray.length > 0, dann gilt fur alle gültigen i,
                    dass inputArray[i].length > 0 ∧ inputArray[i].length < 32 ist. Alle Zahlen in
                    inputArray sind Nullen oder Einsen

    */
    private static int[] reformatArray(int[][] inputArray) {
        int[] output = new int[inputArray.length];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                output[i] += Math.pow(2, j) * inputArray[i][inputArray[i].length - j - 1]; // LSB -> MSB
            }
        }
        return output;
    }



    // vorgegebene Methode - BITTE NICHT VERÄNDERN!
    public static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    // vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = genFilledArray(2);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2}, {2, 1}}));
        System.out.println();

        array = genFilledArray(4);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 3}, {3, 4, 3, 2}, {4, 3, 2, 1}}));
        System.out.println();

        array = genFilledArray(7);
        printArray(array);
        System.out.println();


        int[][] array1 = new int[][]{{1, 3, 5}, {6, 2, 1}, {0, 7, 9}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{0, 7, 9}, {1, 3, 5}, {6, 2, 1}}));
        printArray(array1);
        System.out.println();

        array1 = new int[][]{{1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}, {6, 3, 0}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{6, 3, 0}, {1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}}));
        printArray(array1);
        System.out.println();


        int[][] array2 = new int[][]{{4}, {1, 2, 3}, {5, 6}, {7, 8, 9, 1}};
        int[][] array2new1 = extendArray(array2);
        printArray(array2new1);
        assert (Arrays.deepEquals(array2new1, new int[][]{{0, 0, 0, 4}, {1, 2, 3, 0}, {0, 0, 5, 6}, {7, 8, 9, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {1, 1}, {1, 0, 0, 0}, {1, 1, 0, 1}, {1}, {1}};
        int[][] array2new2 = extendArray(array2);
        printArray(array2new2);
        assert (Arrays.deepEquals(array2new2, new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 3, 2}, {5, 1}, {6, 8, 5, 4}, {9, 4, 1, 9, 2}, {1, 8, 7, 5, 3, 2, 5}, {3}};
        int[][] array2new3 = extendArray(array2);
        printArray(array2new3);
        assert (Arrays.deepEquals(array2new3, new int[][]{{0, 0, 0, 0, 1, 3, 2}, {5, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 6, 8, 5, 4}, {9, 4, 1, 9, 2, 0, 0}, {1, 8, 7, 5, 3, 2, 5}, {3, 0, 0, 0, 0, 0, 0}}));
        System.out.println();


        int[][] array3 = new int[][]{{1, 0, 1, 1}, {0, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0}, {1, 1, 1, 1, 1}};
        int[] array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{11, 3, 24, 2, 2, 31}));
        System.out.println();

        array3 = array2new2.clone();
        array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{176, 124, 3, 128, 13, 128, 1}));
        System.out.println();
    }
}

