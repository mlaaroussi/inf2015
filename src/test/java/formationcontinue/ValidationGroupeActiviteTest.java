package formationcontinue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationGroupeActiviteTest {

    Activite uneActivite;

    public ValidationGroupeActiviteTest() {
    }

    ValidationGroupeActivite validationGroupeActivite;

    @Before
    public void setUp() {
        validationGroupeActivite = new ValidationGroupeActivite();
        validationGroupeActivite.ajouterActivite(new fakeActivite.Cours20141202());
        validationGroupeActivite.ajouterActivite(new fakeActivite.Cours20141202());
        validationGroupeActivite.ajouterActivite(new fakeActivite.Cours20141202());
    }

    @After
    public void tearDown() {
        validationGroupeActivite = null;
    }

    @Test
    public void testNbHeureTotal() {
        assertEquals(36, validationGroupeActivite.nbHeureTotal());
    }

    @Test
    public void testObtenirNbHeurs() {
        assertEquals(36, validationGroupeActivite.obtenirNbHeurs());
        validationGroupeActivite.setHeureMax(5);
        assertEquals(5, validationGroupeActivite.obtenirNbHeurs());
    }

    @Test
    public void testMessage() {
        assertEquals(0, validationGroupeActivite.message().size());
        validationGroupeActivite.setHeureMin(36);
        assertEquals(0, validationGroupeActivite.message().size());
        validationGroupeActivite.setHeureMin(37);
        assertEquals(1, validationGroupeActivite.message().size());
    }

}
