package generateurJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Declaration {

    public JSONObject getDeclarationJSON_EntreValide() {

        JSONArray activiteArray = new JSONArray();
        JSONObject jsonActivities = new JSONObject();
        jsonActivities.accumulate("description", "Cours sur la déontologie");
        jsonActivities.accumulate("categorie", "cours");
        jsonActivities.accumulate("heures", "14");
        jsonActivities.accumulate("date", "2013-03-20");
        activiteArray.add(jsonActivities);

        JSONObject declarationJSON_EntreValide = new JSONObject();
        declarationJSON_EntreValide.accumulate("nom", "nomBidon");
        declarationJSON_EntreValide.accumulate("prenom", "prenomBidon");
        declarationJSON_EntreValide.accumulate("sexe", "1");
        declarationJSON_EntreValide.accumulate("ordre", "architectes");
        declarationJSON_EntreValide.accumulate("numero_de_permis", "T1234");
        declarationJSON_EntreValide.accumulate("cycle", "2011-2013");
        declarationJSON_EntreValide.accumulate("activites", activiteArray);

        return declarationJSON_EntreValide;
    }

}
