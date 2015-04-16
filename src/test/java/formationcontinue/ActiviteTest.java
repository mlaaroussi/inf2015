package formationcontinue;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActiviteTest {

    JSONObject activiteJSON_EntreValide;
    JSONObject activiteJSON_EntreInvalide;
    Activite activiteSortie;

    public ActiviteTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        activiteJSON_EntreValide = new JSONObject();
        activiteJSON_EntreValide.accumulate("description", "bidonDesc");
        activiteJSON_EntreValide.accumulate("categorie", "bidonCateg");
        activiteJSON_EntreValide.accumulate("heures", "12");
        activiteJSON_EntreValide.accumulate("date", "2014-01-25");

        activiteJSON_EntreInvalide = new JSONObject();
        activiteJSON_EntreInvalide.accumulate("description", "bidonDesc");

        activiteSortie = new Activite();
        activiteSortie.setDescription("bidonDesc");
        activiteSortie.setCategorie("bidonCateg");
        activiteSortie.setHeures(12);
        activiteSortie.setDate(DateUtil.getDate("2014-01-25"));
    }

    @After
    public void tearDown() {
        activiteJSON_EntreValide = null;
        activiteJSON_EntreInvalide = null;

    }

    @Test
    public void testGetFromJSON() {

        assertEquals(activiteSortie.getDescription(), Activite.getFromJSON(activiteJSON_EntreValide).getDescription());
        assertEquals(activiteSortie.getCategorie(), Activite.getFromJSON(activiteJSON_EntreValide).getCategorie());
        assertEquals(activiteSortie.getHeures(), Activite.getFromJSON(activiteJSON_EntreValide).getHeures());
        assertEquals(activiteSortie.getDate(), Activite.getFromJSON(activiteJSON_EntreValide).getDate());
    }

    @Test(expected = JSONException.class)
    public void testGetFromJSONVide() {

        Activite.getFromJSON(activiteJSON_EntreInvalide);

    }
}
