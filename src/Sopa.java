public class Sopa {
    public char[][] sopa;
    public boolean[][] trobat;

    public int paraulesTrobades = 0;

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

    public void setSopa(String cadena) {
        sopa = new char[10][10];
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                sopa[i][j] = cadena.charAt(i*10+j);
            }
        }
    }

    public String getBrief() {
        return this.getCadenaSopa().substring(0,10);
    }

    public String estat() {
        if (this.paraulesTrobades ==  5){
            return "FINALITZADA";
        }else{
            return "EN CURS";
        }
    }
}
