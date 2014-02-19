package it.unibas.campominato.vista;

import com.jeta.forms.components.panel.FormPanel;
import it.unibas.campominato.Costanti;
import it.unibas.campominato.controllo.AzioneChiudiFinestra;
import it.unibas.ping.framework.FinestraDiDialogoPing;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JButton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FinestraLegenda extends FinestraDiDialogoPing{
    
    private FormPanel formPanel;
    private Log logger = LogFactory.getLog(this.getClass());
    
    public void inizializza() {
//        this.setUndecorated(true);
        formPanel = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "finestraLegenda.jfrm");
        this.add(formPanel);
        this.creaForm();
        this.setModal(true);
        this.pack();
    }
    
    private void creaForm() {
        JButton bottoneChiudi = (JButton)formPanel.getButton("bottoneChiudi");
        String toolTip = bottoneChiudi.getToolTipText();
        Icon icona = bottoneChiudi.getIcon();
        bottoneChiudi.setAction(this.controllo.getAzioneSwing(AzioneChiudiFinestra.class.getName()));
        bottoneChiudi.setIcon(icona);
        bottoneChiudi.setToolTipText(toolTip);
    }
    
    public void visualizzaLegenda() {
        this.setVisible(true);
    }
    
    public void nascondiLegenda() {
        this.setVisible(false);
    }
}
