import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


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
}
