package regle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Podiatres implements Declaration {

    private static final List<Cycle> REGLES_CYCLE = new ArrayList<>(
            Arrays.asList(new Cycle("2013-2016", "2013-05-01", "2016-05-01")
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
        return declaration.getNumeroDePermis().matches("\\d{5}");
    }

    @Override
    public boolean contientHeuresTransferees() {
        return false;
    }

    @Override
    public int NbHeuresMinCycles() {
        return 60;
    }
}
