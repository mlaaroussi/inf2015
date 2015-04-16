package fakeDeclaration;

import formationcontinue.Activite;
import formationcontinue.Declaration;
import java.util.ArrayList;
import java.util.List;

public class DeclarationInvalide extends Declaration{

    public DeclarationInvalide() {
           
        List<Activite> activites = new ArrayList<>();
        activites.add(new fakeActivite.Cours20141202());
        
        setActivities(activites);
        setCycle("1111-1111");
        setNom("unNom");
        setPrenom("unPrenom");
        setOrdre("architectes");
        setNumeroDePermis("BJ0001");
        setChampHeuresTransfereesCyclePrecedentPresent(true);
        setHeuresTransfereesCyclePrecedent(8);
        setSexe(0);
    }
    
    
    
}
