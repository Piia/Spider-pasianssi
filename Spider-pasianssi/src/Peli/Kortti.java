package Peli;

import Rajapinnat.KorttiRajapinta;

public class Kortti implements KorttiRajapinta {
    
    private Maa maa;
    private int arvo;
    private boolean nakyvyys;
    
    public Kortti(Maa maa, int arvo) {
        this.maa = maa;
        this.arvo = arvo;
        this.nakyvyys = false;
    }

    @Override
    public Maa getMaa() {
        return maa;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kortti other = (Kortti) obj;
        if (this.maa != other.maa) {
            return false;
        }
        if (this.arvo != other.arvo) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public int getArvo() {
        return arvo;
    }

    @Override
    public boolean getNakyvyys() {
        return nakyvyys;
    }

    @Override
    public void setNakyvaksi() {
        nakyvyys = true;
    }
    
    @Override
    public String toString() {
        String jono = "";
        
        if(nakyvyys == false) {
            jono += "\u266F";
            
        }   else {
            jono += maa.toString();

            if(arvo == 11) {
                jono += "J";

            }   else if(arvo == 12) {
                jono += "D";

            }   else if(arvo == 13) {
                jono += "K";

            }   else if(arvo == 10) {
                jono += "10";

            }   else {
                jono += arvo + "";
            }
        }
        
        return String.format("%-4s", jono);
    }
    
}
