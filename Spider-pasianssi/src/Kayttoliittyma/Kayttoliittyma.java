package Kayttoliittyma;


import Peli.Vaikeusaste;
import javax.swing.JFrame;
import javax.swing.JMenuBar;



public class Kayttoliittyma implements Runnable {
    
    private JFrame ikkuna;
    private Alusta alusta;
    private JMenuBar valikko;
    
    public Kayttoliittyma() {
        valikko = new Valikko(this);
        
    }

    @Override
    public void run() {
        
        ikkuna = new JFrame("SPIDER-PASIANSSI");        
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
        ikkuna.setJMenuBar(valikko);
        ikkuna.pack();
        ikkuna.setSize(1100, 700);
        
    }
    
    public void luoAlusta(Vaikeusaste vaikeusaste) {
        if(alusta != null) {
            ikkuna.remove(this.alusta);
        }
        
        alusta = new Alusta(vaikeusaste);
        ikkuna.add(alusta);
       
        ikkuna.paintAll(ikkuna.getGraphics());
    }
    
    public int jaaKortit() {
        alusta.jaa();
        
        return alusta.montaJakoa();
    }


  
}