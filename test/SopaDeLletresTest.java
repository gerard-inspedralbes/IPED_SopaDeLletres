import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SopaDeLletresTest {
    static String cadena = "";

    @BeforeEach
    void setUp() {
        SopaDeLletres.sopa = new char[10][10];
        SopaDeLletres.trobat = new boolean[10][10];
        char lletra = 'A';
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                SopaDeLletres.sopa[i][j] = lletra;
                cadena += lletra;
                lletra++;
                if (lletra > 'Z')
                    lletra = 'A';
            }
        }
    }

    @Test
    void test_crearSopaDeLletres() {
        assertArrayEquals(SopaDeLletres.sopa, SopaDeLletres.crearSopaDeLletres(cadena));
        System.out.println(SopaDeLletres.sopa);
        System.out.println(cadena);
    }

    @Test
    void test_buscarParaula() {
        assertTrue(SopaDeLletres.buscarParaula("AB"));
        assertTrue(SopaDeLletres.buscarParaula("FGHIJ"));
        assertFalse(SopaDeLletres.buscarParaula("DD"));
        assertFalse(SopaDeLletres.buscarParaula("DOOOOOOOOOOOOWERQWER"));
    }
}