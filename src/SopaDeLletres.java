import Utils.Utils;

public class SopaDeLletres {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        System.out.println("Benvinguts a la sopa de lletres");
        boolean sortir = false;
        while (!sortir)
            switch (mostrarMenu()) {
                case 1:
                    carregarSopes();
                    break;
                case 2:
                    jugar();
                case 0:
                    sortir = true;
                    System.out.println("Adeu!");
            }
    }

    private static void carregarSopes() {
        try {
            Sopa s = DataLayer.getSopa(1);
            do {
                s = crearSopaDeLletres(DataLayer.llegirCadenaTXT());
                DataLayer.guardarNovaSopaBytes(s);
            } while (s != null);
        } catch (Exception e) {
            //System.out.println("ERROR :" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void jugar() {
        try {
            Sopa s = DataLayer.getSopa(1);
            if (s == null) {
                System.out.println("No hi ha cap sopa creada, primer carregar un fitxer de sopes.");
            }
            int trobades = 0;
            while (trobades < 5) {
                mostrarSopaDeLletres(s);
                String paraula = Utils.LlegirString("Introdueix la paraula a buscar:", 1, 10);
                if (buscarParaula(s, paraula)) {
                    System.out.println("Paraula trobada");
                    trobades++;
                } else {
                    System.out.println("Paraula no trobada");
                }
                DataLayer.guardarNovaSopaBytes(s);
                //s = DataLayer.llegirSopaBytes();
            }
            System.out.println("Has trobat totes les paraules");
        } catch (Exception e) {
            //System.out.println("ERROR :" + e.getMessage());
            e.printStackTrace();
        }

    }

    public static int mostrarMenu() {
        String menu = """
                *** MENÚ ***
                1.- Carregar sopa des d'un fitxer
                2.- Buscar paraules a una sopa
                0.- Sortir
                """;
        return Utils.LlegirInt(menu, 0, 2);
    }

    /**
     * Busca una paraula a la sopa de lletres
     *
     * @param paraula a buscar
     * @return true si la paraula ha estat trobada
     */
    public static boolean buscarParaula(Sopa s, String paraula) {
        for (int i = 0; i < s.sopa.length; i++) {
            for (int j = 0; j < s.sopa[i].length; j++) {
                if (s.sopa[i][j] == paraula.charAt(0)) {
                    int[][] coords = comprovarParaula(s, paraula, i, j, true);
                    if (coords != null) {
                        marcarParaulaTrobada(s, coords);
                        return true;
                    } else {
                        coords = comprovarParaula(s, paraula, i, j, false);
                        if (coords != null) {
                            marcarParaulaTrobada(s, coords);
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
     *
     * @param coords coordenades de la paraula
     */
    private static void marcarParaulaTrobada(Sopa s, int[][] coords) {
        for (int i = 0; i < coords.length; i++) {
            s.trobat[coords[i][0]][coords[i][1]] = true;
        }
    }

    /**
     * Cerca la paraula a partir d'una posició inicial en una direcció
     *
     * @param paraula  a cercar
     * @param i        cordenada inicial fila
     * @param j        oordenada inicial columna
     * @param vertical cert si cal cercar la paraula en vertical
     * @return llista de coordenades de la paraula si la paraula ha estat trobada, null si no ha estat trobada
     */
    private static int[][] comprovarParaula(Sopa s, String paraula, int i, int j, boolean vertical) {
        int[][] coords = new int[paraula.length()][2];
        int k = 0;
        while (k < paraula.length()) {
            if (i < s.sopa.length && j < s.sopa[i].length && s.sopa[i][j] == paraula.charAt(k)) {
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
    private static void mostrarSopaDeLletres(Sopa s) {
        System.out.println("Sopa de lletres:");
        for (int i = 0; i < s.sopa.length; i++) {
            for (int j = 0; j < s.sopa[i].length; j++) {
                pintarLletra(s.sopa[i][j], s.trobat[i][j], ANSI_RED);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     * Pinta una lletra per consola
     *
     * @param c     lletra a pintar
     * @param b     si la lletra ha estat trobada
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
     *
     * @param cadena amb el condingut de la sopa de lletres
     * @return sopa de lletres de 10x10 o null si no s'ha pogut construir la sopa amb la cadena rebuda
     */
    public static Sopa crearSopaDeLletres(String cadena) {
        if(cadena == null || cadena =="" || cadena.length()<100){
            return null;
        }
        char[][] s = new char[10][10];
        crearSopaDeLletresRecursiu(s, cadena, 0, 0);
        Sopa sopa = new Sopa();
        sopa.sopa = s;
        sopa.trobat = new boolean[sopa.sopa.length][sopa.sopa[0].length];
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