package formationcontinue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

public class Statistique {

    private static final String FIC_STATISTIQUE = "statistique.json";
    protected static final Statistique STATISTIQUE = new Statistique();
    private final static int INCONNU = 0;
    private final static int HOMME = 1;
    private final static int FEMME = 2;

    private int nbDeclarationsCompletes;

    private int nbDeclarationsIncompletesOuInvalide;
    private int nbDeclarationsHommes;
    private int nbDeclarationsFemmes;
    private int nbDeclarationsSexeInconnu;
    private Map<String, Integer> nbDeclarationsOrdreComplet = new HashMap<>();
    private Map<String, Integer> nbDeclarationsOrdreIncomplet = new HashMap<>();
    private int nbDeclarationsNumeroInvalide;

    private Map<String, Integer> nbActivites = new HashMap<>();

    protected Statistique() {
    }

    private void statiqueFromFile() {
        try {
            JSONObject jsonStatistique = getJsonStatistique();
            charger(jsonStatistique);
        } catch (Exception e) {
            nouvelleStatistique();
        }
    }

    public void nouvelleStatistique() {
        nbDeclarationsCompletes = 0;
        nbDeclarationsIncompletesOuInvalide = 0;
        nbDeclarationsHommes = 0;
        nbDeclarationsFemmes = 0;
        nbDeclarationsSexeInconnu = 0;
        nbActivites = new HashMap<>();
        nbDeclarationsOrdreComplet = new HashMap<>();
        nbDeclarationsOrdreIncomplet = new HashMap<>();
        nbDeclarationsNumeroInvalide = 0;
    }

    private JSONObject getJsonStatistique() throws IOException {
        String jsonStatistique = FileReader.loadFileIntoString(FIC_STATISTIQUE, "UTF-8");
        return JSONObject.fromObject(jsonStatistique);
    }

    protected void charger(JSONObject jsonStatistique) {
        nbDeclarationsCompletes = jsonStatistique.getInt("declaration_completes");
        nbDeclarationsIncompletesOuInvalide = jsonStatistique.getInt("declaration_incompletes_ou_invalide");
        nbDeclarationsHommes = jsonStatistique.getInt("declaration_hommes");
        nbDeclarationsFemmes = jsonStatistique.getInt("declaration_femmes");
        nbDeclarationsSexeInconnu = jsonStatistique.getInt("declaration_sexe_inconnu");

        initialiserMap(nbActivites, jsonStatistique.getJSONObject("activites"));
        initialiserMap(nbDeclarationsOrdreComplet, jsonStatistique.getJSONObject("declaratiion_ordre_valide_complete"));
        initialiserMap(nbDeclarationsOrdreIncomplet, jsonStatistique.getJSONObject("declaratiion_ordre_valide_incomplete"));
        nbDeclarationsNumeroInvalide = jsonStatistique.getInt("declaration_numero_invalide");
    }

    private void initialiserMap(Map<String, Integer> var, JSONObject json) {
        for (Object s : json.keySet()) {
            var.put(s.toString(), json.getInt(s.toString()));
        }
    }

    protected JSONObject creerJsonStatistique() {
        JSONObject jsonStatistique = new JSONObject();
        jsonStatistique.accumulate("declaration_completes", nbDeclarationsCompletes);
        jsonStatistique.accumulate("declaration_incompletes_ou_invalide", nbDeclarationsIncompletesOuInvalide);
        jsonStatistique.accumulate("declaration_hommes", nbDeclarationsHommes);
        jsonStatistique.accumulate("declaration_femmes", nbDeclarationsFemmes);
        jsonStatistique.accumulate("declaration_sexe_inconnu", nbDeclarationsSexeInconnu);

        jsonStatistique.accumulate("activites", creerJsonMap(nbActivites));
        jsonStatistique.accumulate("declaratiion_ordre_valide_complete", creerJsonMap(nbDeclarationsOrdreComplet));
        jsonStatistique.accumulate("declaratiion_ordre_valide_incomplete", creerJsonMap(nbDeclarationsOrdreIncomplet));
        jsonStatistique.accumulate("declaration_numero_invalide", nbDeclarationsNumeroInvalide);
        return jsonStatistique;
    }

    private JSONObject creerJsonMap(Map<String, Integer> var) {
        JSONObject jsonActivites = new JSONObject();

        for (Map.Entry<String, Integer> entry : var.entrySet()) {
            jsonActivites.accumulate(entry.getKey(), entry.getValue());
        }

        return jsonActivites;
    }

    public static void initialiser() {
        STATISTIQUE.statiqueFromFile();
    }

    public static void ajouterDeclarationComplet(Declaration declaration) {

        STATISTIQUE.nbDeclarationsCompletes++;

        switch (declaration.getSexe()) {
            case INCONNU:
                STATISTIQUE.nbDeclarationsSexeInconnu++;
                break;
            case HOMME:
                STATISTIQUE.nbDeclarationsHommes++;
                break;
            case FEMME:
                STATISTIQUE.nbDeclarationsFemmes++;
                break;

        }

        int valeur
                = STATISTIQUE.nbDeclarationsOrdreComplet.containsKey(declaration.getOrdre())
                        ? STATISTIQUE.nbDeclarationsOrdreComplet.get(declaration.getOrdre())
                        : 0;

        STATISTIQUE.nbDeclarationsOrdreComplet.put(declaration.getOrdre(), valeur + 1);

    }

