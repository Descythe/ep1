/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
@SuppressWarnings({"UnnecessaryLocalVariable"})
public class Aufgabe1 {
    
    private static void genArray(int[] filledArray){
        int value = 5;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = value;
            value += 5;
        }
    }
    
    private static void printFilteredArrayContent(int[] workArray){
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if(copiedArray[i] % 4 == 0){
                copiedArray[i] = 0;
            }
        }
        printArray(copiedArray);
    }
    
    private static void genNewArrayContent(int[] workArray){
        int[] helpArray = new int[15];
        int value = 7;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = value;
            value += 7;
        }
        workArray = helpArray;
        printArray(workArray);
    }
    
    private static void printArray(int[] workArray){
        for(int i = 0; i < workArray.length; i++) {
            System.out.print(workArray[i]+ " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] filledArray = new int[15];
        genArray(filledArray);
        printArray(filledArray);
        
        printFilteredArrayContent(filledArray);
        printArray(filledArray);
        
        filledArray[0] = 2020;
        printArray(filledArray);
        
        genNewArrayContent(filledArray);
        printArray(filledArray);
    }
    
    //***************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ****
    //***************************************************************************

    //Frage 1: for(int i = 0; i <= workArray.length; i--): Fehler da Grenzwert falsch und i dekrementiert wird.
    //Frage 2: da der Methode die Referenz des Arrays mitgegeben wurde, die Werte dieses also nicht kopiert wurden.
    //         Dadurch wird das geänderte Array auch in die Main-Funktion "übernommen"
    //Frage 3: da innerhalb der Methode "printFilteredArrayContent" keine Kopie des Arrays erstellt wird, sondern nur die
    //         Referenz übergeben wurde. Diese zeigt allerdings auf ein und dasselbe Array (innerhalb des Speichers).
    //         (Kopieren des int[]-Arrays siehe unten)
    //Frage 4: die Referenz im Speicher wurde im Übergabewert mitgegeben, danach jedoch auf eine andere Adresse gesetzt,
    //         an der das Array geändert wurde. Dadurch wurde das ursprüngliche Array nie verändert. (scoping)

    // Zusatzfragen:

    // 1. den Datentyp int (short, byte oder char können durch implizite Typumwandlung auf int ebenfalls genutzt werden)
    // 2. Nein, jede Komponente des Arrays wird auf ihren Standardwert initialisiert
    // 3. Die Länge eines Arrays kann nachträglich nicht verändert werden => daher neues Array mit
    //    gewünschter Länge erzeugen und mit Ursprungswerten befüllen.
    // 4. Object.clone() / Arrays.copyOf() / System.arraycopy() / ...
    // 5. Ja
    // 6. Nein, da dann die Speicheradresse beider Arrays verglichen wird und nicht deren Inhalt.
}
