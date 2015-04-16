package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class ActiviteSeminaire20140104 extends Activite {

    public ActiviteSeminaire20140104() {
        setDescription("Séminaire sur l'architecture contemporaine");
        setCategorie("séminaire");
        setHeures(10);
        setDate(DateUtil.getDate("2014-01-07"));
    }
}
