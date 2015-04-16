package regle;

import formationcontinue.Declaration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PodiatresTest {

    public PodiatresTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNumeroDePermisValide() {
        Declaration declaration = new Declaration();
        declaration.setNumeroDePermis("34567");
        Podiatres instance = new Podiatres();

        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("345625");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("3A425");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("354562");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("37456");
        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("3645H");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

    }

}
