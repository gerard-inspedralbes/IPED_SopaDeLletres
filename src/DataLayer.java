import java.io.*;


public class DataLayer {

    static final String NOM_FITXER_BIN = "./sopes.bin";
    static final String NOM_FITXER_TXT = "./sopes.txt";
    static BufferedReader buffer;

    static void guardarNovaSopaBytes(Sopa sopa) throws IOException {
        if (sopa != null && !sopaExistent(sopa)) {
            File arxiu = new File(NOM_FITXER_BIN);
            FileOutputStream fos = new FileOutputStream(arxiu, true);
            DataOutputStream dos = new DataOutputStream(fos);
            escriureSopaBytes(sopa, dos);
            fos.flush();
            fos.close();
        }
    }

    static void guardarSopaBytes(Sopa sopa) throws IOException {
        if (sopa != null && sopaExistent(sopa)) {
            File arxiu = new File(NOM_FITXER_BIN);
            FileOutputStream fos = new FileOutputStream(arxiu, true);
            DataOutputStream dos = new DataOutputStream(fos);
            escriureSopaBytes(sopa, dos);
            fos.flush();
            fos.close();
        }
    }

    private static boolean sopaExistent(Sopa nova_sopa) throws IOException {
        DataInputStream dis = null;
        try {
            File arxiu = new File(NOM_FITXER_BIN);
            if (!arxiu.exists()) {
                return false;
            }
            FileInputStream fis = new FileInputStream(arxiu);
            dis = new DataInputStream(fis);
            while (true) {
                Sopa sopa_existent = llegirSopaBytes(dis);
                if (nova_sopa.getCadenaSopa().equals(sopa_existent.getCadenaSopa())) {
                    //Hem torbat una sopa igual

                    return true;
                }
            }
        } catch (EOFException e) {
            //hem arribat al final del fitxer sense trobar la sopa
            return false;
        } finally {
            if (dis != null) {
                //tanquem el fitxer
                dis.close();
            }
        }
    }

    private static void escriureSopaBytes(Sopa sopa, DataOutputStream dos) throws IOException {
        dos.writeUTF(sopa.getCadenaSopa());
        for (int i = 0; i < sopa.trobat.length; i++) {
            for (int j = 0; j < sopa.trobat[i].length; j++) {
                dos.writeBoolean(sopa.trobat[i][j]);
            }
        }
    }

    static Sopa llegirSopaBytes(DataInputStream dis) throws IOException {
        Sopa s = new Sopa();
        s.setSopa(dis.readUTF());
        s.trobat = new boolean[10][10];
        for (int i = 0; i < s.trobat.length; i++) {
            for (int j = 0; j < s.trobat[i].length; j++) {
                s.trobat[i][j] = dis.readBoolean();
            }
        }
        return s;
    }


    public static String llegirCadenaTXT() throws IOException {
        if (buffer == null) {
            openTXTFile();
        }
        String cadena = buffer.readLine();
        if (cadena == null || cadena == "") {
            closeTXTFile();
        }
        return cadena;
    }

    public static void openTXTFile() throws IOException {
        File arxiu = new File(NOM_FITXER_TXT);
        FileReader reader = new FileReader(arxiu);
        buffer = new BufferedReader(reader);
    }

    public static void closeTXTFile() throws IOException {
        buffer.close();
    }

    public static Sopa getSopa(int i) {
        DataInputStream dis = null;
        try {
            File arxiu = new File(NOM_FITXER_BIN);
            if (!arxiu.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(arxiu);
            dis = new DataInputStream(fis);
            return llegirSopaBytes(dis);
        } catch (IOException e) {
            return null;
        }
    }
}
