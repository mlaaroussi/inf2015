package regle;

import formationcontinue.Activite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CategoriesTest {

    public CategoriesTest() {
    }

    formationcontinue.Declaration declarationArch;

    @Before
    public void setUp() {
        declarationArch = new fakeDeclaration.ArchValideEtComplet();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testExtraireActivites() {
        Categories regleCat = new Categories(17, 0, new String[]{"atelier", "séminaire"});

        for (Activite activite : regleCat.extraireActivites(declarationArch.getActivities())) {
            assertTrue(activite.getCategorie().equals("atelier") || activite.getCategorie().equals("séminaire"));
        }
    }

}
