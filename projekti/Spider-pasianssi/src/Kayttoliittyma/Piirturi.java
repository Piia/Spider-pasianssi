package Kayttoliittyma;

import Peli.Maa;
import Rajapinnat.KorttiRajapinta;
import Rajapinnat.PinoRajapinta;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * Piirturi perii Canvas-luokan. Se huolehtii oman PinoRajapinnan toteuttavan 
 * pinon piirtämisestä. Sisältää aliluokan, Hiirenkuuntelijan.
 * 
 * @author Piia
 */

public class Piirturi extends Canvas {
    
    private PinoRajapinta pino;
    private Alusta alusta;
    
    private int pinonNRO;
    private int alinValittava;
    private int valittu;
    
    private Color keltainen;
    private Color sininen;
    private Color vihrea;
    
    /**
     * Konstruktori ottaa parametreina Alusta ja PinoRajapinta -oliot sekä oman
     * pinonsa "numeron", joka itseasiassa on olion indeksi Alustan Piirtureita
     * sisältävän ArrayList-listassa.
     * 
     * @param alusta
     * @param pino
     * @param pinonNRO 
     */
    public Piirturi(Alusta alusta, PinoRajapinta pino, int pinonNRO) {
        this.alusta = alusta;
        this.addMouseListener(new Hiirenkuuntelija());
        this.pino = pino;
        this.pinonNRO = pinonNRO;
        
        this.keltainen = Color.YELLOW;
        this.sininen = Color.BLUE;
        this.vihrea = new Color(34, 139, 34);
        
        this.alinValittava = -1;
        this.valittu = -1;
    }
    
    /**
     * Peritty paint-metodi ottaa parametrikseen Graphics-olion. 
     * Metodi kutsuu tämän luokan metodia piirraKortti.
     * 
     * @param g 
     */
    @Override 
    public void paint(Graphics g) {
        this.setBackground(vihrea); 
        for(int i = 0; i < pino.getPituus(); i++) {
            piirraKortti(g, i);
        }
        
    }
    
    /**
     * Antaa muuttujalle valittu arvon -1, jolloin piirraKortti-metodi tietää, 
     * että mitään korttia ei ole valittu tällä hetkellä.
     */
    public void setValittuPois() {
        valittu = -1;
    }
    
