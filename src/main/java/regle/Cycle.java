package regle;

import formationcontinue.DateUtil;
import java.util.Date;

public class Cycle {

    private String nomDuCycle;
    private Date dateDebut;
    private Date dateFin;

    public Cycle(String nomDuCycle, String dateDebut, String dateFin) {
        this.nomDuCycle = nomDuCycle;
        this.dateDebut = DateUtil.getDate(dateDebut);
        this.dateFin = DateUtil.getDate(dateFin);
    }

    /**
     * @return the nomDuCycle
     */
    public String getNomDuCycle() {
        return nomDuCycle;
    }

    public boolean estDateValide(Date date) {
        return !date.before(dateDebut) && !date.after(dateFin);
    }

}
