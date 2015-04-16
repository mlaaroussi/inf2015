package fakeDeclaration;

import formationcontinue.Activite;
import formationcontinue.Declaration;
import java.util.ArrayList;
import java.util.List;

public class ArchValideEtComplet extends Declaration {

    public ArchValideEtComplet() {
        List<Activite> activites = new ArrayList<>();
        activites.add(new fakeActivite.Cours20141202());
        activites.add(new fakeActivite.Atelier20131202());
        activites.add(new fakeActivite.Invalide());
        activites.add(new fakeActivite.Atelier2030());

        setActivities(activites);
        setCycle("2012-2014");
        setNom("unNom");
        setPrenom("unPrenom");
        setOrdre("architectes");
        setChampHeuresTransfereesCyclePrecedentPresent(true);
        setHeuresTransfereesCyclePrecedent(8);
        setSexe(0);
    }
}
