package regle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Psychologues implements Declaration {

    private static final List<Cycle> REGLES_CYCLE = new ArrayList<>(
            Arrays.asList(new Cycle("2010-2015", "2010-01-01", "2015-01-01")
            )
    );

    private static final Categories REGLE_CATEGORIES_HEURE_TRANSFERER
            = new Categories(0, 0, new String[]{});

    private static final List<Categories> REGLES_CATEGORIES = new ArrayList<>(
            Arrays.asList(new Categories(25, 0, new String[]{"cours"}),
                    new Categories(0, 15, new String[]{"conférence"}),
                    new Categories(0, 0, new String[]{
                        "atelier", "séminaire", "colloque", "lecture dirigée",
                        "présentation", "groupe de discussion",
                        "projet de recherche", "rédaction professionnelle"})
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
        return declaration.getNumeroDePermis().matches("\\d{5}[-]{1}\\d{2}");
    }

    @Override
    public boolean contientHeuresTransferees() {
        return false;
    }

    @Override
    public int NbHeuresMinCycles() {
        return 90;
    }

}
