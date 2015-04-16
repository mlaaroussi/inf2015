package formationcontinue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;

public class Activite {

    public static final String[] CATEGORIES_RECONNUES = {
        "cours", "atelier", "séminaire", "colloque",
        "conférence", "lecture dirigée", "présentation",
        "groupe de discussion", "projet de recherche",
        "rédaction professionnelle"};

    private String description;
    private String categorie;
    private int heures;
    private Date date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    static Activite getFromJSON(JSONObject activiteJSON) {
        Activite activite = new Activite();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);

        activite.setDescription(activiteJSON.getString("description"));
        activite.setCategorie(activiteJSON.getString("categorie"));
        activite.setHeures(activiteJSON.getInt("heures"));
        activite.setDate(DateUtil.getDate(activiteJSON.getString("date")));

        return activite;
    }
}
