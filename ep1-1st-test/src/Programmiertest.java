import java.util.Arrays;

@SuppressWarnings("ExplicitArrayFilling")
public class Programmiertest {
    public static void main(String[] args) {
        int[][] data0 = {{3, 0}, {0, 1}, {2, 2}};
        int[][] data1 = {{0, 1, 0, 0, 1, 0}, {}, {2, 2, 2, 2, 0, 1}};
        int[] target1 = {0, 0, 0};
        int[] target2 = {9, 9, 9, 9};

        System.out.println(Arrays.deepToString(labelPath(3, new int[][] {})));
        System.out.println(Arrays.deepToString(labelPath(4, data0)));
        findMatches(data0, data0[1], target1);
        System.out.println(Arrays.toString(target1));
        findMatches(data1, data0[1], target1);
        System.out.println(Arrays.toString(target1));
        findMatches(data1, data0[2], target2);
        System.out.println(Arrays.toString(target2));
        System.out.println(insertMiddle("XY", "abc"));
        System.out.println(insertMiddle("01234", "abc"));
        System.out.println(insertMiddle("01234567890123", "./-"));
    }

    public static int[][] labelPath(int n, int[][] points) {
        int[][] output = new int[n][n];

        // output mit n füllen
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = n;
            }
        }

        for (int[] index: points) {
            output[index[0]][index[1]] = -1;
        }

        return output;
    }

    public static void findMatches(int[][] data, int[] pattern, int[] target) {
        for (int k = 0; k < data.length; k++) {
            int patternCount = 0;

            for (int i = 0; i < data[k].length - pattern.length + 1; i++) {
                boolean patternFound = true;

                for (int j = 0; j < pattern.length; j++) {
                    if (data[k][i + j] != pattern[j]) {
                        patternFound = false;
                        break;
                    }
                }

                if (patternFound) {
                    patternCount++;
                }
            }

            target[k] = patternCount;
        }
    }

    public static String insertMiddle(String input, String seps) {
        if(input.length() <= 1 || seps.isEmpty()) {
            return input;
        }

        return insertMiddle(input.substring(0, input.length() / 2), seps.substring(1)) +
               seps.charAt(0) +
               insertMiddle(input.substring((input.length() / 2)), seps.substring(1));
    }
}



