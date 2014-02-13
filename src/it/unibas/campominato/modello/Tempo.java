package it.unibas.campominato.modello;

public class Tempo implements Comparable{
    
    private long secondi;
    private String nomeGiocatore;
    private int numRighe;
    private int numColonne;
    private int numMine;
    
    public Tempo() {
    }
    
    public Tempo(long secondi, String nomeGiocatore, int numColonne, int numRighe, int numMine) {
        this.secondi = secondi;
        this.nomeGiocatore = nomeGiocatore;
        this.numColonne = numColonne;
        this.numRighe = numRighe;
        this.numMine = numMine;
    }

    public int compareTo(Object tempo) {
        Long secondi = this.getSecondi();
        Long secondi2 = ((Tempo)tempo).getSecondi();
        return secondi.compareTo(secondi2);
    }

    public String getNomeGiocatore() {
        return this.nomeGiocatore;
    }

    public void setNomeGiocatore(final String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
    }

    public int getNumColonne() {
        return this.numColonne;
    }

    public void setNumColonne(final int numColonne) {
        this.numColonne = numColonne;
    }

    public int getNumMine() {
        return this.numMine;
    }

    public void setNumMine(final int numMine) {
        this.numMine = numMine;
    }

    public int getNumRighe() {
        return this.numRighe;
    }

    public void setNumRighe(final int numRighe) {
        this.numRighe = numRighe;
    }

    public long getSecondi() {
        return this.secondi;
    }

    public void setSecondi(final long secondi) {
        this.secondi = secondi;
    }
}
