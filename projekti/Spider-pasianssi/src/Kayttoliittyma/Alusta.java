package Kayttoliittyma;

import Peli.Spider;
import Peli.Vaikeusaste;
import Rajapinnat.SpiderRajapinta;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Alusta huolehtii sovelluslogiikan ja graafisen käyttöliittymän 
 * välisestä yhteydenpidosta. 
 * 
 * Alustan attribuutteina ovat SpiderRajapinnan toteuttava olio ja kymmenen 
 * Piirturi-oliota, jotka piirtävät kukin oman pinonsa. Piirturit sijaitsevat 
 * kätevästi ArrayList-rakenteessa.
 * 
 * @author Piia
 */

public class Alusta extends JPanel {

    private SpiderRajapinta spider;
    private ArrayList<Piirturi> piirturit;
    private Vaikeusaste vaikeusaste;
    
    private boolean yksiKorttiValittu;
    private int valittuPinosta;
    private int valittuIndeksi;

    
    /**
     * Konstruktori ottaa parametriksi pelin vaikeusasteen. Alustalle asetetaan 
     * layoutiksi GridLayout ja luodaan sekä lisätään Piirturi-oliot.
     * 
     * @param vaikeusaste 
     */
    public Alusta(Vaikeusaste vaikeusaste) {
        super(new GridLayout());
        
        this.yksiKorttiValittu = false;
        this.piirturit = new ArrayList<Piirturi>();
        this.vaikeusaste = vaikeusaste;
        this.spider = new Spider(vaikeusaste);
        
        
        for (int i = 0; i < 10; i++) {
            Piirturi p = new Piirturi(this, spider.getPinot().get(i), i);p.setEnabled(true);
            p.setVisible(true);
            p.setEnabled(true);
            p.setLocation(pinonX(i), 50);
            piirturit.add(p);
            this.add(p);
        }
        
    }

    /**
     * Peritty paint-metodi ottaa parametriksi Graphics-olion. Se kutsuu 
     * piirtureiden paint-metodia.
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        this.setBackground(new Color(34, 139, 34));
        for(Piirturi p: piirturit) {
            p.paint(g);
        }
        
    }

    /**
     * Peritty repaint-metodi kutsuu piirtureiden repaint-metodia.
     */
    @Override
    public void repaint(){
        
        if(spider != null){
            for(Piirturi p: piirturit) {
                p.repaint();
            }
        }
        super.repaint();
    }

    /**
     * Kutsuu spiderin jaa-metodia ja sen jälkeen repaint-metodia.
     */
    public void jaa() {
        if (spider == null) {
            return;
        }
        spider.jaa();
        this.repaint();
    }

    /**
     * Palauttaa spiderin montaJakoa-metodin palauttaman kokonaislukuarvon, 
     * joka kertoo jäljellä olevien jakojen määrän.
     * @return 
     */
    public int montaJakoa() {
        if (spider == null) {
            return 0;
        }
        return spider.montaJakoa();
    }

    /**
     * Metodi ottaa parametriksi pinon indeksin ja pinossa olevan kortin indeksin.
     * 
     * Nämä parametrit talletetaan muuttujiin valittuPinosta ja valittuIndeksi, 
     * mutta mikäli muuttujan yksiKorttiValittu arvo on jo tässä metodissa muutettu 
     * arvoksi true, kutsutaan siirto-metodia.
     * @param pino
     * @param indeksi 
     */
    protected void korttiValittu(int pino, int indeksi) {
        if (yksiKorttiValittu == false) {
            valittuPinosta = pino;
            valittuIndeksi = indeksi;
            yksiKorttiValittu = true;

        } else if (valittuPinosta == pino) {
            valittuIndeksi = indeksi;

        } else {
            siirto(valittuIndeksi, valittuPinosta, pino);
            yksiKorttiValittu = false;
        }
    }

    /**
     * Metodi ottaa parametriksi kortin indeksin, kortin pinon ja pinon, johon 
     * kortti ollaan siirtämässä. Metodi kutsuu spiderin siirto-metodia, 
     * piirtureiden setValittuPois-metodia, sekä repaint-metodia. Jos peli on 
     * loppunut, ilmestyy kuvaruutuun popUp-ikkuna.
     * 
     * @param indeksi
     * @param pinosta
     * @param pinoon 
     */
    private void siirto(int indeksi, int pinosta, int pinoon) {
        spider.siirto(indeksi, pinosta, pinoon);
        for(Piirturi p: piirturit) {
            p.setValittuPois();
        }
        this.repaint();
        
        if(spider.peliLoppui()) {
            JOptionPane.showMessageDialog (null, "Onneksi olkoon!", 
                    "Peli loppui!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metodi ottaa parametriksi pinon indeksin ja palauttaa sen x-koordinaatin 
     * ikkunassa.
     * 
     * @param pino
     * @return 
     */
    private int pinonX(int pino) {
        int kortinLeveys = 72;
        return 50 + pino * kortinLeveys + pino * 25;
    }
}
