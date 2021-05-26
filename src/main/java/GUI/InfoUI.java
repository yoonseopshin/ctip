package GUI;

import Logic.DVM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoUI extends JFrame implements ActionListener {

    private int s = 9;
    private Timer timer;
    private JButton confirm;
    private JLabel label;
    private int return_value = -1;

    public InfoUI(String str, String str2) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(s + "초 후 " + str2 + " 화면으로 돌아갑니다.");
                if (s == 0) {
                    timer.stop();
                    return_value = 0;
                }
                s--;
            }
        });
        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 300));
        label = new JLabel("10초 후 " + str2 + " 화면으로 돌아갑니다.");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);

        //안내패널
        JPanel informpanel = new JPanel();
        JLabel infolabel = new JLabel(str);
        infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
        informpanel.add(infolabel);

        //버튼패널
        JPanel buttonpanel = new JPanel();
        confirm = new JButton("확인");
        confirm.setPreferredSize(new Dimension(300, 70));
        confirm.addActionListener(this);
        buttonpanel.add(confirm);

        add(labelpanel, BorderLayout.NORTH);
        add(informpanel, BorderLayout.CENTER);
        add(buttonpanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {
            timer.stop();
            return_value = 0;
        }
    }

    public int getReturn_value() { return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }

}
