package Peli;

import Rajapinnat.KorttiRajapinta;
import Rajapinnat.PakkaRajapinta;
import java.util.ArrayList;
import java.util.Collections;

public class Pakka implements PakkaRajapinta {
    
    private ArrayList<KorttiRajapinta> pakka;
    private Vaikeusaste vaikeusaste;
    
    public Pakka(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        this.pakka = new ArrayList<KorttiRajapinta>();
        luoPakka();
    }
    
    // Palauttaa true, jos pakka on tyhjä.
    @Override
    public boolean onTyhja() {
        return pakka.isEmpty();
    }
    
    // Palauttaa pakasta KorttiRajapinnan toteuttavan kortin poistaen sen samalla.
    @Override
    public KorttiRajapinta annaKortti() {
        KorttiRajapinta kortti = pakka.get(pakka.size()-1);
        pakka.remove(pakka.size()-1);
        return kortti;
    }
    
    // TESTAAMISEEN: palauttaa pakan kortit ArrayListana.
    public ArrayList<KorttiRajapinta> getPakka() {
        ArrayList<KorttiRajapinta> uusiPakka = new ArrayList<KorttiRajapinta>();
        
        for(KorttiRajapinta kortti: pakka) {
            uusiPakka.add(kortti);
        }
        
        return uusiPakka;
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
