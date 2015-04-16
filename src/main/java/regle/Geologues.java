package regle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geologues implements Declaration {

    private static final List<Cycle> REGLES_CYCLE = new ArrayList<>(
            Arrays.asList(new Cycle("2013-2016", "2013-06-01", "2016-06-01")
            )
    );

    private static final Categories REGLE_CATEGORIES_HEURE_TRANSFERER
            = new Categories(0, 0, new String[]{});

    private static final List<Categories> REGLES_CATEGORIES = new ArrayList<>(
            Arrays.asList(new Categories(22, 0, new String[]{"cours"}),
                    new Categories(3, 0, new String[]{"projet de recherche"}),
                    new Categories(1, 0, new String[]{"groupe de discussion"}),
                    new Categories(0, 0, new String[]{
                        "atelier", "séminaire", "colloque",
                        "conférence", "lecture dirigée", "présentation",
                        "rédaction professionnelle"})
            )
    );

    @Override
    public List<Cycle> getReglesCycle() {
        return REGLES_CYCLE;
    }

    @Override
    public List<Categories> getReglesCategories() {
        return REGLES_CATEGORIES;
    }

    @Override
    public Categories RegleCategoriesHeuresTransferees() {
        return REGLE_CATEGORIES_HEURE_TRANSFERER;
    }

    @Override
    public boolean possedeNumeroDePermisValide(formationcontinue.Declaration declaration) {
        return declaration.getNumeroDePermis().matches("[A-Z]{2}\\d{4}")
                && declaration.getNom().length() > 0
                && declaration.getPrenom().length() > 0
                && declaration.getNom().toUpperCase().charAt(0) == declaration.getNumeroDePermis().charAt(0)
                && declaration.getPrenom().toUpperCase().charAt(0) == declaration.getNumeroDePermis().charAt(1);
    }

    @Override
    public boolean contientHeuresTransferees() {
        return false;
    }

    @Override
    public int NbHeuresMinCycles() {
        return 55;
    }

}
