package regle;

import formationcontinue.Declaration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArchitectesTest {

    Declaration declaration;
    Architectes instanceArchitecte;
    String numeroPermisValide = "T1234";
    String numeroPermisInvalide = "at1234";

    public ArchitectesTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        declaration = new Declaration();
        instanceArchitecte = new Architectes();
    }

    @After
    public void tearDown() {
        declaration = null;
        instanceArchitecte = null;
    }

    @Test
    public void testPossedeNumeroDePermisValide() {
        System.out.println("possedeNumeroDePermisValide");
        declaration.setNumeroDePermis(numeroPermisValide);

        boolean expResult = true;
        boolean result = instanceArchitecte.possedeNumeroDePermisValide(declaration);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testPossedeNumeroDePermisInvalide() {
        System.out.println("possedeNumeroDePermisValide");
        declaration.setNumeroDePermis(numeroPermisInvalide);

        boolean expResult = false;
        boolean result = instanceArchitecte.possedeNumeroDePermisValide(declaration);
        assertEquals(expResult, result);

    }

}
