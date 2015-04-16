package formationcontinue;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FormationContinue {

    public static void main(String[] args) throws IOException, ParseException {
        if(args.length == 2){
            JSONObject resultat = obtenirJsonResultat(args[0]);
            enregistrerJSON(resultat, args[1]);
        }else if(args.length == 1 && args[0].equals("-S")){
             Statistique.aficherStatistiques();
        }else if (args.length == 1 && args[0].equals("-SR")){
           Statistique.reinitialiser();
           System.out.println("Les statistique ont été reinitialisés");
        }else{
            System.out.println("L'option est erroné!");
        }
        
         Statistique.enregistrer();
    }

    public static JSONObject obtenirJsonResultat(String ficDeclaration) {
        JSONObject resultat;
        try {
            Resultat rs;
            Declaration cycleTest = obtenirCycle(ficDeclaration);
            rs = new Resultat(cycleTest);
            resultat = rs.genererJSON();
        } catch (Exception e) {
            resultat = jsonFichierInvalide();
            System.out.println("Le fichier d'entrée est invalide.");
            Statistique.ajouterDeclaratioInvalide();
        }

        return resultat;
    }

    public static Declaration obtenirCycle(String ficDeclaration) throws IOException, ParseException {
        String jsonCycleDeFormation = FileReader.loadFileIntoString(ficDeclaration, "UTF-8");
        JSONObject cycleDeFormation = JSONObject.fromObject(jsonCycleDeFormation);
        
        Declaration declaration = Declaration.getFromJSON(cycleDeFormation);
        
        if(!formationcontinue.regle.Declaration.estDeclarationValide(declaration)) {
            throw new RuntimeException();
        }

        return declaration;
    }

    public static void enregistrerJSON(JSONObject jsonObjet, String fic) throws IOException {
        FileWriter pretty = new FileWriter(fic);
        pretty.write(jsonObjet.toString(2));
        pretty.close();
    }

    private static JSONObject jsonFichierInvalide() {
        JSONObject jsonObjet = new JSONObject();
        jsonObjet.accumulate("complet", false);

        JSONArray erreursTable = new JSONArray();
        erreursTable.add("le fichier d'entrée est invalide.");
        jsonObjet.accumulate("erreurs", erreursTable);

        return jsonObjet;
    }
    
}
