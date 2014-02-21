package it.unibas.campominato.vista;

import com.jeta.forms.components.panel.FormPanel;
import it.unibas.campominato.Costanti;
import it.unibas.campominato.controllo.AzioneChiudiFinestra;
import it.unibas.campominato.modello.Livello;
import it.unibas.campominato.modello.Tempo;
import it.unibas.ping.framework.FinestraDiDialogoPing;
import java.awt.Font;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FinestraRecord extends FinestraDiDialogoPing{
    
    private FormPanel formPanel;
    private Log logger = LogFactory.getLog(this.getClass());
    
    public void inizializza() {
//        this.setUndecorated(true);
        formPanel = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "finestraRecord.jfrm");
        this.add(formPanel);
        this.creaForm();
        this.setModal(true);
        this.pack();
    }
    
    public void aggiornaRecord() {
//        this.getContentPane().removeAll();
//        this.contenitore.removeAll();
//        this.contenitore.add(this.legenda);
        Object obj = this.getModello().getBean("records");
        List<Livello> listaRecord = (List<Livello>)obj;
        for (Livello livelloIesimo : listaRecord) {
            costruisciLivello(livelloIesimo);
        }
        this.add(this.formPanel);
        this.pack();
    }
    
    private void costruisciLivello(Livello livello) {
        List<Tempo> listaTempi = livello.getListaTempi();
        Object[] listaNomi = new Object[5];
        Object[] listaSecondi = new Object[5];
        Object[] listaRighe = new Object[5];
        Object[] listaColonne = new Object[5];
        Object[] listaMine = new Object[5];
        int i = 0;
        for (Tempo tempo : listaTempi) {
            String nome = tempo.getNomeGiocatore().toString();
            listaNomi[i] = nome;
            String sec = "" + tempo.getSecondi();
            listaSecondi[i] = sec;
            String righe = "" + tempo.getNumRighe();
            listaRighe[i] = righe;
            String colonne = "" + tempo.getNumColonne();
            listaColonne[i] = colonne;
            String mine = "" + tempo.getNumMine();
            listaMine[i] = mine;
            i++;
        }
        this.formPanel.getList("lista" + livello.toString().replace(" ", "") + "Nome").setListData(listaNomi);
        this.formPanel.getList("lista" + livello.toString().replace(" ", "") + "Sec").setListData(listaSecondi);
        this.formPanel.getList("lista" + livello.toString().replace(" ", "") + "Righe").setListData(listaRighe);
        this.formPanel.getList("lista" + livello.toString().replace(" ", "") + "Colonne").setListData(listaColonne);
        this.formPanel.getList("lista" + livello.toString().replace(" ", "") + "Mine").setListData(listaMine);
    }
    
    private void creaForm() {
        JButton bottoneChiudi = (JButton)formPanel.getButton("bottoneChiudi");
        String toolTip = bottoneChiudi.getToolTipText();
        Icon icona = bottoneChiudi.getIcon();
        bottoneChiudi.setAction(this.controllo.getAzioneSwing(AzioneChiudiFinestra.class.getName()));
        bottoneChiudi.setIcon(icona);
        bottoneChiudi.setToolTipText(toolTip);
    }
    
    public void visualizzaFinestraRecord() {
        this.aggiornaRecord();
        this.setVisible(true);
    }
    
    public void nascondiFinestraRecord() {
        this.setVisible(false);
    }
}
