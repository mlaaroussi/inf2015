package formationcontinue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationDeclaration implements Validation {

    List<Validation> validationActivite = new ArrayList<>();
    private List<String> msg = new ArrayList<>();
    private Declaration cycle;
    private regle.Declaration regleDeclaration;

    private final static String MSG_CYCLE_NON_VALIDE = "Le cycle n'est pas reconnu.";
    private final static String MSG_ATCIVITE_NON_RETENUE = "L'activité %DESC% n'a pas été retenue.";
    private final static String MSG_NBR_MIN_HEURES_DECLARE_PAS_ATTEINT = "Il manque %HH% heures de formation pour compléter le cycle.";
    private final static String MSG_NBR_HEURES_TRANSFEREES_PLUS_DE_7 = "Plus de 7 heures ont été déclarées dans les heures transférées du cycle précédent.";

    public ValidationDeclaration() {
     
    }

    public ValidationDeclaration(Declaration cycle) {
        this.cycle = cycle;
        this.regleDeclaration = regle.Declaration.getRegle(cycle.getOrdre());

        if (estCycleValide()) {
            validationActivite();

            if (obtenirNbHeurs() < regleDeclaration.NbHeuresMinCycles()) {
                msg.add(MSG_NBR_MIN_HEURES_DECLARE_PAS_ATTEINT.replace("%HH%",
                        String.valueOf(regleDeclaration.NbHeuresMinCycles() - obtenirNbHeurs())));
                Statistique.ajouterDeclaratioIncomplete(cycle);
            } else {
                Statistique.ajouterDeclarationComplet(cycle);
            }
        } else {
            Statistique.ajouterDeclaratioInvalide(cycle);
        }
    }

    protected void validationActivite() {

        List<Activite> activteesValidees = retenirActivites(obtenirGroupeCycle());
        
        for(regle.Categories regleCat : regleDeclaration.getReglesCategories()) {
            validationActivite.add(regleCat.creerValidation(activteesValidees));
        }
        
        if(regleDeclaration.contientHeuresTransferees()) {
            validationActivite.add(regleDeclaration.RegleCategoriesHeuresTransferees().creerValidation(activteesValidees, CalculHeureTransferees(cycle)));
        }
    }

    protected List<Activite> retenirActivites(regle.Cycle grCycle) {
        List<Activite> listRslt = new ArrayList<>();
        
        for (Activite activite : cycle.getActivities()) {
            if (grCycle.estDateValide(activite.getDate()) && categoriePourActiviteValide(activite)) {
                listRslt.add(activite);
                Statistique.ajouterActiviteValide(activite);
            } else {
                msg.add(MSG_ATCIVITE_NON_RETENUE.replace("%DESC%", activite.getDescription()));
            }
        }

        return listRslt;
    }
    
    public boolean categoriePourActiviteValide(Activite activite) {
        return Arrays.asList(Activite.CATEGORIES_RECONNUES).contains(activite.getCategorie());
    }

    protected int CalculHeureTransferees(Declaration cycle) {
        int nbHeures;

        if (cycle.getHeuresTransfereesCyclePrecedent() > 7) {
            nbHeures = 7;
            msg.add(MSG_NBR_HEURES_TRANSFEREES_PLUS_DE_7);
        } else {
            nbHeures = cycle.getHeuresTransfereesCyclePrecedent();
        }

        return nbHeures;
    }

    protected boolean estCycleValide() {

        boolean rslt = false;
        
        for(regle.Cycle regleCycle : regleDeclaration.getReglesCycle()) {
            rslt = rslt || cycle.getCycle().equals(regleCycle.getNomDuCycle());
        }
        
        if(!rslt) {
            msg.add(MSG_CYCLE_NON_VALIDE);
        }

        return rslt;
    }

    protected regle.Cycle obtenirGroupeCycle() {
        for (regle.Cycle gc : regleDeclaration.getReglesCycle()) {
            if (cycle.getCycle().equals(gc.getNomDuCycle())) {
                return gc;
            }
        }
        return null;
    }

    @Override
    public List<String> message() {
        List<String> rslt = new ArrayList<>();

        for (Validation validation : validationActivite) {
            rslt.addAll(validation.message());
        }

        rslt.addAll(msg);

        return rslt;
    }

    @Override
    public int obtenirNbHeurs() {
        int nbHeureTotal = 0;

        for (Validation validation : validationActivite) {
            nbHeureTotal += validation.obtenirNbHeurs();
        }
        return nbHeureTotal;
    }

    @Override
    public boolean estValide() {

        boolean rslt = obtenirNbHeurs() >= regleDeclaration.NbHeuresMinCycles();
        for (Validation validation : validationActivite) {
            rslt = rslt && validation.estValide();
        }
        return rslt;
    }

}
