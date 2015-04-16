package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class Atelier20131202 extends Activite {

    public Atelier20131202() {
        setDescription("une description2");
        setCategorie("atelier");
        setHeures(5);
        setDate(DateUtil.getDate("2013-12-02"));
    }

}
