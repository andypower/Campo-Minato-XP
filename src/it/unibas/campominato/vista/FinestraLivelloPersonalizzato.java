package it.unibas.campominato.vista;

import com.jeta.forms.components.panel.FormPanel;
import it.unibas.campominato.Costanti;
import it.unibas.campominato.controllo.EseguiLivelloPersonalizzato;
import it.unibas.ping.binding.Form;
import it.unibas.ping.binding.IForm;
import it.unibas.ping.convalida.ConvalidatoreNumeroIntero;
import it.unibas.ping.convalida.IConvalidatoreForm;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.unibas.ping.framework.MessaggioPing;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FinestraLivelloPersonalizzato extends FinestraDiDialogoPing{
    
    private FormPanel formPanel;
    private Log logger = LogFactory.getLog(this.getClass());
    
    public void inizializza() {
//        this.setUndecorated(true);
        formPanel = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "finestraLivelloPersonalizzato.jfrm");
        this.add(formPanel);
        formPanel.getLabel("labelInserimento").setFont(new Font("Tahoma",Font.PLAIN,11));
        this.creaForm();
        this.setModal(true);
        this.pack();
    }
    
    private void creaForm() {
        IForm form = new Form(this);
        IConvalidatoreForm livelloCorretto = new IConvalidatoreForm() {
            public List convalida(IForm iForm) {
                String numRighe = (String)vista.getValore("numeroRighe");
                String numColonne = (String)vista.getValore("numeroColonne");
                String numMine = (String)vista.getValore("numeroMine");
                ConvalidatoreNumeroIntero convalidatoreRighe = new ConvalidatoreNumeroIntero("numeroRighe", 2, 24);
                ConvalidatoreNumeroIntero convalidatoreColonne = new ConvalidatoreNumeroIntero("numeroColonne", 2, 30);
                ConvalidatoreNumeroIntero convalidatoreMine = new ConvalidatoreNumeroIntero("numeroMine", 1, 667);
                List errori = new ArrayList();
                if (convalidatoreColonne.convalida(numColonne).size() != 0) {
                    errori.add("Errore nel campo delle Colonne");
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Cosa pensavi di fare???"));
                }
                if (convalidatoreRighe.convalida(numRighe).size() != 0) {
                    errori.add("Errore nel campo delle Righe");
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Fare le cose per bene no???"));
                }
                if (convalidatoreMine.convalida(numMine).size() != 0) {
                    errori.add("Errore nel campo delle Mine");
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Le mine potrebbero esplodere!!!"));
                }
                if (errori.size() == 0) {
                    int numeroColonne = convalidatoreColonne.converti(numColonne);
                    int numeroRighe = convalidatoreRighe.converti(numRighe);
                    int numeroMine = convalidatoreMine.converti(numMine);
                    int totCelle = numeroRighe * numeroColonne;
                    if (totCelle < numeroMine) {
                        errori.add("Numero di mine eccessivo rispetto alle righe e colonne fornite");
                        errori.add("Rispettare le regole del livello Personalizzato");
                        modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Non è che in fondo in fondo ti senti un kamikaze?"));
                    }
                }
                return errori;
            }
        };
        form.addConvalidatoreForm(livelloCorretto);
        JButton bottoneCommit = (JButton)formPanel.getButton("bottoneSceltaLivello");
        JButton bottoneRollback = (JButton)formPanel.getButton("bottoneAnnulla");
        form.setBottoneCommit(bottoneCommit, EseguiLivelloPersonalizzato.class.getName());
        form.setBottoneRollback(bottoneRollback);
    }
    
    public void mostraFinestraLivelloPersonalizzato() {
        this.setVisible(true);
    }
    
    public void nascondiFinestraLivelloPersonalizzato() {
        this.setVisible(false);
    }
}
