package Peli;

import java.util.ArrayList;
import java.util.Collections;

public class Pakka {
    
    private ArrayList<KorttiRajapinta> kortit;
    private int vaikeusaste;
    
    public Pakka(int vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        this.kortit = new ArrayList<KorttiRajapinta>();
        luoPakka();
    }
    
    public ArrayList<KorttiRajapinta> getPakka() {
        return kortit;
    }
    
    private void luoPakka() {
        // Varmistetaan oikea vaikeusaste.
        if(vaikeusaste < 1 || vaikeusaste > 4 || vaikeusaste == 3) {
            System.out.println("PAKKA: VAIKEUSASTE VALITTU VÄÄRIN!!!");
            return;
        }
        
        for(int i = 0; i < 8/vaikeusaste; i++) {
            for(int j = 1; j <= 13; j++) {
                kortit.add(new Kortti(Maa.PATA, j));
                
                if(vaikeusaste > 1) {
                    kortit.add(new Kortti(Maa.HERTTA, j));
                    
                    if(vaikeusaste == 4) {
                        kortit.add(new Kortti(Maa.RUUTU, j));
                        kortit.add(new Kortti(Maa.RISTI, j));
                
                    }
                }
                
            }
        }
        
        Collections.shuffle(kortit);
    }
}
