package regle;

import formationcontinue.Activite;
import formationcontinue.Validation;
import formationcontinue.ValidationGroupeActivite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Categories {

    int min;
    int max;
    String[] categories;

    public Categories(int min, int max, String[] categories) {
        this.min = min;
        this.max = max;
        this.categories = categories;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String[] getCategories() {
        return categories;
    }

    public Validation creerValidation(List<Activite> activites, int hSup) {
        List<Activite> listActivites = extraireActivites(activites);
        ValidationGroupeActivite groupe = new ValidationGroupeActivite();

        groupe.setHeureSupplemantaire(hSup);
        groupe.setHeureMax(getMax());
        groupe.setHeureMin(getMin());
        groupe.setInfoCategories(Arrays.asList(categories));

        groupe.ajouterActivites(listActivites);

        return groupe;
    }

    public Validation creerValidation(List<Activite> activites) {
        return creerValidation(activites, 0);
    }

    public List<Activite> extraireActivites(List<Activite> activites) {
        List<Activite> groupe = new ArrayList<>();

        for (Activite a : activites) {
            if (Arrays.asList(categories).contains(a.getCategorie())) {
                groupe.add(a);
            }
        }

        return groupe;
    }
}
