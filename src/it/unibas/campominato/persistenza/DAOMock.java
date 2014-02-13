package it.unibas.campominato.persistenza;

import it.unibas.campominato.modello.Livello;
import it.unibas.campominato.modello.LivelloPersonalizzato;
import it.unibas.campominato.modello.Tempo;
import java.util.ArrayList;
import java.util.List;


public class DAOMock {
    
    public static List<Livello> caricaLivelliRecord() {
        Tempo tempoBase1 = new Tempo(27, "andypower", 9, 9, 10);
        Tempo tempoBase2 = new Tempo(65, "salvatore", 9, 9, 10);
        Tempo tempoBase3 = new Tempo(57, "gianfry", 9, 9, 10);
        Tempo tempoBase4 = new Tempo(76, "santomax", 9, 9, 10);
        Tempo tempoBase5 = new Tempo(98, "jokey", 9, 9, 10);
        Livello base = new Livello("Livello Base");
        base.addTempo(tempoBase1);
        base.addTempo(tempoBase2);
        base.addTempo(tempoBase3);
        base.addTempo(tempoBase4);
        base.addTempo(tempoBase5);
        Tempo tempoIntermedio1 = new Tempo(168, "andypower", 16, 16, 40);
        Tempo tempoIntermedio2 = new Tempo(534, "salvatore", 16, 16, 40);
        Tempo tempoIntermedio3 = new Tempo(290, "gianfry", 16, 16, 40);
        Tempo tempoIntermedio4 = new Tempo(899, "santomax", 16, 16, 40);
        Tempo tempoIntermedio5 = new Tempo(390, "jokey", 16, 16, 40);
        Livello intermedio = new Livello("Livello Intermedio");
        intermedio.addTempo(tempoIntermedio1);
        intermedio.addTempo(tempoIntermedio2);
        intermedio.addTempo(tempoIntermedio3);
        intermedio.addTempo(tempoIntermedio4);
        intermedio.addTempo(tempoIntermedio5);
        Tempo tempoAvanzato1 = new Tempo(590, "andypower", 16, 30, 99);
        Tempo tempoAvanzato2 = new Tempo(899, "salvatore", 16, 30, 99);
        Tempo tempoAvanzato3 = new Tempo(1208, "gianfry", 16, 30, 99);
        Tempo tempoAvanzato4 = new Tempo(1983, "santomax", 16, 30, 99);
        Tempo tempoAvanzato5 = new Tempo(1768, "jokey", 16, 30, 99);
        Livello avanzato = new Livello("Livello Avanzato");
        avanzato.addTempo(tempoAvanzato1);
        avanzato.addTempo(tempoAvanzato2);
        avanzato.addTempo(tempoAvanzato3);
        avanzato.addTempo(tempoAvanzato4);
        avanzato.addTempo(tempoAvanzato5);
        Tempo tempoPersonalizzato1 = new Tempo(120, "andypower", 16, 30, 99);
        Tempo tempoPersonalizzato2 = new Tempo(500, "salvatore", 16, 30, 99);
        Tempo tempoPersonalizzato3 = new Tempo(200, "gianfry", 16, 30, 99);
        Tempo tempoPersonalizzato4 = new Tempo(199, "santomax", 16, 30, 99);
        Tempo tempoPersonalizzato5 = new Tempo(390, "jokey", 16, 30, 99);
        Livello personalizzato = new LivelloPersonalizzato();
        personalizzato.addTempo(tempoPersonalizzato1);
        personalizzato.addTempo(tempoPersonalizzato2);
        personalizzato.addTempo(tempoPersonalizzato3);
        personalizzato.addTempo(tempoPersonalizzato4);
        personalizzato.addTempo(tempoPersonalizzato5);
        List<Livello> livelliRecord = new ArrayList<Livello>();
        livelliRecord.add(base);
        livelliRecord.add(intermedio);
        livelliRecord.add(avanzato);
        livelliRecord.add(personalizzato);
        return livelliRecord;
    }
    
}
