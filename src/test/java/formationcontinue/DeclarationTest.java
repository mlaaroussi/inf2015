package formationcontinue;

import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeclarationTest {

    generateurJson.Declaration declarationJSON_EntreValide;
    JSONObject declarationJSON_EntreInvalide;
    JSONObject declarationJSON_EntreActivite;
    generateurJson.Declaration declarationSortie;

    Activite activiteSortie;

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
        declarationJSON_EntreValide = new generateurJson.Declaration();
    }

    @After
    public void tearDown() {
        declarationJSON_EntreValide = null;
        declarationJSON_EntreInvalide = null;
        declarationSortie = null;
    }

    @Test
    public void testGetFromJSON() {
        Declaration result = Declaration.getFromJSON(declarationJSON_EntreValide.getDeclarationJSON_EntreValide());
        assertEquals("nomBidon", result.getNom());
        assertEquals("prenomBidon", result.getPrenom());
        assertEquals(1, result.getSexe());
        assertEquals("T1234", result.getNumeroDePermis());
        assertEquals("2011-2013", result.getCycle());
    }

}
