package sejt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class sejtAlap extends JFrame implements ActionListener {
    private static int hossz = 10;
    private JButton btGomb[][] = new JButton[hossz][hossz];
    private final JButton btStart = new JButton("Új játék indítása");
    private final JButton btRandom = new JButton("Lakosság növelése");
    private final JButton btBeallit = new JButton("Kattints, ha beállítottad!");
    private final JLabel lbMeret = new JLabel("Méret: ");
    private final JComboBox cbMeret = new JComboBox(new String[]{"10*10", "20*20", "30*30", "40*40", "50*50"});
    private final JPanel pnJatekTer = new JPanel(new GridLayout(hossz, hossz));
    private final Color eloSzin = Color.ORANGE;
    private final Color haldokloSzin = Color.LIGHT_GRAY;
    private final Color halottSzin = Color.WHITE;
    private final Color szuletoSzin = Color.BLUE;
    private int szamlalo=0;
        
    public static void main(String[] args) {
        sejtAlap sejt = new sejtAlap();
    }
    
    public sejtAlap() {
        inicializal(hossz);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btAkt = (JButton) e.getSource();
        if (btAkt == btStart) {
            hossz = (cbMeret.getSelectedIndex() + 1) * 10;
            meretetAllit(hossz);
        } 
        else if (btAkt == btRandom) {
            noveles();
        }
        else if (btAkt == btBeallit){
            eletJateka();
            szamlalo+=1;
        }
        else if (btAkt.getBackground().equals(eloSzin)){
            btAkt.setBackground(halottSzin);
        }
        else
            btAkt.setBackground(eloSzin);
    }

    private void meretetAllit(int hossz) {
        pnJatekTer.removeAll(); //gombok kiürítése
        pnJatekTer.setLayout(new GridLayout(hossz, hossz));
        btGomb = new JButton[hossz][hossz];
        gombokLetrehozasa();
        revalidate();
        ujrakezd();
    }

    private void inicializal(int hossz) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sejtautomaták");
        setSize(600, 650);
//        setResizable(false);
        setLocationRelativeTo(this);
        JPanel pnAlap = new JPanel();
        pnAlap.add(btStart);
        pnAlap.add(btRandom);
        pnAlap.add(btBeallit);
        pnAlap.add(lbMeret);
        pnAlap.add(cbMeret);
        btStart.addActionListener(this);
        btBeallit.addActionListener(this);
        btRandom.addActionListener(this);
        add(pnAlap, BorderLayout.NORTH);
        gombokLetrehozasa();
        add(pnJatekTer);
        setVisible(true);
        ujrakezd();
    }

    private void gombokLetrehozasa() {
        for (int i = 0; i < hossz; i++) {
            for (int j = 0; j < hossz; j++) {
                btGomb[i][j] = new JButton();
                btGomb[i][j].addActionListener(this);
                pnJatekTer.add(btGomb[i][j]);
            }
        }
    }

    private void ujrakezd() {
        for (int i = 0; i < hossz; i++) {
            for (int j = 0; j < hossz; j++) {
                btGomb[i][j].setBackground(halottSzin);
            }
        }
    }

    public void eletJateka() {
        
        double bhatar=0;
        double jhatar=9;
        double fhatar=9;
        double ahatar=0;
        if (szamlalo%2==1){
        for (int y=0;y<btGomb.length;y++){
            for (int x=0;x<btGomb.length;x++){
                if (btGomb[x][y].getBackground()==szuletoSzin){
                    btGomb[x][y].setBackground(eloSzin);
                }
                if (btGomb[x][y].getBackground()==haldokloSzin){
                    btGomb[x][y].setBackground(halottSzin);
                }
            }
        }
        }
         if (szamlalo%2==0){
        for (int x=0;x<btGomb.length;x++){
            for (int y=0;y<btGomb.length;y++){
                
                //if (y%10!=bhatar && y%10!=jhatar && x%10!=fhatar && x%10!=ahatar){
                    int valtozas=szomszedSzamol(x, y);
                    //System.out.println(btGomb[x][y].getBackground());
                    if (valtozas<2 && btGomb[x][y].getBackground()==eloSzin){
                        btGomb[x][y].setBackground(haldokloSzin);
                    }
                    else if(valtozas>=4 && btGomb[x][y].getBackground()==eloSzin){
                        btGomb[x][y].setBackground(haldokloSzin);
                    }
                    else if(valtozas==3 && btGomb[x][y].getBackground()==halottSzin){
                        btGomb[x][y].setBackground(szuletoSzin);
                    }
                //}
            }
        }
        }
    }
    
    private int szomszedSzamol(int x, int y) {
        int osszesen=0;
        if (y-1>=0){
            if (x-1>=0){
                if (btGomb[x-1][y-1].getBackground()==eloSzin || btGomb[x-1][y-1].getBackground()==haldokloSzin){
                   osszesen+=1;
                }
            }
            if (btGomb[x][y-1].getBackground()==eloSzin || btGomb[x][y-1].getBackground()==haldokloSzin){
                osszesen+=1;
            }
            if (x+1<10){
                if (btGomb[x+1][y-1].getBackground()==eloSzin || btGomb[x+1][y-1].getBackground()==haldokloSzin){
                    osszesen+=1;
                }
            }
        }
        if (y+1<10){
            if (x+1<10){
                if (btGomb[x+1][y+1].getBackground()==eloSzin || btGomb[x+1][y+1].getBackground()==haldokloSzin){
                    osszesen+=1;
                }
            }
            if (btGomb[x][y+1].getBackground()==eloSzin || btGomb[x][y+1].getBackground()==haldokloSzin){
                osszesen+=1;
            }
            if (x-1>=0){
                if (btGomb[x-1][y+1].getBackground()==eloSzin || btGomb[x-1][y+1].getBackground()==haldokloSzin){
                    osszesen+=1;
                }
            }
        }
        if (x-1>=0){
            if (btGomb[x-1][y].getBackground()==eloSzin || btGomb[x-1][y].getBackground()==haldokloSzin){
                osszesen+=1;
            }
        }
        if (x+1<10){
            if (btGomb[x+1][y].getBackground()==eloSzin || btGomb[x+1][y].getBackground()==haldokloSzin){
                osszesen+=1;
            }
        }
        return osszesen;
    }

    private void noveles() {
        Random rnd=new Random();
        for (int k=0;k<10;k++){
            btGomb[rnd.nextInt(0,btGomb.length)][rnd.nextInt(btGomb.length)].setBackground(eloSzin);
        }
    }
}
