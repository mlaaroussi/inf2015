package fakeDeclaration;

import formationcontinue.Activite;
import formationcontinue.Declaration;
import java.util.ArrayList;
import java.util.List;

public class GeoValideEtComplet extends Declaration {

    public GeoValideEtComplet() {
        List<Activite> activites = new ArrayList<>();
        activites.add(new fakeActivite.Cours20141202());
        activites.add(new fakeActivite.Atelier20131202());
        activites.add(new fakeActivite.Invalide());
        activites.add(new fakeActivite.Atelier2030());

        setActivities(activites);
        setCycle("2010-2015");
        setNom("Laaroussi");
        setPrenom("mohamed");
        setOrdre("psychologues");
        setSexe(1);
    }
}
