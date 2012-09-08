package Peli;

import Rajapinnat.PakkaRajapinta;
import Rajapinnat.PinoRajapinta;
import Rajapinnat.SpiderRajapinta;
import java.util.ArrayList;

/**
 * Spider vastaa Spider-pasianssin sovelluslogiikasta. 
 * Spider luo konstruktorissaan pakan ja pinot ja muokkaa metodeillaan niiden
 * tilaa.  
 * 
 * @author Piia
 */

public class Spider implements SpiderRajapinta {
    
    private PakkaRajapinta pakka;
    private Vaikeusaste vaikeusaste;
    private ArrayList<PinoRajapinta> pinot;
    
    public Spider(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        pinot = new ArrayList<PinoRajapinta>();
        pakka = new Pakka(vaikeusaste);
        luoPinot();
    }

    
    /**
     * Palauttaa true, jos kaikki pinot ja pakka ovat tyhjiä.
     * @return 
     */
    @Override
    public boolean peliLoppui() {
        if(pakka.onTyhja() == false) {
            return false;
        }
        for(PinoRajapinta pino: pinot) {
            if(pino.pinoOnTyhja() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pinojen päälle jaetaan pakasta kortti.
     * Jos pakka on tyhjä, palautetaan false, muuten true.
     * 
     * @return 
     */
    @Override
    public boolean jaa() {
        if(pakka.onTyhja()) {
            return false;
        }
        for(PinoRajapinta pino: pinot) {
            pino.lisaa(pakka.annaKortti());
        }
        
        paivita();
        return true;
    }
    
    /**
     * Siirtää pinosta p1 pinoon p2 kortin/kortteja indeksin mukaan.
     * Aluksi tarkistetaan, voiko niin tehdä.
     * 
     * @param indeksi
     * @param p1
     * @param p2
     * @return 
     */
    @Override
    public boolean siirto(int indeksi, int p1, int p2) {
        PinoRajapinta pinosta = pinot.get(p1);
        PinoRajapinta pinoon = pinot.get(p2);
        
        if(pinosta.voikoPoistaa(indeksi) == false ||
                pinoon.voikoLisata(pinosta.getKortti(indeksi)) == false) {
            return false;
        }
        
        pinoon.lisaa(pinosta.getOsaPino(indeksi));
        pinosta.poista(indeksi);
        paivita();
        
        return true;
    }

    /**
     * Palauttaa ArrayListana PinoRajapinnan toteuttavia olioita.
     * @return 
     */
    @Override
    public ArrayList<PinoRajapinta> getPinot() {
        ArrayList<PinoRajapinta> uudetPinot = new ArrayList<PinoRajapinta>();
        
        for(PinoRajapinta pino: pinot) {
            uudetPinot.add(pino);
        }
        
        return uudetPinot;
    }
    
    /**
     * Kutsuu pinojen paivita()-metodia, joka tarkistaa, onko pinossa kokonainen
     * sarja kortteja ja kääntää tarvittaessa päällimmäisen kortin oikein
     * päin.
     */
    private void paivita() {
        for(PinoRajapinta pino: pinot) {
            pino.paivita();
        }
    }
    
    /**
     * Palauttaa pakka.montaJakoa(), joka palauttaa mahdollisten jakojen
     * kokonaislukumäärän.
     * @return 
     */
    @Override
    public int montaJakoa() {
        return pakka.montaJakoa();
    }
    
    
    /**
     * Luo kymmenen pino-oliota ja jakaa alkuasetelman kortin.
     */
    private void luoPinot() {
        for(int i = 0; i < 10; i++) {
            pinot.add(new Pino());
        }
        
        // Kaikkiin pinoihin lisätään pakasta viisi korttia. 
        // Kortit luonnollisesti poistuvat pakasta.
        for(int i = 0; i < 5; i++) {
            for(PinoRajapinta pino: pinot) {
                pino.lisaa(pakka.annaKortti());
            }
        }
        
        // Neljään pinoon lisätään kuudes kortti.
        for(int i = 0; i < 4; i++) {
            pinot.get(i).lisaa(pakka.annaKortti());
        }
        
        paivita();
    }


    
}
