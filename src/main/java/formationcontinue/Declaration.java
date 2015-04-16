package formationcontinue;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Declaration {

    public static final String[] ORDRES_VALIDE = {
        "architectes",
        "géologues",
        "psychologues",
        "podiatres"};

    private String nom;
    private String prenom;
    private int sexe;
    private String ordre;
    private String numeroDePermis;
    private String cycle;
    private int heuresTransfereesCyclePrecedent;
    private boolean champHeuresTransfereesCyclePrecedentPresent;
    private List<Activite> activities = new ArrayList<>();

    public String getNumeroDePermis() {
        return numeroDePermis;
    }

    public void setNumeroDePermis(String numeroDePermis) {
        this.numeroDePermis = numeroDePermis;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public int getHeuresTransfereesCyclePrecedent() {
        return heuresTransfereesCyclePrecedent;
    }

    public void setHeuresTransfereesCyclePrecedent(int heuresTransfereesCycle_Precedent) {
        this.heuresTransfereesCyclePrecedent = heuresTransfereesCycle_Precedent;
    }

    public boolean isChampHeuresTransfereesCyclePrecedentPresent() {
        return champHeuresTransfereesCyclePrecedentPresent;
    }

    public void setChampHeuresTransfereesCyclePrecedentPresent(boolean champHeuresTransfereesCyclePrecedentPresent) {
        this.champHeuresTransfereesCyclePrecedentPresent = champHeuresTransfereesCyclePrecedentPresent;
    }

    public List<Activite> getActivities() {
        return activities;
    }

    public void setActivities(List<Activite> activities) {
        this.activities = (ArrayList<Activite>) activities;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public static Declaration getFromJSON(JSONObject cycleDeFormation) {
        Declaration declaration = new Declaration();

        declaration.setNom(cycleDeFormation.getString("nom"));
        declaration.setPrenom(cycleDeFormation.getString("prenom"));
        declaration.setSexe(cycleDeFormation.getInt("sexe"));
        declaration.setOrdre(cycleDeFormation.getString("ordre"));
        declaration.setNumeroDePermis(cycleDeFormation.getString("numero_de_permis"));
        declaration.setCycle(cycleDeFormation.getString("cycle"));
        declaration.setChampHeuresTransfereesCyclePrecedentPresent(cycleDeFormation.has("heures_transferees_du_cycle_precedent"));

        declaration.setHeuresTransfereesCyclePrecedent(getHeuresTransfereesFromJSON(cycleDeFormation));

        declaration.setActivities(getActivitesFromJSON(cycleDeFormation.getJSONArray("activites")));

        return declaration;
    }

    private static int getHeuresTransfereesFromJSON(JSONObject cycleDeFormation) {
        if (cycleDeFormation.has("heures_transferees_du_cycle_precedent")) {
            return cycleDeFormation.getInt("heures_transferees_du_cycle_precedent");
        }

        return 0;
    }

    private static List<Activite> getActivitesFromJSON(JSONArray tabActivities) {
        List<Activite> activites = new ArrayList<>();

        for (int i = 0; i < tabActivities.size(); i++) {
            JSONObject uneActivite = tabActivities.getJSONObject(i);
            Activite activite = Activite.getFromJSON(uneActivite);

            activites.add(activite);
        }

        return activites;
    }
}
