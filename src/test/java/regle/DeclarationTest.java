package regle;

import formationcontinue.Activite;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeclarationTest {

    public DeclarationTest() {
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
    public void testEstSexeValide() {
        assertEquals(false, Declaration.estSexeValide("-1"));
        assertEquals(true, Declaration.estSexeValide("0"));
        assertEquals(true, Declaration.estSexeValide("2"));
        assertEquals(false, Declaration.estSexeValide("3"));
    }

    @Test
    public void testGetRegle() {
        assertTrue(Declaration.getRegle("architectes") instanceof regle.Architectes);
        assertTrue(Declaration.getRegle("géologues") instanceof regle.Geologues);
        assertTrue(Declaration.getRegle("psychologues") instanceof regle.Psychologues);
        assertTrue(Declaration.getRegle("podiatres") instanceof regle.Podiatres);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetRegleException() {
        Declaration.getRegle("");
    }

    @Test
    public void testEstDeclarationValide() {
        Activite activite = new formationcontinue.Activite();
        activite.setHeures(1);
        activite.setDescription("%%%%%%%%%%%%%%%%%(21)");
        List<Activite> activites = new ArrayList<>();
        activites.add(activite);
        formationcontinue.Declaration declaration = new formationcontinue.Declaration();

        declaration.setOrdre("architectes");
        declaration.setNumeroDePermis("T2222");
        declaration.setActivities(activites);
        assertTrue(regle.Declaration.estDeclarationValide(declaration));

        activite.setDescription("trop court");
        assertFalse(regle.Declaration.estDeclarationValide(declaration));

        activite.setHeures(0);
        assertFalse(regle.Declaration.estDeclarationValide(declaration));

        declaration.setNumeroDePermis("BAD");
        assertFalse(regle.Declaration.estDeclarationValide(declaration));

        declaration.setOrdre("mauvais");
        assertFalse(regle.Declaration.estDeclarationValide(declaration));
    }

}
