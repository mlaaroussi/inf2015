package formationcontinue;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import regle.Cycle;

public class ValidationDeclarationTest {

    Declaration declarationGeo;
    Declaration declarationArch;
    Declaration declarationInvalide;
    Activite coursActivite;
    Activite uneActiviteInvalide;

    public ValidationDeclarationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        declarationGeo = new fakeDeclaration.GeoValideEtComplet();
        declarationArch = new fakeDeclaration.ArchValideEtComplet();
        declarationInvalide = new fakeDeclaration.DeclarationInvalide();
        coursActivite = new fakeActivite.Cours20141202();
        uneActiviteInvalide = new fakeActivite.Invalide();
    }

    @After
    public void tearDown() {
        declarationGeo = null;
        declarationArch = null;
        coursActivite = null;
        uneActiviteInvalide = null;
        declarationInvalide = null;
    }

    @Test
    public void testCategoriePourActiviteValide() {
        ValidationDeclaration vd = new ValidationDeclaration();
        assertTrue(vd.categoriePourActiviteValide(coursActivite));
    }

    @Test
    public void testCategoriePourActiviteInvalide() {
        ValidationDeclaration vd = new ValidationDeclaration();
        assertFalse(vd.categoriePourActiviteValide(uneActiviteInvalide));
    }

    @Test
    public void testRetenirActivites() {
        ValidationDeclaration vd = new ValidationDeclaration(declarationArch);

        regle.Cycle regle = new Cycle("2012-2014", "2012-04-01", "2014-04-01");

        List<Activite> activiteRetenues = vd.retenirActivites(regle);
        assertEquals(activiteRetenues.size(), 2);
        assertEquals(activiteRetenues.get(0).getCategorie(), "cours");
        assertEquals(activiteRetenues.get(1).getCategorie(), "atelier");

    }

    @Test
    public void testObtenirNbHeursGeo() {
        ValidationDeclaration validationDeclaration = new ValidationDeclaration(declarationGeo);
        assertEquals(validationDeclaration.obtenirNbHeurs(), 17);
    }

    @Test
    public void testObtenirNbHeursArch() {
        ValidationDeclaration validationDeclaration = new ValidationDeclaration(declarationArch);
        assertEquals(validationDeclaration.obtenirNbHeurs(), 24);
    }

    @Test
    public void testObtenirNbHeursArchHrTransf() {
        declarationArch.setHeuresTransfereesCyclePrecedent(1);
        ValidationDeclaration validationDeclaration = new ValidationDeclaration(declarationArch);
        assertEquals(validationDeclaration.obtenirNbHeurs(), 18);
    }

    @Test
    public void testMessageCycleNonReconnu() {
        ValidationDeclaration validationDeclaration = new ValidationDeclaration(declarationInvalide);
        System.out.println(" validationDeclaration.message()=" + validationDeclaration.message());
        assertTrue(validationDeclaration.message().contains("Le cycle n'est pas reconnu."));
    }

    @Test
    public void testMessageCategorieInvalid() {
        ValidationDeclaration validationDeclaration = new ValidationDeclaration(declarationArch);
        assertTrue(validationDeclaration.message().contains("L'activité CategorieInvalide n'a pas été retenue."));
    }
}
