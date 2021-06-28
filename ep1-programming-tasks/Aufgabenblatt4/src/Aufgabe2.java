/*
    Aufgabe 2) Eindimensionale Arrays
*/
@SuppressWarnings({"StringConcatenationInLoop", "ManualArrayCopy", "SameParameterValue"})
public class Aufgabe2 {
    public static void main(String[] args) {
        /* a) */

        int[] a = {1, 4, 7, 0, 3, 6, 2, 8};
        printTask(toString(a, ','));

        /* b) */

        int[] b = new int[20];

        for(int i = 0; i < b.length; i++) {
            if((12 + (i * 4)) % 9 == 0) {
                b[i] = 0;
            } else {
                b[i] = 12 + (i * 4);
            }
        }

        printTask(toString(b, ' '));

        /* c) */

        int[] c = new int[]{4, 8, 1, 5, 2},
              extended = new int[c.length + 2];

        extended[0] = 100;

        for(int i = 1; i < extended.length - 1; i++) {
            extended[i] = c[i - 1];
        }

        extended[extended.length - 1] = 200;

        printTask(toString(extended, ' '));

        /* d) */

        int[] d = new int[]{15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        printTask();

        System.out.print("while-Schleife: ");

        int j = d.length;

        while(--j >= 0) {
            System.out.print(d[j] + ((j > 0) ? " " : ""));
        }

        System.out.print("\n\tfor-Schleife: ");

        for(int i = d.length - 1; i >= 0; i--) {
            System.out.print(d[i] + ((i > 0) ? " " : ""));
        }

        System.out.println();

        /* e) */

        int[] e = new int[]{61, 13, 19, 10, 2, 33, 41, 73, 0, 56, 94, 6, 45, 84, 23};

        int min = e[0],
            max = e[0],
            sum = e[0];

        for(int i = 1; i < e.length; i++) {
            min = Math.min(min, e[i]);
            max = Math.max(max, e[i]);
            sum += e[i];
        }

        printTask(toString(e, ' '));
        System.out.println("\t" + toString(new int[]{min, sum / e.length, max}, ' '));
    }

    private static String toString(int[] a, char seperator) {
        String output = "";
        for(int i: a) {
            output += "" + seperator + i;
        }
        return output.substring(1);
    }

    /* ******************************** */
    /* ********** PRINT TASK ********** */
    /* ******************************** */

    private static char currentTask = 'a';

    private static void printTask() {
        System.out.print(currentTask++ + ")\t");
    }

    private static void printTask(String solution) {
        System.out.println(currentTask++ + ")\t" + solution);
    }
}