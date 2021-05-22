package GUI;

import Logic.DVM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SmartPayUI extends JFrame implements ActionListener {

    private int s = 39;
    private Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (s <= 10) {
                cancel.setText("확인");
                label.setText("남은 시간: 0");
                infolabel.setText("<html><center>입력시간이 초과되어 결제가 취소되었습니다.<br>" +
                        s + "초 후 메인화면으로 돌아갑니다.</center></html>");
                if (s == 0) {
                    timer.stop();
                    return_value = 0;
                }
            } else {
                label.setText("남은 시간: " + (s - 10));
            }
            s--;
        }
    });

    private JButton cancel;
    private JLabel label;
    private JLabel infolabel;
    private JPanel buttonpanel;
    private int return_value = -1;

    public SmartPayUI() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (s > 10) {
                    if (e.getKeyChar() == 's') {
                        timer.stop();
                        return_value = 1;
                    } else if (e.getKeyChar() == 'f') {
                        timer.stop();
                        return_value = -3;
                    }
                }
            }
        });

        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 300));
        label = new JLabel("남은 시간: " + 30);
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);

        //안내패널
        JPanel informpanel = new JPanel();
        infolabel = new JLabel("간편결제");
        infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
        informpanel.add(infolabel);

        //버튼패널
        buttonpanel = new JPanel();
        cancel = new JButton("취소");
        cancel.setPreferredSize(new Dimension(300, 70));
        cancel.addActionListener(this);
        cancel.setFocusable(false);
        buttonpanel.add(cancel);

        add(labelpanel, BorderLayout.NORTH);
        add(informpanel, BorderLayout.CENTER);
        add(buttonpanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            timer.stop();
            return_value = 0;
        }
    }

    public int getReturn_value() { return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }

}
