package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static GUI.Sleep.*;

public class InfoNoDVMUI extends JFrame {

    private JLabel label;
    private int s = 2;
    public int return_value = -1;

    public InfoNoDVMUI(String name) {
        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM "+ CurrentID);

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 300));
        label = new JLabel("3초 후 메인화면으로 돌아갑니다.");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
        //안내패널
        JPanel informpanel = new JPanel();
        //informpanel.setPreferredSize(new Dimension(600,300));
        JLabel infolabel = new JLabel("<html><center><strong>" + name + "</strong>"
                + "<br>해당 음료의 재고가 있는 자판기가 없습니다.</center></html>");
        infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
        informpanel.add(infolabel);

        add(labelpanel, BorderLayout.NORTH);
        add(informpanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
        public static void main(String[] args) {
            new InfoNoDVMUI("코카콜라");

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
