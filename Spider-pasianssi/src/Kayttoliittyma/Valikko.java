package Kayttoliittyma;

import Peli.Vaikeusaste;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Valikko extends JMenuBar {

    private JMenu valikko;
    private JMenu vaikeusasteValikko;
    private JMenuItem aloitusNappi;
    private JMenuItem jaaNappi;
    private ButtonGroup group;
    private Vaikeusaste vaikeusaste;
    private Kayttoliittyma kayttoliittyma;

    public Valikko(Kayttoliittyma kayttoliittyma) {
        super();
        this.kayttoliittyma = kayttoliittyma;

        valikko = new JMenu("Valikko");
        valikko.setMnemonic(KeyEvent.VK_A);
        this.add(valikko);

        // Luodaan valikon nappulat jne:
        luoAloitusNappi();

        valikko.addSeparator();

        luoVaikeusasteValikko();

        jaaNappi();

    }

    private void luoAloitusNappi() {
        aloitusNappi = new JMenuItem("Uusi peli", KeyEvent.VK_T);
//        aloitusNappi.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        aloitusNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                kayttoliittyma.luoAlusta(vaikeusaste);

            }
        });


        valikko.add(aloitusNappi);
    }

    private void luoVaikeusasteValikko() {
        // JMenu vaikeusasteValikko on siis ALIVALIKKO/SUBMENU!


        vaikeusasteValikko = new JMenu("Vaikeusasteet");
        vaikeusasteValikko.setMnemonic(KeyEvent.VK_S);

        group = new ButtonGroup();

        JRadioButtonMenuItem helppo = new JRadioButtonMenuItem("Helppo");
        helppo.setActionCommand("helppo");
        helppo.setMnemonic(KeyEvent.VK_R);

        JRadioButtonMenuItem normaali = new JRadioButtonMenuItem("Normaali");
        normaali.setActionCommand("normaali");
        normaali.setMnemonic(KeyEvent.VK_R);

        JRadioButtonMenuItem vaikea = new JRadioButtonMenuItem("Vaikea");
        vaikea.setActionCommand("vaikea");
        vaikea.setMnemonic(KeyEvent.VK_R);


        group.add(helppo);
        group.add(normaali);
        group.add(vaikea);


        vaikeusasteValikko.add(helppo);
        vaikeusasteValikko.add(normaali);
        vaikeusasteValikko.add(vaikea);


        ActionListener vaikeusasteenKuuntelija = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {


                if (ae.getActionCommand().equalsIgnoreCase("helppo")) {
                    vaikeusaste = Vaikeusaste.HELPPO;
                } else if (ae.getActionCommand().equalsIgnoreCase("normaali")) {
                    vaikeusaste = Vaikeusaste.NORMAALI;
                } else if (ae.getActionCommand().equalsIgnoreCase("vaikea")) {
                    vaikeusaste = Vaikeusaste.VAIKEA;
                }
            }
        };

        helppo.addActionListener(vaikeusasteenKuuntelija);
        normaali.addActionListener(vaikeusasteenKuuntelija);
        vaikea.addActionListener(vaikeusasteenKuuntelija);

        valikko.add(vaikeusasteValikko);
        helppo.doClick();


    }

    private void jaaNappi() {
        jaaNappi = new JMenuItem("JAA", KeyEvent.VK_T);
        jaaNappi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int jakoja = kayttoliittyma.jaaKortit();

                if (jakoja != 0) {
                    jaaNappi.setText("JAA " + jakoja);
                } else {
                    jaaNappi.setText("JAA");
                }
            }
        });


        this.add(jaaNappi);
    }
}
