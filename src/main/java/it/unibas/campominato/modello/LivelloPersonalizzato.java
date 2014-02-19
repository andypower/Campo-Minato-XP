package it.unibas.campominato.modello;

public class LivelloPersonalizzato extends Livello {
    
    public LivelloPersonalizzato() {
        super("Livello Personalizzato");
    }
    
    @Override
    public void addTempo(Tempo tempo) {
        super.listaTempi.add(tempo);
        if (super.listaTempi.size() == 6) {
            super.listaTempi.remove(0);
        }
    }
    
}
