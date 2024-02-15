import java.io.*;


public class DataLayer {

    static final String NOM_FITXER = "./sopes.bin";

    static void guardarSopaBytes(Sopa sopa) throws IOException {
        File arxiu = new File(NOM_FITXER);
        FileOutputStream fos = new FileOutputStream(arxiu, false);
        DataOutputStream dos = new DataOutputStream(fos);
        escriureSopaBytes(sopa, dos);
        fos.flush();
        fos.close();
    }
    private static void escriureSopaBytes(Sopa sopa, DataOutputStream dos) throws IOException {
        dos.writeUTF(sopa.getCadenaSopa());
        for (int i = 0; i < sopa.trobat.length; i++) {
            for (int j = 0; j < sopa.trobat[i].length; j++) {
                dos.writeBoolean(sopa.trobat[i][j]);
            }
        }
    }
    static Sopa llegirSopaBytes() throws IOException {
        File arxiu = new File(NOM_FITXER);
        if (!arxiu.exists()){
            return null;
        }
        FileInputStream fis = new FileInputStream(arxiu);
        DataInputStream dis = new DataInputStream(fis);
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


}
