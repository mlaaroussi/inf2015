package formationcontinue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

class Resultat {

    private static String ficResultat;
    private static List<String> erreurs = new ArrayList<>();

    private static boolean estComplet = false;

    public static void reinitialiser() {
        erreurs = new ArrayList<>();
        estComplet = false;
    }

    public static void ajouterMessage(String message) {
        erreurs.add(message);
    }

    public static void ajouterMessage(List<String> messages) {
        erreurs.addAll(messages);
    }

    public static List<String> getMessages() {
        return erreurs;
    }

    public static void setEstComplet(boolean estComplet) {
        Resultat.estComplet = estComplet;
    }

    public static void setFicResultat(String fic) {
        ficResultat = fic;
    }

    protected static JSONObject creerJsonResultat() {
        JSONObject jsonResultat = new JSONObject();

        jsonResultat.accumulate("complet", estComplet);

        JSONArray erreursTable = new JSONArray();
        erreursTable.addAll(erreurs);

        jsonResultat.accumulate("erreurs", erreursTable);

        return jsonResultat;
    }

    public static void enregistrer() throws IOException {
        FileWriter.writeFileIntoString(creerJsonResultat().toString(2), ficResultat, "UTF-8");
    }
}
