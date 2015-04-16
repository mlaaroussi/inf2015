package formationcontinue;

import java.util.ArrayList;
import java.util.List;

public class ValidationGroupeActivite implements Validation {

    private final static String MSG = "Il manque %HEURES% heures dans la/les catégorie(s) suivante(s) : %CAT%.";

    private List<Activite> activites = new ArrayList<>();
    private List<String> infoCategories = new ArrayList<>();

    private int heureMax = 0; //Si = 0, pas de maximum.
    private int heureMin = 0;
    private int heureSupplemantaire = 0;

    public List<String> getInfoCategories() {
        return infoCategories;
    }

    public void setInfoCategories(List<String> infoCategories) {
        this.infoCategories = infoCategories;
    }

    public void setHeureSupplemantaire(int heureSupplemantaire) {
        this.heureSupplemantaire = heureSupplemantaire;
    }

    public void setHeureMax(int heure_max) {
        this.heureMax = heure_max;
    }

    public void setHeureMin(int heure_min) {
        this.heureMin = heure_min;
    }

    public void ajouterActivite(Activite activite) {
        this.activites.add(activite);
    }

    public void ajouterActivites(List<Activite> activites) {
        this.activites.addAll(activites);
    }

    protected int nbHeureTotal() {
        int nbHeure = heureSupplemantaire;

        for (Activite a : activites) {
            nbHeure += a.getHeures();
        }

        return nbHeure;
    }

    @Override
    public List<String> message() {
        List<String> msgs = new ArrayList<String>();

        if (!estValide()) {
            msgs.add(MSG.replace("%CAT%", getInfoCategories().toString()).replace("%HEURES%", "" + (heureMin - obtenirNbHeurs())));
        }

        return msgs;
    }

    @Override
    public int obtenirNbHeurs() {
        int nbHeure = nbHeureTotal();

        if (nbHeure > heureMax && heureMax != 0) {
            nbHeure = heureMax;
        }

        return nbHeure;
    }

    @Override
    public boolean estValide() {
        return obtenirNbHeurs() >= heureMin;
    }
}
