package Peli;

public enum Maa {
    
    HERTTA,
    RUUTU, 
    RISTI,
    PATA;
    
    public String toString() {
        if(this == HERTTA) {
            return "HERTTA";
            
        }   else if(this == RUUTU) {
            return "RUUTU";
            
        }   else if(this == RISTI) {
            return "RISTI";
            
        }   else {
            return "PATA";
        }
    }
    
}
