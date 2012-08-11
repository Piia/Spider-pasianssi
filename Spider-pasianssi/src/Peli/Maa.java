package Peli;

public enum Maa {
    
    HERTTA,
    RUUTU, 
    RISTI,
    PATA;
    
    
    public String toString() {
        if(this == HERTTA) {
            return "\u2661";
                    
        }   else if(this == RUUTU) {
            return "\u2662";
                    
        }   else if(this == RISTI) {
            return "\u2663";
        }
        else {
            return "\u2660";
        }
    }
    
}
