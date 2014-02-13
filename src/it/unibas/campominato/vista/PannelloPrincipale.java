package it.unibas.campominato.vista;

import com.jeta.forms.components.panel.FormPanel;
import it.unibas.campominato.Costanti;
import it.unibas.campominato.controllo.AzioneChiudiFinestra;
import it.unibas.campominato.controllo.AzionePausa;
import it.unibas.ping.binding.Form;
import it.unibas.ping.binding.IForm;
import it.unibas.ping.binding.osservatori.IOsservatore;
import it.unibas.ping.binding.osservatori.OsservatoreLabel;
import it.unibas.ping.binding.osservatori.OsservatoreTabellaBidim;
import it.unibas.ping.framework.Controllo;
import it.unibas.ping.framework.PannelloPing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PannelloPrincipale extends PannelloPing{
    
    private OsservatoreLabel ossStatus;
    private JTable tabella;
    private JPanel pannelloPausa;
    private JDialog finestraMinaTrovata;
    private static FormPanel pannelloPrincipale;
    Log logger = LogFactory.getLog(this.getClass());
    
    public void inizializza() {
        this.setLayout(new BorderLayout());
        this.inizializzaPannelloPrincipale();
        this.add(pannelloPrincipale);
        this.inizializzaFinestraMinaTrovata();
    }
    
    private void inizializzaPannelloPrincipale() {
        pannelloPrincipale = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "pannelloPrincipale.jfrm");
        long secondi = 0;
        this.modello.putBean("secondi", secondi);
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long secondi = (Long)modello.getBean("secondi");
                secondi++;
                JLabel labelTempo = pannelloPrincipale.getLabel("labelTempo");
                labelTempo.setText("" + secondi);
                modello.putBean("secondi", secondi);
            }
        });
        this.modello.putBean(Costanti.TIMER, timer);
        JButton bottonePausa = (JButton)pannelloPrincipale.getButton("bottonePause");
        Icon icona = bottonePausa.getIcon();
        bottonePausa.setAction(this.controllo.getAzioneSwing(AzionePausa.class.getName()));
        bottonePausa.setText("");
        bottonePausa.setIcon(icona);
        this.creaTabellaScacchiera();
    }
    
    private void creaTabellaScacchiera() {
        this.tabella = this.pannelloPrincipale.getTable("tabella");
        this.tabella.setRowHeight(30);
        this.tabella.setBackground(new Color(231,231,231));
        this.tabella.setCellSelectionEnabled(true);
        this.tabella.setColumnSelectionAllowed(false);
        this.tabella.setDragEnabled(false);
        this.tabella.setRowSelectionAllowed(false);
        this.tabella.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void aggiornaPannelloPrincipale() {
        long secondi = (Long)this.modello.getBean("secondi");
        JLabel labelTempo = pannelloPrincipale.getLabel("labelTempo");
        labelTempo.setText("" + secondi);
        this.aggiungiListenersTabella();
        this.aggiungiOsservatori();
        this.tabella.setPreferredSize(this.getDimensioniScacchiera());
        this.vista.getFramePrincipale().pack();
    }
    
    public void rimuoviListenersTabella() {
        ListSelectionListener selectionListenerTabella = this.controllo.getListSelectionListener(Costanti.AZIONE_GIOCA);
        MouseListener mouseListenerTabella = this.controllo.getMouseListener(Costanti.AZIONE_GIOCA_MOUSE);
        this.tabella.getSelectionModel().removeListSelectionListener(selectionListenerTabella);
        this.tabella.getColumnModel().getSelectionModel().removeListSelectionListener(selectionListenerTabella);
        this.tabella.removeMouseListener(mouseListenerTabella);
        logger.info("Listeners rimossi");
    }
    
    private void aggiungiListenersTabella() {
        ListSelectionListener selectionListenerTabella = this.controllo.getListSelectionListener(Costanti.AZIONE_GIOCA);
        MouseListener mouseListenerTabella = this.controllo.getMouseListener(Costanti.AZIONE_GIOCA_MOUSE);
        this.tabella.getSelectionModel().addListSelectionListener(selectionListenerTabella);
        this.tabella.getColumnModel().getSelectionModel().addListSelectionListener(selectionListenerTabella);
        this.tabella.addMouseListener(mouseListenerTabella);
        logger.info("Listeners aggiunti");
    }
    
    private void aggiungiOsservatori() {
        IOsservatore numMine = new OsservatoreLabel("campoNumMine", Costanti.SCACCHIERA, Costanti.NUMERO_MINE);
        OsservatoreTabellaBidim ossScacchiera = new OsservatoreTabellaBidim("tabella",
                new ModelloTabella(), Costanti.SCACCHIERA, Costanti.CELLE);
        if (ossStatus == null) {
            ossStatus = new OsservatoreLabel("labelStatus", Controllo.MESSAGGIO_STATO, Controllo.VALORE_MESSAGGIO);
            ossStatus.setPrefisso("..: ");
            ossStatus.setSuffisso(" :..");
        }
    }
    
    public void visualizzaSchermoPausa() {
        JTextPane textPane = new JTextPane();
        textPane.insertIcon(Utilita.creaIcona(Costanti.SFONDO_PAUSA));
        pannelloPausa = new JPanel(new BorderLayout());
        if (this.getDimensioniScacchiera().width > 800) {
            int larghezza = this.getDimensioniScacchiera().width - 800;
            JTextPane textPane2 = new JTextPane();
            textPane2.insertIcon(Utilita.creaIcona(Costanti.SFONDO_PAUSA));
            textPane2.setSize(larghezza, this.getDimensioniScacchiera().height);
            pannelloPausa.add(textPane2, BorderLayout.WEST);
        }
        pannelloPausa.setSize(this.getDimensioniScacchiera().width+130, this.getDimensioniScacchiera().height);
        pannelloPausa.add(textPane, BorderLayout.CENTER);
        this.pannelloPrincipale.getTable("tabella").add(pannelloPausa);
    }
    
    public void visualizzaTabellaDopoPausa() {
        this.pannelloPrincipale.getTable("tabella").remove(this.pannelloPausa);
        this.aggiungiListenersTabella();
        this.repaint();
    }
    
    private Dimension getDimensioniScacchiera() {
        int colonneScacchiera = (Integer)this.modello.getBean("colonneScacchiera");
        int righeScacchiera = (Integer)this.modello.getBean("righeScacchiera");
        int larghezza =(30 * colonneScacchiera);
        int altezza =(30 * righeScacchiera);
        return new Dimension(larghezza, altezza);
    }
    
    private void inizializzaFinestraMinaTrovata() {
        IForm form = new Form(this);
        FormPanel formPanel = new FormPanel("it" + Costanti.FILE_SEPARATOR + "unibas" + Costanti.FILE_SEPARATOR +
                "campominato" + Costanti.FILE_SEPARATOR + "vista" + Costanti.FILE_SEPARATOR + "finestraMinaTrovata.jfrm");
        this.finestraMinaTrovata = new JDialog(vista.getFramePrincipale(), "Peccato", true);
        JButton bottoneCommit = (JButton)formPanel.getButton("bottoneNuova");
        JButton bottoneChiudi = (JButton)formPanel.getButton("bottoneChiudi");
        form.setBottoneCommit(bottoneCommit, Costanti.AZIONE_NUOVA_PARTITA);
        String toolTip = bottoneChiudi.getToolTipText();
        Icon icona = bottoneChiudi.getIcon();
        bottoneChiudi.setAction(this.controllo.getAzioneSwing(AzioneChiudiFinestra.class.getName()));
        bottoneChiudi.setIcon(icona);
        bottoneChiudi.setToolTipText(toolTip);
//        this.finestraMinaTrovata.setUndecorated(true);
        this.finestraMinaTrovata.add(formPanel);
        this.finestraMinaTrovata.setLocation(100,100);
        this.finestraMinaTrovata.pack();
    }
    
    public void mostraFinestraMinaTrovata() {
        this.finestraMinaTrovata.setVisible(true);
    }
    
    public void nascondiFinestraMinaTrovata() {
        this.finestraMinaTrovata.setVisible(false);
    }
    
}
