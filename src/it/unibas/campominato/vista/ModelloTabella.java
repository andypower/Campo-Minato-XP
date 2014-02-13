package it.unibas.campominato.vista;

import it.unibas.campominato.modello.Scacchiera;
import it.unibas.ping.binding.osservatori.ModelloTabellaPing;
import javax.swing.ImageIcon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModelloTabella extends ModelloTabellaPing{
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    public int getRowCount() {
        Scacchiera scacchiera = (Scacchiera)super.getBean();
//        logger.info("--------> getRowCount: " + scacchiera.getNumRighe());
        if (scacchiera != null) {
            return scacchiera.getNumRighe();
        }
        return 0;
    }
    
    public boolean isCellEditable(int i1, int i2) {
        return false;
    }
    
    public Class getColumnClass(int i){
        return ImageIcon.class;
    }
    
    public Object getValueAt(int i1, int i2) {
        Scacchiera scacchiera = (Scacchiera)super.getBean();
        if (scacchiera != null) {
            return scacchiera.getCella(i1, i2).getImage();
        }
        return 0;
    }
    
    public int getColumnCount() {
        Scacchiera scacchiera = (Scacchiera)super.getBean();
        if (scacchiera != null) {
            return scacchiera.getNumColonne();
        }
        return 0;
    }
    
    public String getColumnName(int i) {
        return null;
    }
}
