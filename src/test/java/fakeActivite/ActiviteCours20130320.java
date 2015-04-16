package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class ActiviteCours20130320 extends Activite {

    public ActiviteCours20130320() {
        setDescription("Cours sur la déontologie");
        setCategorie("cours");
        setHeures(14);
        setDate(DateUtil.getDate("2013-03-20"));
    }
}