    public static void ajouterDeclaratioIncomplete(Declaration declaration) {
        STATISTIQUE.nbDeclarationsIncompletesOuInvalide++;
        switch (declaration.getSexe()) {
            case INCONNU:
                STATISTIQUE.nbDeclarationsSexeInconnu++;
                break;
            case HOMME:
                STATISTIQUE.nbDeclarationsHommes++;
                break;
            case FEMME:
                STATISTIQUE.nbDeclarationsFemmes++;
                break;

        }

        int valeur
                = STATISTIQUE.nbDeclarationsOrdreIncomplet.containsKey(declaration.getOrdre())
                        ? STATISTIQUE.nbDeclarationsOrdreIncomplet.get(declaration.getOrdre())
                        : 0;

        STATISTIQUE.nbDeclarationsOrdreIncomplet.put(declaration.getOrdre(), valeur + 1);
    }

    public static void ajouterDeclaratioInvalide(Declaration declaration) {
        if (declaration != null && regle.Declaration.estOrdreValide(declaration.getOrdre())
                && !regle.Declaration.getRegle(declaration.getOrdre()).possedeNumeroDePermisValide(declaration)) {
            STATISTIQUE.nbDeclarationsNumeroInvalide++;
        }

        STATISTIQUE.nbDeclarationsIncompletesOuInvalide++;
    }

    public static void ajouterActiviteValide(Activite activite) {

        STATISTIQUE.nbActivites.put(activite.getCategorie(), Statistique.getNbActivitesValides(activite.getCategorie()) + 1);

    }

    public static void reinitialiser() {
        STATISTIQUE.nouvelleStatistique();
    }

    public static void enregistrer() throws IOException {
        FileWriter.writeFileIntoString(STATISTIQUE.creerJsonStatistique().toString(2), FIC_STATISTIQUE, "UTF-8");
    }

    public static int getNbDeclarationsTraitees() {
        return STATISTIQUE.nbDeclarationsCompletes
                + STATISTIQUE.nbDeclarationsIncompletesOuInvalide;
    }

    public static int getNbDeclarationsCompletes() {
        return STATISTIQUE.nbDeclarationsCompletes;
    }

    public static int getNbDeclarationsIncompletes() {
        return STATISTIQUE.nbDeclarationsIncompletesOuInvalide;
    }

    public static int getNbDeclarationsHommes() {
        return STATISTIQUE.nbDeclarationsHommes;
    }

    public static int getNbDeclarationsFemmes() {
        return STATISTIQUE.nbDeclarationsFemmes;
    }

    public static int getNbDeclarationsSexeInconnu() {
        return STATISTIQUE.nbDeclarationsSexeInconnu;
    }

    public static int getNbDeclarationsNumeroInvalide() {
        return STATISTIQUE.nbDeclarationsNumeroInvalide;
    }

    public static int getNbActivitesValides() {
        int rslt = 0;
        for (Map.Entry<String, Integer> entree : STATISTIQUE.nbActivites.entrySet()) {
            rslt += entree.getValue();
        }
        return rslt;
    }

    public static int getNbActivitesValides(String categorie) {
        Integer nbActivite = STATISTIQUE.nbActivites.get(categorie);

        if (nbActivite != null) {
            return nbActivite;
        } else {
            return 0;
        }
    }

    public static int getNbDeclarationsOrdreComplet(String ordre) {
        Integer nbDeclaration = STATISTIQUE.nbDeclarationsOrdreComplet.get(ordre);

        if (nbDeclaration != null) {
            return nbDeclaration;
        } else {
            return 0;
        }
    }

    public static int getNbDeclarationsOrdreIncomplet(String ordre) {
        Integer nbDeclaration = STATISTIQUE.nbDeclarationsOrdreIncomplet.get(ordre);

        if (nbDeclaration != null) {
            return nbDeclaration;
        } else {
            return 0;
        }
    }

    public static String infoStatistiques() {

        StringBuilder strStats = new StringBuilder();

        strStats.append("Le nombre total de déclarations traitées : " + getNbDeclarationsTraitees() + "\n"
                + "Le nombre total de déclarations complètes : " + getNbDeclarationsCompletes() + "\n"
                + "Le nombre total de déclarations incomplètes ou invalides : " + getNbDeclarationsIncompletes() + "\n"
                + "Le nombre total de déclarations faites par des hommes : " + getNbDeclarationsHommes() + "\n"
                + "Le nombre total de déclarations faites par des femmes : " + getNbDeclarationsFemmes() + "\n"
                + "Le nombre total de déclarations faites par des gens de sexe inconnu : " + getNbDeclarationsSexeInconnu() + "\n"
                + "Le nombre total d'activités valides dans les déclarations : " + getNbActivitesValides() + "\n"
                + "Le nombre d'activités valides pour les catégories : \n");

        for (String categorie : Activite.CATEGORIES_RECONNUES) {
            strStats.append("    " + categorie + " : " + getNbActivitesValides(categorie) + "\n");
        }
        strStats.append("Le nombre total de déclarations valides et complètes pour ordre : \n");

        for (String ordre : Declaration.ORDRES_VALIDE) {
            strStats.append("    " + ordre + " : " + getNbDeclarationsOrdreComplet(ordre) + "\n");
        }

        strStats.append("Le nombre total de déclarations valides et incomplètes pour ordre : \n");

        for (String ordre : Declaration.ORDRES_VALIDE) {
            strStats.append("    " + ordre + " : " + getNbDeclarationsOrdreIncomplet(ordre) + "\n");
        }

        strStats.append("Le nombre de déclarations soumises avec un numéro de permis invalide : " + getNbDeclarationsNumeroInvalide() + "\n");

        return strStats.toString();
    }
}
