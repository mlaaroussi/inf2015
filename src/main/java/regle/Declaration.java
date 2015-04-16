package regle;

import formationcontinue.Activite;
import java.util.Arrays;
import java.util.List;

public interface Declaration {

    List<Cycle> getReglesCycle();
    List<Categories> getReglesCategories();
    boolean contientHeuresTransferees();
    Categories RegleCategoriesHeuresTransferees();
    boolean possedeNumeroDePermisValide(formationcontinue.Declaration declaration);
    int NbHeuresMinCycles();

    static boolean estSexeValide(String sexe) {
        return sexe.matches("[0-2]");
    }

    static boolean estOrdreValide(String ordre) {
        return Arrays.asList(formationcontinue.Declaration.ORDRES_VALIDE).contains(ordre);
    }

    static Declaration getRegle(String ordre) {
        Declaration regles = null;

        switch (ordre) {
            case "architectes":
                regles = new Architectes();
                break;
            case "géologues":
                regles = new Geologues();
                break;
            case "psychologues":
                regles = new Psychologues();
                break;
            case "podiatres":
                regles = new Podiatres();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return regles;
    }

    static boolean nbHeureValide(List<Activite> activites) {
        boolean valide = true;

        for (formationcontinue.Activite activite : activites) {
            valide = valide && activite.getHeures() > 0;
        }

        return valide;
    }

    static boolean longueurDescriptionValide(List<Activite> activites) {
        boolean valide = true;

        for (formationcontinue.Activite activite : activites) {
            valide = valide && activite.getDescription().length() > 20;
        }

        return valide;
    }

    static String erreurDeclaration(formationcontinue.Declaration declaration) {
        String erreur = "";

        Declaration regle;

        if (estOrdreValide(declaration.getOrdre())) {
            regle = getRegle(declaration.getOrdre());

            if (!regle.possedeNumeroDePermisValide(declaration)) {
                erreur = "Le numéro de permis est invalide";
            } else if (!nbHeureValide(declaration.getActivities())) {
                erreur = "Le nb. d'heure d'une activité doit etre supérieur a 0.";
            } else if (!longueurDescriptionValide(declaration.getActivities())) {
                erreur = "La description d'une activité doit avoir plus de 20 caractere.";
            }
        } else {
            erreur = "L'ordre n'est pas valide.";
        }

        return erreur;
    }

    static boolean estDeclarationValide(formationcontinue.Declaration declaration) {
        return erreurDeclaration(declaration).isEmpty();
    }
}
