package regle;

import formationcontinue.Declaration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeologuesTest {

    public GeologuesTest() {
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
    public void testPossedeNumeroDePermisValide() {
        System.out.println("possedeNumeroDePermisValide");
        Declaration declaration = new Declaration();
        declaration.setPrenom("Jojo");
        declaration.setNom("Toto");
        declaration.setNumeroDePermis("TJ3456");
        Geologues instance = new Geologues();

        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNom("");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNom("bato");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNom("tato");
        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));
    }

}
