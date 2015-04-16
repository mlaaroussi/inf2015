package formationcontinue;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResultatTest {
    
    public ResultatTest() {
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
        Resultat.reinitialiser();
    }

    @Test
    public void testCreerJsonResultat() {
        
        Resultat.ajouterMessage("test...");
        Resultat.setEstComplet(true);
        
        JSONObject jsonResultat = new JSONObject();
        jsonResultat.accumulate("complet", true);
        JSONArray erreursTable = new JSONArray();
        erreursTable.add("test...");
        jsonResultat.accumulate("erreurs", erreursTable);
        
        
        assertEquals(jsonResultat, Resultat.creerJsonResultat());
    }
    
    @Test
    public void testAjouterMessage() {
        List<String> messages = new ArrayList<>();
        messages.add("test1");
        messages.add("test2");
        messages.add("test3");
        
        Resultat.ajouterMessage(messages);        
        
        assertEquals(messages, Resultat.getMessages());
    }
    
    
}
