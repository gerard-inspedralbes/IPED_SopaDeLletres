public class Sopa {
    public char[][] sopa;
    public boolean[][] trobat;

    /**
     * Obtenim la sopa com a String (les 100 lletres seguides)
     * @return cadena sopa
     */
    public String getCadenaSopa(){
        String cadena = "";
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                cadena += sopa[i][j];
            }
        }
        return cadena;
    }
}
