package formationcontinue;

import java.io.IOException;
import java.text.ParseException;
import net.sf.json.JSONObject;

public class FormationContinue {

    public static void main(String[] args) throws IOException, ParseException {
        Statistique.initialiser();

        if (args.length == 2) {
            declaration(args[0], args[1]);
        } else if (args.length == 1 && args[0].equals("-S")) {
            System.out.println(Statistique.infoStatistiques());
        } else if (args.length == 1 && args[0].equals("-SR")) {
            Statistique.reinitialiser();
            System.out.println("Les statistique ont été reinitialisés");
        } else {
            System.out.println("L'option est erroné!");
        }

        Statistique.enregistrer();
    }

    private static void declaration(String ficDeclaration, String ficResultat) throws IOException {
        Resultat.setFicResultat(ficResultat);

        try {
            traiterDeclaration(obtenirDeclaration(ficDeclaration));
        } catch (Exception e) {
            declarationInvalide("le fichier d'entrée est invalide.");
        }

        Resultat.enregistrer();
    }

    public static Declaration obtenirDeclaration(String ficDeclaration) throws IOException, ParseException {
        String jsonCycleDeFormation = FileReader.loadFileIntoString(ficDeclaration, "UTF-8");
        JSONObject cycleDeFormation = JSONObject.fromObject(jsonCycleDeFormation);

        Declaration declaration = Declaration.getFromJSON(cycleDeFormation);

        return declaration;
    }

    private static void traiterDeclaration(Declaration declaration) {
        if (regle.Declaration.estDeclarationValide(declaration)) {
            ValidationDeclaration validationDeclaration = new ValidationDeclaration(declaration);
            Resultat.ajouterMessage(validationDeclaration.message());
            Resultat.setEstComplet(validationDeclaration.estValide());
        } else {
            Statistique.ajouterDeclaratioInvalide(declaration);
            declarationInvalide(regle.Declaration.erreurDeclaration(declaration));
        }
    }

    private static void declarationInvalide(String erreur) {
        Resultat.setEstComplet(false);
        Resultat.ajouterMessage(erreur);
        System.out.println(erreur);
    }

}
