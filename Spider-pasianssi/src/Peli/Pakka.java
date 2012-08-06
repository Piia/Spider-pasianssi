package Peli;

import Rajapinnat.KorttiRajapinta;
import Rajapinnat.PakkaRajapinta;
import Rajapinnat.PinoRajapinta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Pakka implements PakkaRajapinta {
    
    private ArrayList<KorttiRajapinta> pakka;
    private Vaikeusaste vaikeusaste;
    private List<PinoRajapinta> pinot;
    
    public Pakka(Vaikeusaste vaikeusaste, List<PinoRajapinta> pinot) {
        this.vaikeusaste = vaikeusaste;
        this.pinot = pinot;
        this.pakka = new ArrayList<KorttiRajapinta>();
        luoPakka();
        alustaPinot();
    }
    
    // Testaamista vasten konstruktori, jonka ei tarvitse tietää pinoista.
    public Pakka(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        this.pakka = new ArrayList<KorttiRajapinta>();
        luoPakka();
    }

    
    // Jakaa pinojen päälle yhden kortin.
    // Kortit luonnollisesti poistuvat pakasta.
    @Override
    public boolean jaa() {
        if(pakka.isEmpty()) {
            return false;
        }
        
        Iterator<KorttiRajapinta> pakkaIteraattori = pakka.iterator();
        
        for(PinoRajapinta pino: pinot) {
            KorttiRajapinta seuraavaKortti = pakkaIteraattori.next();
            pino.lisaa(seuraavaKortti);
            pakka.remove(seuraavaKortti);
        }
        return true;
    }
    
    // Palauttaa true, jos pakka on tyhjä.
    @Override
    public boolean onTyhja() {
        return pakka.isEmpty();
    }
    
    // TESTAAMISEEN: palauttaa pakan kortit ArrayListana.
    public ArrayList<KorttiRajapinta> getPakka() {
        return pakka;
    }

    // Alustaa pinot pelikuntoon lisäämällä aloitustilannetta vastaavat kortit.
    private void alustaPinot() {
        Iterator<KorttiRajapinta> pakkaIteraattori = pakka.iterator();
        KorttiRajapinta seuraavaKortti;
        
        // Kaikkiin pinoihin lisätään pakasta viisi korttia. 
        // Kortit luonnollisesti poistuvat pakasta.
        for(int i = 0; i < 5; i++) {
            for(PinoRajapinta pino: pinot) {
                seuraavaKortti = pakkaIteraattori.next();
                pino.lisaa(seuraavaKortti);
                pakka.remove(seuraavaKortti);
            }
        }
        
        // Neljään pinoon lisätään kuudes kortti.
        for(int i = 0; i < 4; i++) {
            seuraavaKortti = pakkaIteraattori.next();
            pinot.get(i).lisaa(seuraavaKortti);
            pakka.remove(seuraavaKortti);
        }
    }
    
    // Luo pakkaan kaikki kortit vaikeusasteen mukaan, yhteensä kahdeksan sarjaa:
    // HELPPO: Kahdeksan sarjaa PATAA.
    // NORMAALI: Neljä sarjaa PATAA ja HERTTAA.
    // VAIKEA: Jokaista maata kaksi sarjaa.
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
            
        }   else {
            for(int i = 0; i < 2; i++) {
                luoSarjaKortteja(Maa.PATA);
                luoSarjaKortteja(Maa.HERTTA);
                luoSarjaKortteja(Maa.RISTI);
                luoSarjaKortteja(Maa.RUUTU);
            }
        }
        
        Collections.shuffle(pakka);
    }
    
    // Luo pakkaan saman maan kortit ässästä kuninkaaseen.
    private void luoSarjaKortteja(Maa maa) {
        for(int i = 1; i <= 13; i++) {
            pakka.add(new Kortti(maa, i));
        }
    }

}
