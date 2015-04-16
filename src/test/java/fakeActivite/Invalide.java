package fakeActivite;

import formationcontinue.Activite;
import formationcontinue.DateUtil;

public class Invalide extends Activite {

    public Invalide() {
        setDescription("CategorieInvalide");
        setCategorie("categorieInvalide");
        setHeures(10);
        setDate(DateUtil.getDate("2013-02-02"));
    }

}