    /**
     * Piirtää pinon kortin indeksin perusteella. Ottaa parametrikseen 
     * Graphics-olion ja kortin indeksi. 
     * 
     * Ennen varsinaista piirtämistä kortin kuva etsitään ja ladataan tiedostosta.
     * 
     * Mikäli pelin pelaaja on valinnut kortteja tai pitää hiirtä pinon yllä, 
     * korttien ympärille piirretään sininen tai keltainen reunus.
     * 
     * @param g
     * @param indeksi 
     */
    private void piirraKortti(Graphics g, int indeksi) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(etsiKuvanOsoite(pino.getKortti(indeksi))));

        } catch (IOException e) {
            System.out.println("Kuvaa ei löydetty");
            return;
        }
        
        int kortinY = kortinY(indeksi);
        
        if(alinValittava != -1 && indeksi >= alinValittava) {
            g.setColor(keltainen);
            
            if(indeksi < pino.getPituus()-1) {
                g.drawRect(14, kortinY-1, 74, 20);
            } else {
                g.drawRect(14, kortinY-1, 74, 98);
            }
            
        } else if(valittu != -1 && indeksi >= valittu) {
            g.setColor(sininen);
            if(indeksi < pino.getPituus()-1) {
                g.drawRect(14, kortinY-1, 74, 20);
            } else {
                g.drawRect(14, kortinY-1, 74, 98);
            }
        }
        g.drawImage(img, 15, kortinY(indeksi), null);
    }
    
    /**
     * Etsii KorttiRajapinnan toteuttavaa korttia vastaavan kuvatiedoston osoitteen.
     * Palauttaa osoitteen String-muodossa.
     * 
     * @param kortti
     * @return 
     */
    private String etsiKuvanOsoite(KorttiRajapinta kortti) {
        String kuvakansionOsoite = null;
        try {
            kuvakansionOsoite = new java.io.File(".").getCanonicalPath()
                    + "/src/classic-cards/";
        } catch (Throwable t) {
        }

        if (kortti.getNakyvyys() == false) {
            return kuvakansionOsoite + "b2fv.png";
        }

        int numero = 4;
        if (kortti.getArvo() == 1) {
            numero -= getKuvanMaaNumero(kortti.getMaa());
            return kuvakansionOsoite + numero + ".png";
        }

        numero = 52;
        numero -= (kortti.getArvo() - 2) * 4;
        numero -= getKuvanMaaNumero(kortti.getMaa());

        return kuvakansionOsoite + numero + ".png";
    }

    /**
     * Palauttaa kortin maata vastaavan numeron, joka auttaa korttia vastaavan 
     * kuvatiedoston löytämisessä.
     * 
     * @param maa
     * @return 
     */
    private int getKuvanMaaNumero(Maa maa) {
        if (maa == Maa.RISTI) {
            return 3;
        } else if (maa == Maa.PATA) {
            return 2;
        } else if (maa == Maa.HERTTA) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Palauttaa piirtämistä varten kortin indeksiä vastaavan y-koordinaatin 
     * ikkunassa.
     * 
     * @param indeksi
     * @return 
     */
    private int kortinY(int indeksi) {
        return indeksi * 20 + 10;
    }    
    
    
    
    /**
     * Hiirrenkuuntelija toimii Piirturin aliluokkana ja perii MouseAdapterin. 
     */
    class Hiirenkuuntelija extends MouseAdapter {

        /**
         * Peritty mouseClicked-metodi saa parametrina MouseEvent-olion, joka 
         * tarjoaa klikkauksen koordinaateille sopivat getterit.
         * 
         * Jos klikkaus osui pinon kortin päälle, valittu-muuttuja saa arvokseen 
         * kortin indeksin. Metodi kutsuu alustan korttiValittu-metodia sekä 
         * repaint-metodia uudelleenpiirtämistä varten.
         * @param me 
         */
        @Override
        public void mouseClicked(MouseEvent me) {
            int hiiriY = me.getY();
            
            if(hiiriY > kortinY(pino.getPituus()-1) && hiiriY < 
                    kortinY(pino.getPituus()-1) + 98) {
                valittu = pino.getPituus()-1;
                
            }   else { 
                for(int i = 0; i < pino.getPituus()-1; i++) {
                    if(hiiriY > kortinY(i) && hiiriY < kortinY(i+1)) { 
                        valittu = i;
                    }
                }
            }
            
            alinValittava = -1;
            alusta.korttiValittu(pinonNRO, valittu);
            repaint();
        }

        /**
         * Peritty mouseEntered-metodi ottaa parametrikseen MouseEvent-olion.
         * 
         * Kun pelaaja siirtää hiiren pinon päälle, metodi kutsuu pino-luokan 
         * getAlinPoistettava-metodia, joka palauttaa alimman poistettavan 
         * kortin indeksiä. Indeksi tallennetaan muuttujaan alinValittava ja 
         * sen jälkeen metodi kutsuu repaint-metodia uudelleen piirtämistä 
         * varten.
         * 
         * @param me 
         */
        @Override
        public void mouseEntered(MouseEvent me) {
            alinValittava = pino.getAlinPoistettava();
            repaint();
        }

        /**
         * Peritty mouseExited-metodi ottaa parametrikseen MouseEvent-olion.
         * 
         * Kun pelaaja siirtää pinon päällä olleen hiiren pois pinon päältä, 
         * sijoitetaan alinValittava-muuttujan arvoksi -1 ja kutsutaan 
         * repaint-metodia uudelleen piirtämistä varten.
         * 
         * @param me 
         */
        @Override
        public void mouseExited(MouseEvent me) {
            alinValittava = -1;
            repaint();
            
        }


        
        
        
        
    }
}
