package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Logic.*;
import static GUI.Sleep.*;

public class PrintCnumberUI extends JFrame {

    private JLabel label;
    private int s = 4;
    public int return_value = -1;

    public PrintCnumberUI(Title t, int DVMid, int Cnumber) {
        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM "+ CurrentID);

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 300));
        label = new JLabel("5초 후 메인화면으로 돌아갑니다.");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
        //안내패널
        JPanel informpanel = new JPanel();
        //informpanel.setPreferredSize(new Dimension(600,300));
        JLabel infolabel = new JLabel("<html><center>제품: " + t.getName() + "   DVM ID: " + DVMid +
                "<br>인증번호:" + Cnumber + "</center></html>");
        infolabel.setFont(infolabel.getFont().deriveFont(30.0f));
        informpanel.add(infolabel);

        add(labelpanel, BorderLayout.NORTH);
        add(informpanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
/*
    public static void main(String[] args) {
        new PrintCnumberUI(new Title("appke",1), 1,111111);

    }
*/

    private Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            label.setText(s + "초 후 메인화면으로 돌아갑니다.");
            if (s == 0) {
                return_value = 0;
                timer.stop();
            }
            s--;
        }
    });
}
