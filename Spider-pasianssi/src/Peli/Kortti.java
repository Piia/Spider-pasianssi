package Peli;

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
    public int getArvo() {
        return arvo;
    }

    @Override
    public boolean getNakyvyys() {
        return nakyvyys;
    }

    @Override
    public void setNakyvyys(boolean haluttu) {
        nakyvyys = haluttu;
    }
    
    @Override
    public String toString() {
        return maa.toString() + arvo;
    }
    
}
