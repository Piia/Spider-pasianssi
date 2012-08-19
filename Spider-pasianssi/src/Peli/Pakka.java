package Peli;

import Rajapinnat.KorttiRajapinta;
import Rajapinnat.PakkaRajapinta;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Pakka koostuu listasta KorttiRajapinnan toteuttavia kortteja.
 * Pakan kortit ovat vaikeusasteesta riippuen joko yhtä, kahta tai neljää maata.
 * 
 * @author Piia
 */

public class Pakka implements PakkaRajapinta {
    
    private ArrayList<KorttiRajapinta> pakka;
    private Vaikeusaste vaikeusaste;
    
    public Pakka(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        this.pakka = new ArrayList<KorttiRajapinta>();
        luoPakka();
    }
    
    /**
     * Palauttaa true, jos pakka on tyhjä.
     * @return 
     */
    @Override
    public boolean onTyhja() {
        return pakka.isEmpty();
    }
    
    /**
     * Palauttaa pakasta KorttiRajapinnan toteuttavan kortin poistaen sen samalla.
     * @return 
     */
    @Override
    public KorttiRajapinta annaKortti() {
        KorttiRajapinta kortti = pakka.get(pakka.size()-1);
        pakka.remove(pakka.size()-1);
        return kortti;
    }
    
    /**
     * Palauttaa kokonaisluvun, joka kertoo montako kertaa pakasta voi vielä 
     * jakaa pinoihin.
     * @return 
     */
    @Override
    public int montaJakoa() {
        return pakka.size()/10;
    }
    
    /**
     * LÄHINNÄ TESTAAMISEEN: palauttaa pakan kortit ArrayListana.
     * @return 
     */
    public ArrayList<KorttiRajapinta> getPakka() {
        ArrayList<KorttiRajapinta> uusiPakka = new ArrayList<KorttiRajapinta>();
        
        for(KorttiRajapinta kortti: pakka) {
            uusiPakka.add(kortti);
        }
        
        return uusiPakka;
    }
    
    /**
     * Luo pakkaan kaikki kortit vaikeusasteen mukaan, yhteensä kahdeksan sarjaa:
     * HELPPO: Kahdeksan sarjaa PATAA.
     * NORMAALI: Neljä sarjaa PATAA ja HERTTAA.
     * VAIKEA: Jokaista maata kaksi sarjaa.
     */
    private void luoPakka() {
        
        if(vaikeusaste == Vaikeusaste.HELPPO) {
            for(int i = 0; i < 8; i++) {
                luoSarjaKortteja(Maa.PATA);
            }
            
        }   else if(vaikeusaste == Vaikeusaste.NORMAALI) {
            for(int i = 0; i < 4; i++) {
                luoSarjaKortteja(Maa.PATA);
                luoSarjaKortteja(Maa.HERTTA);
            }
            
        }   else if(vaikeusaste == Vaikeusaste.VAIKEA) {
            for(int i = 0; i < 2; i++) {
                luoSarjaKortteja(Maa.PATA);
                luoSarjaKortteja(Maa.HERTTA);
                luoSarjaKortteja(Maa.RISTI);
                luoSarjaKortteja(Maa.RUUTU);
            }
        }   else {
            assert(false) : "Vaikeusastetta ei ole annettu!";
        }
        
        Collections.shuffle(pakka);
    }
    
    /**
     * Luo pakkaan saman maan kortit ässästä kuninkaaseen.
     * 
     * @param maa 
     */
    private void luoSarjaKortteja(Maa maa) {
        for(int i = 1; i <= 13; i++) {
            pakka.add(new Kortti(maa, i));
        }
    }

}
