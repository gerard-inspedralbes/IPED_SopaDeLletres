package Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt()">

    private static Scanner scan=null;


    public static int LlegirInt() {
        int result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirInt(scan);

        return result;
    }

    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null)
            scan = new Scanner(System.in);
        result = LlegirInt(scan, missatge);

        return result;
    }

    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax)
    {
        int result =0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

// </editor-fold>


    /**
     * Obté tots els divisors d'un nombre
     * @param numero per obtenir-ne els divisors
     * @return Col·lecció de tots les divisors d'un nombre exceptuant-ne ell mateix.
     */
    public static ArrayList<Integer> getDivisors(int numero) {
        ArrayList<Integer> divisors = new ArrayList<Integer>();
        for (int i = 1; i < numero; i++) {
            if(numero%i == 0){
                divisors.add(i);
            }
        }
        return divisors;
    }
}