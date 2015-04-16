package regle;

import formationcontinue.Declaration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PsychologuesTest {

    public PsychologuesTest() {
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
        declaration.setNumeroDePermis("34567-25");
        Psychologues instance = new Psychologues();

        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("3456-25");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("3A456-25");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("35456-2");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("37456-75");
        assertEquals(true, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("36456-2H");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

        declaration.setNumeroDePermis("3545625");
        assertEquals(false, instance.possedeNumeroDePermisValide(declaration));

    }

}
