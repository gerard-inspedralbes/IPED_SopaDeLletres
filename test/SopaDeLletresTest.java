import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SopaDeLletresTest {

    static String cadena = "";
    char[][] sopa = new char[10][10];
    @BeforeEach
    void setUp() {
        char lletra = 'A';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sopa[i][j] = lletra;
                cadena += lletra;
                lletra++;
                if (lletra > 'Z')
                    lletra = 'A';
            }
        }
    }

    @Test
    void crearSopaDeLletres() {
        assertArrayEquals(sopa, SopaDeLletres.crearSopaDeLletres(cadena));
        System.out.println(sopa);
        System.out.println(cadena);
    }
}