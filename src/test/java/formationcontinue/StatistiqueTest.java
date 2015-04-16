package formationcontinue;

import fakeDeclaration.ArchValideEtComplet;
import fakeDeclaration.GeoValideEtComplet;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatistiqueTest {

    Declaration decArch;
    Declaration decGeo;

    public StatistiqueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        decGeo = new GeoValideEtComplet();
        decArch = new ArchValideEtComplet();
        Statistique.reinitialiser();
    }

    @After
    public void tearDown() {
        decGeo = null;
        decArch = null;
    }

    @Test
    public void testAjouterDeclarationComplet() {
        Statistique.ajouterDeclarationComplet(decArch);
        Statistique.ajouterDeclarationComplet(decArch);
        assertEquals(Statistique.getNbDeclarationsCompletes(), 2);

    }

    @Test
    public void testAjouterDeclaratioImcomplete() {
        Statistique.ajouterDeclaratioIncomplete(decGeo);
        Statistique.ajouterDeclaratioIncomplete(decArch);

        assertEquals(Statistique.getNbDeclarationsIncompletes(), 2);
    }

    @Test
    public void testAjouterActiviteValide() {
        Statistique.ajouterActiviteValide(new fakeActivite.Cours20141202());
        Statistique.ajouterActiviteValide(new fakeActivite.Cours20141202());
        Statistique.ajouterActiviteValide(new fakeActivite.Atelier2030());

        assertEquals(2, Statistique.getNbActivitesValides("cours"));
        assertEquals(1, Statistique.getNbActivitesValides("atelier"));
        assertEquals(0, Statistique.getNbActivitesValides("autre"));
    }

    @Test
    public void testGetNbDeclarationsIncompletes() {
        Statistique.ajouterDeclaratioIncomplete(decArch);
        Statistique.ajouterDeclaratioIncomplete(decArch);

        assertEquals(Statistique.getNbDeclarationsIncompletes(), 2);
    }

    @Test
    public void testGetNbDeclarationsHommes() {

        Statistique.ajouterDeclarationComplet(decGeo);
        Statistique.ajouterDeclarationComplet(decGeo);

        assertEquals(Statistique.getNbDeclarationsHommes(), 2);
        assertEquals(Statistique.getNbDeclarationsFemmes(), 0);
    }

    @Test
    public void testGetNbDeclarationsFemmes() {
        Statistique.ajouterDeclarationComplet(decGeo);

        assertEquals(Statistique.getNbDeclarationsFemmes(), 0);
    }

    @Test
    public void testGetNbDeclarationsSexeInconnu() {
        Statistique.ajouterDeclarationComplet(decArch);
        Statistique.ajouterDeclarationComplet(decArch);

        assertEquals(Statistique.getNbDeclarationsSexeInconnu(), 2);
    }

    @Test
    public void testCharger() {
        Statistique.ajouterDeclarationComplet(decArch);
        Statistique.ajouterDeclarationComplet(decGeo);
        Statistique.ajouterDeclaratioIncomplete(decArch);
        
        String infoStatistiques = Statistique.infoStatistiques();

        JSONObject JsonStatistique = Statistique.STATISTIQUE.creerJsonStatistique();
        Statistique.reinitialiser();
        Statistique.STATISTIQUE.charger(JsonStatistique);
        
        assertEquals(infoStatistiques, Statistique.infoStatistiques());
    }

    @Test
    public void testInfoStatistiques() {
        Statistique.ajouterDeclarationComplet(decArch);
        Statistique.ajouterDeclarationComplet(decGeo);
        Statistique.ajouterDeclaratioIncomplete(decArch);

        Statistique.ajouterActiviteValide(new fakeActivite.ActiviteSeminaire20140104());

        assertEquals(Statistique.infoStatistiques(),
                "Le nombre total de déclarations traitées : 3\n"
                + "Le nombre total de déclarations complètes : 2\n"
                + "Le nombre total de déclarations incomplètes ou invalides : 1\n"
                + "Le nombre total de déclarations faites par des hommes : 1\n"
                + "Le nombre total de déclarations faites par des femmes : 0\n"
                + "Le nombre total de déclarations faites par des gens de sexe inconnu : 2\n"
                + "Le nombre total d'activités valides dans les déclarations : 1\n"
                + "Le nombre d'activités valides pour les catégories : \n"
                + "    cours : 0\n"
                + "    atelier : 0\n"
                + "    séminaire : 1\n"
                + "    colloque : 0\n"
                + "    conférence : 0\n"
                + "    lecture dirigée : 0\n"
                + "    présentation : 0\n"
                + "    groupe de discussion : 0\n"
                + "    projet de recherche : 0\n"
                + "    rédaction professionnelle : 0\n"
                + "Le nombre total de déclarations valides et complètes pour ordre : \n"
                + "    architectes : 1\n"
                + "    géologues : 0\n"
                + "    psychologues : 1\n"
                + "    podiatres : 0\n"
                + "Le nombre total de déclarations valides et incomplètes pour ordre : \n"
                + "    architectes : 1\n"
                + "    géologues : 0\n"
                + "    psychologues : 0\n"
                + "    podiatres : 0\n"
                + "Le nombre de déclarations soumises avec un numéro de permis invalide : 0\n"
        );
    }

}
