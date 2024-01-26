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
            if (buscarParaula(paraula)) {
                System.out.println("Paraula trobada");
                trobades++;
            } else {
                System.out.println("Paraula no trobada");
            }

        }
        System.out.println("Has trobat totes les paraules");
    }

    /**
     * Busca una paraula a la sopa de lletres
     * @param paraula a buscar
     * @return true si la paraula ha estat trobada
     */
    public static boolean buscarParaula(String paraula) {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j] == paraula.charAt(0)) {
                    int[][] coords = comprovarParaula(paraula, i, j, true);
                    if (coords != null) {
                        marcarParaulaTrobada(coords);
                        return true;
                    } else {
                        coords = comprovarParaula(paraula, i, j, false);
                        if (coords != null) {
                            marcarParaulaTrobada(coords);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Marca la paraula com a trobada
     * @param coords coordenades de la paraula
     */
    private static void marcarParaulaTrobada(int[][] coords) {
    for (int i = 0; i < coords.length; i++) {
            trobat[coords[i][0]][coords[i][1]] = true;
        }
    }

    /**
     * Cerca la paraula a partir d'una posició inicial en una direcció
     * @param paraula a cercar
     * @param i cordenada inicial fila
     * @param j oordenada inicial columna
     * @param vertical cert si cal cercar la paraula en vertical
     * @return llista de coordenades de la paraula si la paraula ha estat trobada, null si no ha estat trobada
     */
    private static int[][] comprovarParaula(String paraula, int i, int j, boolean vertical) {
        int[][] coords = new int[paraula.length()][2];
        int k = 0;
        while (k < paraula.length() ) {
            if (i < sopa.length && j < sopa[i].length && sopa[i][j] == paraula.charAt(k)) {
                coords[k][0] = i;
                coords[k][1] = j;
                if (vertical)
                    i++;
                else
                    j++;
                k++;
            } else {
                return null;
            }
        }
        return coords;

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
        crearSopaDeLletresRecursiu(sopa, cadena, 0, 0);
        return sopa;
    }

    private static void crearSopaDeLletresRecursiu(char[][] sopa, String cadena, int i, int j) {
        if (i < sopa.length && j < sopa[i].length) {
            sopa[i][j] = cadena.charAt(i * sopa[i].length + j);
            crearSopaDeLletresRecursiu(sopa, cadena, i, j + 1);  // Crida recursiva per la següent columna
            crearSopaDeLletresRecursiu(sopa, cadena, i + 1, j);  // Crida recirsiva per la següent fila
        }
    }
}