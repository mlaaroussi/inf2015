package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class Atelier2030 extends Activite {

    public Atelier2030() {
        setDescription("une description2");
        setCategorie("atelier");
        setHeures(5);
        setDate(DateUtil.getDate("2030-12-02"));
    }

}
