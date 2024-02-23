import java.io.*;

import Utils.Utils;

public class DataLayer {

    static final String NOM_FITXER_BIN = "./sopes.bin";
    static final String NOM_FITXER_BIN_AUX = "./sopes_aux.bin";
    static final String NOM_FITXER_TXT = "./sopes.txt";
    static FileReader reader;
    static BufferedReader buffer;

    static boolean guardarNovaSopaBytes(Sopa sopa) throws IOException {
        if (sopa != null && !sopaExistent(sopa)) {
            File arxiu = new File(NOM_FITXER_BIN);
            FileOutputStream fos = new FileOutputStream(arxiu, true);
            DataOutputStream dos = new DataOutputStream(fos);
            escriureSopaBytes(sopa, dos);
            fos.flush();
            fos.close();
            dos.close();
            return true;
        }
        return false;
    }


    static void guardarSopaBytes(Sopa sopa_modificada, int idSopa) throws IOException {
        File arxiu_bin = new File(NOM_FITXER_BIN);
        FileInputStream fis = new FileInputStream(arxiu_bin);
        DataInputStream dis = new DataInputStream(fis);
        File arxiu_bin_aux = new File(NOM_FITXER_BIN_AUX);
        FileOutputStream fos = new FileOutputStream(arxiu_bin_aux, false);
        DataOutputStream dos = new DataOutputStream(fos);
        Sopa sopa_existent = llegirSopaBytes(dis);
        while (sopa_existent != null) {
            if (sopa_modificada.getCadenaSopa().equals(sopa_existent.getCadenaSopa())) {
                escriureSopaBytes(sopa_modificada, dos);
            } else {
                escriureSopaBytes(sopa_existent, dos);
            }
            sopa_existent = llegirSopaBytes(dis);
        }

        dos.flush();
        fos.close();
        dos.close();
        fis.close();
        dis.close();
        File arxiu_old = new File(NOM_FITXER_BIN);
        File arxiu_aux = new File(NOM_FITXER_BIN_AUX);
        arxiu_old.delete();
        arxiu_aux.renameTo(arxiu_old);

    }


    private static boolean sopaExistent(Sopa nova_sopa) throws IOException {
        File arxiu = new File(NOM_FITXER_BIN);
        if (arxiu.exists()) {
            FileInputStream fis = new FileInputStream(arxiu);
            DataInputStream dis = new DataInputStream(fis);
            Sopa sopa_existent = llegirSopaBytes(dis);
            while (sopa_existent != null) {
                if (nova_sopa.getCadenaSopa().equals(sopa_existent.getCadenaSopa())) {
                    //Hem torbat una sopa igual
                    fis.close();
                    dis.close();
                    return true;
                }
                sopa_existent = llegirSopaBytes(dis);
            }
            fis.close();
            dis.close();
        }
        return false;
    }

    private static void escriureSopaBytes(Sopa sopa, DataOutputStream dos) throws IOException {
        dos.writeUTF(sopa.getCadenaSopa());
        for (int i = 0; i < sopa.trobat.length; i++) {
            for (int j = 0; j < sopa.trobat[i].length; j++) {
                dos.writeBoolean(sopa.trobat[i][j]);
            }
            dos.writeInt(sopa.paraulesTrobades);
        }
    }

    static Sopa llegirSopaBytes(DataInputStream dis) {
        try {
            Sopa s = new Sopa();
            s.setSopa(dis.readUTF());
            s.trobat = new boolean[10][10];
            for (int i = 0; i < s.trobat.length; i++) {
                for (int j = 0; j < s.trobat[i].length; j++) {
                    s.trobat[i][j] = dis.readBoolean();
                }
                s.paraulesTrobades = dis.readInt();
            }
            return s;
        } catch (IOException e) {
            return null;
        }
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
        reader = new FileReader(arxiu);
        buffer = new BufferedReader(reader);
    }

    public static void closeTXTFile() throws IOException {
        reader.close();
        buffer.close();
        reader = null;
        buffer = null;
    }


    public static Sopa getSopa(int numSopaDesitjada) throws IOException {
        File arxiu = new File(NOM_FITXER_BIN);
        if (arxiu.exists()) {
            FileInputStream fis = new FileInputStream(arxiu);
            DataInputStream dis = new DataInputStream(fis);
            int numSopaActual = 1;
            while (true) {
                Sopa s = llegirSopaBytes(dis);
                if (numSopaActual == numSopaDesitjada) {
                    fis.close();
                    return s;
                }
                numSopaActual++;
            }
        } else {
            return null;
        }
    }
}
