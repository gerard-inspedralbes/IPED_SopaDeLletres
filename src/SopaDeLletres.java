import Utils.Utils;

public class SopaDeLletres {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static char[][] sopa;
    public static boolean[][] trobat;

    public static void main(String[] args) {
        int trobades = 0;
        System.out.println("Benvinguts a la sopa de lletres");
        System.out.println("");
        String cadena = Utils.LlegirString("Introdueix la cadena de 100 lletres:", 100, 100);
        sopa = crearSopaDeLletres(cadena);
        trobat = new boolean[sopa.length][sopa[0].length];
        while (trobades < 5) {
            mostrarSopaDeLletres();
            String paraula = Utils.LlegirString("Introdueix la paraula a buscar:", 1, 10);
        }
    }

    /**
     * Mostra la sopa de lletres per consola
     **/
    private static void mostrarSopaDeLletres() {
        System.out.println("Sopa de lletres:");
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                pintarLletra(sopa[i][j], trobat[i][j],ANSI_RED);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     * Pinta una lletra per consola
     * @param c lletra a pintar
     * @param b si la lletra ha estat trobada
     * @param color color de la lletra
     */
    private static void pintarLletra(char c, boolean b, String color) {
        if (b)
            System.out.print(color + c + ANSI_RESET);
        else
            System.out.print(c);
    }


    /**
     * Crea una sopa de lletres a partir d'una cadena de mínim 100 lletres
     * @param cadena amb el condingut de la sopa de lletres
     * @return sopa de lletres de 10x10
     */
    public static char[][] crearSopaDeLletres(String cadena) {
        char[][] sopa = new char[10][10];
        int posicio = 0;
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                sopa[i][j] = cadena.charAt(posicio);
                posicio++;
            }
        }
        return sopa;
    }


}