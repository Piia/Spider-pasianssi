package Kayttoliittyma;


import Peli.Vaikeusaste;
import javax.swing.JFrame;


/**
 * Kayttoliittyma-luokka luo graafisen käyttöliittymän. 
 * Luokan eräs attribuutti on JFrame-olio ikkuna, joka huolehtii
 * ohjelmaikkunasta. Ikkunaan liitetään yläpalkki, JMenubarin perivä valikko, 
 * sekä JPanelin perivä alusta. 
 * 
 * @author Piia
 */


public class Kayttoliittyma implements Runnable {
    
    private JFrame ikkuna;
    private Alusta alusta;
    private Valikko valikko;
    
    public Kayttoliittyma() {
        valikko = new Valikko(this);
        
    }

    /**
     * Käynnistää graafisen käyttöliittymän.
     * Luo JFrame-ikkunan.
     */
    @Override
    public void run() {
        
        ikkuna = new JFrame("SPIDER-PASIANSSI");        
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setVisible(true);
        ikkuna.setJMenuBar(valikko);
        ikkuna.pack();
        ikkuna.setSize(1100, 700);
        
    }
    
    /**
     * Luo uuden Alusta-olion ja lisää sen ikkunaan. Mikäli ikkunassa on jo
     * edellinen alusta, se poistetaan ja luodaan tilalle uusi.
     * 
     * @param vaikeusaste 
     */
    public void luoAlusta(Vaikeusaste vaikeusaste) {
        if(alusta != null) {
            ikkuna.remove(this.alusta);
        }
        
        alusta = new Alusta(vaikeusaste);
        ikkuna.add(alusta);
       
        ikkuna.paintAll(ikkuna.getGraphics());
    }
    
    /**
     * Metodi kutsuu alustan jaa-metodia, joka jakaa pelipöydälle kortteja.
     * Palauttaa int-arvon jäljellä olevien jakojen määrästä.
     * 
     * @return 
     */
    public int jaaKortit() {
        alusta.jaa();
        
        return alusta.montaJakoa();
    }


  
}