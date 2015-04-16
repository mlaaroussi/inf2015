package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class Cours20141202 extends Activite {

    public Cours20141202() {
        setDescription("une description1");
        setCategorie("cours");
        setHeures(12);
        setDate(DateUtil.getDate("2014-01-02"));
    }

}
