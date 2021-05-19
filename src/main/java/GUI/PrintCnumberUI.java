package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import Logic.DVM;

public class PrintCnumberUI extends JFrame {
    
    private int s = 4;
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
    
    private JLabel label;
    private int return_value = -1;

    public PrintCnumberUI(String name, int DVMid, int Cnumber) {
        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 300));
        label = new JLabel("5초 후 메인화면으로 돌아갑니다.");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
       
        //안내패널
        JPanel informpanel = new JPanel();
        JLabel infolabel = new JLabel("<html><center>제품: " + name + "   DVM ID: " + DVMid +
                "<br>인증번호:" + Cnumber + "</center></html>");
        infolabel.setFont(infolabel.getFont().deriveFont(30.0f));
        informpanel.add(infolabel);

        add(labelpanel, BorderLayout.NORTH);
        add(informpanel, BorderLayout.CENTER);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public int getReturn_value() {return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }

}
