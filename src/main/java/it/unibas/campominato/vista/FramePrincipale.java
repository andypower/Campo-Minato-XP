package it.unibas.campominato.vista;

import it.unibas.campominato.Costanti;
import it.unibas.ping.framework.FramePing;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class FramePrincipale extends FramePing {
    
    static{
        JFrame.setDefaultLookAndFeelDecorated(true);
    }
    
    public FramePrincipale() {
    }
    
    public void inizializza() {
        this.setTitle("Campo Minato Swing");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.creaBarraMenu();
        this.setLayout(new BorderLayout());
        this.getContentPane().add((JPanel)this.getVista().getSottoVista(PannelloPrincipale.class.getName()));
        this.setSize(480,700);
    }
    
    private void creaBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.creaMenuFile());
        menuBar.add(this.creaMenuLivello());
        menuBar.add(this.creaMenuAudio());
        menuBar.add(this.creaMenuHelp());
        this.setJMenuBar(menuBar);
    }
    
    private JMenu creaMenuLivello() {
        ButtonGroup gruppo = new ButtonGroup();
        JMenu menuLivello = new JMenu("Livello");
        menuLivello.setMnemonic('L');
        JRadioButtonMenuItem base = new JRadioButtonMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_LIVELLO_BASE));
        JRadioButtonMenuItem intermedio = new JRadioButtonMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_LIVELLO_INTERMEDIO));
        JRadioButtonMenuItem avanzato = new JRadioButtonMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_LIVELLO_AVANZATO));
        JRadioButtonMenuItem personalizzato = new JRadioButtonMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_LIVELLO_PERSONALIZZATO));
        gruppo.add(base);
        gruppo.add(intermedio);
        intermedio.setSelected(true);
        gruppo.add(avanzato);
        gruppo.add(personalizzato);
        menuLivello.add(base);
        menuLivello.add(intermedio);
        menuLivello.add(avanzato);
        menuLivello.add(personalizzato);
        return menuLivello;
    }
    
    private JMenu creaMenuAudio() {
        ButtonGroup gruppo = new ButtonGroup();
        JMenu menuAudio = new JMenu("Audio");
        menuAudio.setMnemonic('A');
        JCheckBoxMenuItem tipsy = new JCheckBoxMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_SUONA_TIPSY));
        JCheckBoxMenuItem loose = new JCheckBoxMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_SUONA_LOOSE));
        JCheckBoxMenuItem harsh = new JCheckBoxMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_SUONA_HARSH));
        JCheckBoxMenuItem soft = new JCheckBoxMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_SUONA_SOFT));
        gruppo.add(tipsy);
        gruppo.add(loose);
        gruppo.add(harsh);
        gruppo.add(soft);
        menuAudio.add(tipsy);
        menuAudio.add(loose);
        menuAudio.add(harsh);
        menuAudio.add(soft);
        menuAudio.addSeparator();
        JMenuItem stopSuono = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_STOP_SUONO));
        menuAudio.add(stopSuono);
        return menuAudio;
    }
    
    private JMenu creaMenuFile() {
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        JMenuItem nuovaPartita = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_NUOVA_PARTITA));
        JMenuItem pausa = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_PAUSA));
        JMenuItem esci = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_ESCI));
        menuFile.add(nuovaPartita);
        menuFile.add(pausa);
        menuFile.addSeparator();
        menuFile.add(esci);
        return menuFile;
    }
    
    private JMenu creaMenuHelp() {
        JMenu menuHelp = new JMenu("?");
        menuHelp.setMnemonic('?');
        JMenuItem record = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_VISUALIZZA_RECORD));
        JMenuItem info = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_VISUALIZZA_INFO));
        JMenuItem legenda = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_VISUALIZZA_LEGENDA));
        JMenuItem about = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_ABOUT_APPLICAZIONE));
        JMenuItem aboutPing = new JMenuItem(this.controllo.getAzioneSwing(Costanti.AZIONE_ABOUT));
        menuHelp.add(record);
        menuHelp.add(info);
        menuHelp.add(legenda);
        menuHelp.addSeparator();
        menuHelp.add(about);
        menuHelp.add(aboutPing);
        return menuHelp;
    }
    
}
