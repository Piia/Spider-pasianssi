package Peli;

import Rajapinnat.KorttiRajapinta;
import Rajapinnat.PinoRajapinta;
import java.util.ArrayList;
import java.util.List;

/**
 * Pino sisältää listan KorttiRajapinnan toteuttavia kortteja
 * sekä metodit niiden lisäämiseen ja poistamiseen pelin sääntöjen 
 * mukaisesti.
 * 
 * @author Piia
 */

public class Pino implements PinoRajapinta {

    private ArrayList<KorttiRajapinta> pino;

    public Pino() {
        pino = new ArrayList<KorttiRajapinta>();

    }

    /**
     * Lisää pinoon KorttiRajapinnan toteuttavan kortin.
     * 
     * @param kortti 
     */
    @Override
    public void lisaa(KorttiRajapinta kortti) {
        pino.add(kortti);
    }

    /**
     * Lisää pinoon listan verran KorttiRajapinnan toteuttavia kortteja.
     * 
     * @param kortteja 
     */
    @Override
    public void lisaa(List<KorttiRajapinta> kortteja) {
        for (KorttiRajapinta kortti : kortteja) {
            pino.add(kortti);
        }
    }

    /**
     * Poistaa pinosta indeksin perusteella kortin/kortteja.
     * 
     * @param indeksi 
     */
    @Override
    public void poista(int indeksi) {
        while(pino.size() > indeksi) {
            pino.remove(indeksi);
        }
    }

    /**
     * Palauttaa true, jos kortti on päällimmäistä korttia yhtä pienempi.
     * 
     * @param kortti
     * @return 
     */
    @Override
    public boolean voikoLisata(KorttiRajapinta kortti) {
        if(pino.isEmpty()) {
            return true;
        }
        if (kortti.getArvo() == pino.get(pino.size()-1).getArvo() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa true, jos kortin voi poistaa, eli:
     * 1) kortti on päällimmäinen JA
     * 2) kortti on kuvapuoli ylöspäin JA
     * 3) se on samaa sarjaa yllään olevien korttien kanssa(ks. samaaSarjaa()).
     * 
     * @param indeksi
     * @return 
     */
    @Override
    public boolean voikoPoistaa(int indeksi) {
        if (indeksi < 0 || indeksi >= pino.size()) {
            return false;
        }
        if (getKortti(indeksi).getNakyvyys() == false) {
            return false;
        }
        
        if (indeksi == pino.size()-1) {
            return true;
        }

        if (samaaSarjaa(indeksi)) {
            return true;
        }

        return false;
    }

    /**
     * Palauttaa true, jos pino on tyhjä (pino.isEmpty()).
     * 
     * @return 
     */
    @Override
    public boolean pinoOnTyhja() {
        if (pino.isEmpty()) {
            return true;

        } else {
            return false;
        }
    }


    /**
     * Palauttaa pinon, eli ArrayListin KorttiRajapinta-olioita.
     * 
     * @return 
     */
    @Override
    public ArrayList<KorttiRajapinta> getPino() {
        ArrayList<KorttiRajapinta> uusiPino = new ArrayList<KorttiRajapinta>();
        
        for(KorttiRajapinta kortti: pino) {
            uusiPino.add(kortti);
        }
        return uusiPino;
    }
    
    /**
     * Palauttaa pinon päältä kortit ArrayListana indeksiin asti.
     * 
     * @param indeksi
     * @return 
     */
    @Override
    public ArrayList<KorttiRajapinta> getOsaPino(int indeksi) {
        ArrayList<KorttiRajapinta> osaPino = new ArrayList<KorttiRajapinta>();
        if(indeksi == pino.size()-1) {
            osaPino.add(pino.get(pino.size()-1));
            return osaPino;
        }
        
        for(int i = indeksi; i < pino.size(); i++) {
            osaPino.add(getKortti(i));
        }
        return osaPino;
    }
    
    /**
     * Palauttaa pinosta indeksin mukaisen kortin.
     * 
     * @param indeksi
     * @return 
     */
    @Override
    public KorttiRajapinta getKortti(int indeksi) {
        return pino.get(indeksi);
    }

    /**
     * Kääntää päällimmäisen kortin oikeinpäin ja tarkistaa, mikäli pinossa
     * olisi täysi sarja (=> poistaa sen).
     */
    @Override
    public void paivita() {
        if (taysiSarja()) {
            poista(pino.size()-1 - 12);
        }
        kaannaPaallimmainenKortti();
    }
    
    /**
     * Palauttaa pinon pituuden.
     * @return 
     */
    @Override
    public int getPituus() {
        return pino.size();
    }
    
    /**
     * Palauttaa indeksin kortista, joka on pinon alin mahdollinen poistettava.
     * 
     * @return 
     */
    @Override
    public int getAlinPoistettava() {
        int indeksi = -1;
        
        for(int i = pino.size()-1; i >= 0 ; i--) {
            if(voikoPoistaa(i)) {
                indeksi = i;
            }
        }
        
        return indeksi;
    }
    

    /**
     * Palauttaa true, mikäli pinon päällä on samaa maata ässästä kuninkaaseen.
     * 
     * @return 
     */
    private boolean taysiSarja() {
        if(pino.size() < 13) {
            return false;
        }
        if (pino.get(pino.size()-1).getArvo() != 1 || 
                getKortti(pino.size()-1 - 12).getNakyvyys() == false) {
            return false;
        }

        return samaaSarjaa(pino.size()-1 - 12);

    }
    

    /**
     * Palauttaa true, mikäli kortit ovat pinon päältä indeksiin asti samaa 
     * maata ja nousevat arvoltaan tasaisesti.
     * 
     * @param indeksi
     * @return 
     */
    private boolean samaaSarjaa(int indeksi) {
        // Maa on päällimmäisen kortin maa.
        Maa maa = pino.get(pino.size()-1).getMaa();

        for (int i = pino.size()-2; i >= indeksi; i--) {
            if (getKortti(i).getMaa() != maa || getKortti(i).getNakyvyys() == false) {
                return false;
            }
            if (getKortti(i).getArvo() != getKortti(i + 1).getArvo() + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kääntää päällimmäisen kortin kuvapuoli ylöspäin. (kortti.setNakyvaksi())
     */
    private void kaannaPaallimmainenKortti() {
        if(pino.isEmpty()) {
            return;
        }
        pino.get(pino.size()-1).setNakyvaksi();
    }

    

}
