package it.unibas.campominato.vista;

import com.jeta.forms.components.panel.FormPanel;
import it.unibas.campominato.Costanti;
import it.unibas.campominato.controllo.AzioneChiudiFinestra;
import it.unibas.campominato.controllo.ImpostaRecord;
import it.unibas.campominato.modello.Livello;
import it.unibas.ping.binding.Form;
import it.unibas.ping.binding.IForm;
import it.unibas.ping.convalida.ConvalidatoreStringaNonNulla;
import it.unibas.ping.convalida.IConvalidatoreForm;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.FinestraDiDialogoPing;
import it.unibas.ping.framework.MessaggioPing;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FinestraNuovoRecord extends FinestraDiDialogoPing{
    
    private FormPanel formPanel;
    private FormPanel formPanelVincitaAssoluta;
    private Log logger = LogFactory.getLog(this.getClass());
    
    private IForm form = new Form(this);
    
    public void inizializza() {
//        this.setUndecorated(true);
        formPanel = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "pannelloVittoria.jfrm");
        formPanelVincitaAssoluta = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "pannelloVittoriaAssoluta.jfrm");
        this.pack();
        this.setLocation(100,100);
    }
    
    public void visualizzaFinestraNuovoRecord() {
        long secondi = (Long)this.getModello().getBean("secondi");
//        this.removeAll();
        if (this.verificaEasterEgg()) {
            this.remove(this.formPanel);
            this.creaFormAvanzata();
            this.add(this.formPanelVincitaAssoluta);
            formPanelVincitaAssoluta.getLabel("labelRecord").setText("    Wow nuovo record assoluto in soli "
                    + secondi + " secondi!");
        } else {
            this.remove(this.formPanelVincitaAssoluta);
            this.creaForm();
            this.add(formPanel);
            formPanel.getLabel("labelRecord").setText("    Fantastico! Partita conclusa in "
                    + secondi + " secondi!");
        }
        this.pack();
        this.setVisible(true);
    }
    
    private void creaForm() {
        IConvalidatoreForm campoNome = new IConvalidatoreForm() {
            public List convalida(IForm iForm) {
                String nomeGiocatore = (String)vista.getValore("campoNome");
                ConvalidatoreStringaNonNulla convalidatore = new ConvalidatoreStringaNonNulla("campoNome");
                List errori = new ArrayList();
                if (convalidatore.convalida(nomeGiocatore).size() != 0) {
                    errori.add("Il nome deve essere inserito!");
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Hai il coraggio di inserire il tuo nome?"));
                }
                return errori;
            }
        };
        this.form.ripulisciComponenti();
        this.form.svuotaMessaggiErrore();
        this.form.addConvalidatoreForm(campoNome);
        JButton bottoneCommit = (JButton)formPanel.getButton("bottoneProcedi");
        JButton bottoneChiudi = (JButton)formPanel.getButton("bottoneChiudi");
        this.form.setBottoneCommit(bottoneCommit, ImpostaRecord.class.getName());
        String toolTip = bottoneChiudi.getToolTipText();
        Icon icona = bottoneChiudi.getIcon();
        bottoneChiudi.setAction(this.controllo.getAzioneSwing(AzioneChiudiFinestra.class.getName()));
        bottoneChiudi.setIcon(icona);
        bottoneChiudi.setToolTipText(toolTip);
    }
    
    private void creaFormAvanzata() {
        IConvalidatoreForm campoNome = new IConvalidatoreForm() {
            public List convalida(IForm iForm) {
                String nomeGiocatore = (String)vista.getValore("campoNome");
                ConvalidatoreStringaNonNulla convalidatore = new ConvalidatoreStringaNonNulla("campoNome");
                List errori = new ArrayList();
                if (convalidatore.convalida(nomeGiocatore).size() != 0) {
                    errori.add("Il nome deve essere inserito!");
                    modello.putBean(Controllo.MESSAGGIO_STATO, new MessaggioPing("Hai il coraggio di inserire il tuo nome?"));
                }
                return errori;
            }
        };
        this.form.addConvalidatoreForm(campoNome);
        JButton bottoneCommit = (JButton)formPanelVincitaAssoluta.getButton("bottoneProcedi");
        JButton bottoneChiudi = (JButton)formPanelVincitaAssoluta.getButton("bottoneChiudi");
        this.form.setBottoneCommit(bottoneCommit, ImpostaRecord.class.getName());
        String toolTip = bottoneChiudi.getToolTipText();
        Icon icona = bottoneChiudi.getIcon();
        bottoneChiudi.setAction(this.controllo.getAzioneSwing(AzioneChiudiFinestra.class.getName()));
        bottoneChiudi.setIcon(icona);
        bottoneChiudi.setToolTipText(toolTip);
    }
    
    private boolean verificaEasterEgg() {
        boolean easterEgg = false;
        long secondi = (Long)this.getModello().getBean("secondi");
        Object obj = this.getModello().getBean("records");
        List<Livello> listaRecord = (List)obj;
        Integer valoreLivello = (Integer)this.getModello().getBean("livello");
        if (valoreLivello != null) {
            switch(valoreLivello) {
                case 0: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Base") && secondi <= livelloIesimo.getTempo(0).getSecondi()) {
                        easterEgg = true;
                    }
                }; break; //livello base
                case 1: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Intermedio") && secondi <= livelloIesimo.getTempo(0).getSecondi()) {
                        easterEgg = true;
                    }
                };break; //livello intermedio
                case 2: for (Livello livelloIesimo : listaRecord) {
                    if (livelloIesimo.toString().equalsIgnoreCase("Livello Avanzato") && secondi <= livelloIesimo.getTempo(0).getSecondi()) {
                        easterEgg = true;
                    }
                };break; //livello avanzato
                case 3: break; //livello personalizzato
            }
        }
        return easterEgg;
    }
    
    public void nascondiFinestraNuovoRecord() {
        this.setVisible(false);
    }
}
