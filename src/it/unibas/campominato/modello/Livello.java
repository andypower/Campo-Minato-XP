package it.unibas.campominato.modello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Livello {
    
    protected String nome;
    protected List<Tempo> listaTempi = new ArrayList<Tempo>();
    
    public Livello(String nome) {
        this.nome = nome;
    }
    
    public void addTempo(Tempo tempo) {
        this.listaTempi.add(tempo);
        this.ordinaTempi();
        if (this.listaTempi.size() == 6) {
            this.listaTempi.remove(5);
        }
    }
    
    public Tempo getTempo(int indice) {
        return this.listaTempi.get(indice);
    }
    
    public String toString() {
        return this.nome;
    }
    
    public List<Tempo> getListaTempi() {
        return this.listaTempi;
    }
    
    public void setListaTempi(final List<Tempo> tempi) {
        this.listaTempi = tempi;
    }
    
    public void ordinaTempi() {
        Collections.sort(this.listaTempi);
    }
}
