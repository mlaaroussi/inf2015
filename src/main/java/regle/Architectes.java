package regle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Architectes implements Declaration {

    private static final List<Cycle> REGLES_CYCLE = new ArrayList<>(
            Arrays.asList(new Cycle("2010-2012", "2012-04-01", "2010-04-01"),
                    new Cycle("2008-2010", "2008-04-01", "2010-07-01"),
                    new Cycle("2012-2014", "2012-04-01", "2014-04-01")
            )
    );

    private static final Categories REGLE_CATEGORIES_HEURE_TRANSFERER
            = new Categories(17, 0, new String[]{
                "cours", "atelier",
                "séminaire", "colloque",
                "conférence", "lecture dirigée"});

    private static final List<Categories> REGLES_CATEGORIES = new ArrayList<>(
            Arrays.asList(new Categories(0, 23, new String[]{"présentation"}),
                    new Categories(0, 17, new String[]{"groupe de discussion"}),
                    new Categories(0, 23, new String[]{"projet de recherche"}),
                    new Categories(0, 17, new String[]{"rédaction professionnelle"})
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
        return declaration.getNumeroDePermis().matches("[AT]{1}\\d{4}");
    }

    @Override
    public boolean contientHeuresTransferees() {
        return true;
    }

    @Override
    public int NbHeuresMinCycles() {
        return 42;
    }

}
