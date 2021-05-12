package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SmartPayUI extends JFrame implements ActionListener{
    private int s=23;
    private Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(s<=3){
                remove(buttonpanel);
                label.setText("남은 시간: 0");
                infolabel.setText("<html><center>입력시간이 초과되어 결제가 취소되었습니다.<br>"+
                        s+"초 후 메인화면으로 돌아갑니다.</center></html>");
                if(s==0){
                    return_value=0;
                    timer.stop();
                }
            }
            else
                label.setText("남은 시간: "+(s-3));
            s--;
        }
    });

    private JButton cancel;
    private JLabel label;
    private JLabel infolabel;
    private JPanel buttonpanel;
    public int return_value= -1;

    public SmartPayUI(){
        this.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar()=='s')
                return_value=1;
            else if(e.getKeyChar()=='f')
                return_value=-3;
        }
    });
        timer.start();
        this.setPreferredSize(new Dimension(600,800));
        this.setTitle("DVM");

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600,300));
        label = new JLabel();
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
        //안내패널
        JPanel informpanel = new JPanel();
        //informpanel.setPreferredSize(new Dimension(600,300));
        infolabel = new JLabel("간편결제");
        infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
        informpanel.add(infolabel);
        //버튼패널
        buttonpanel = new JPanel();
        cancel =new JButton("취소");
        cancel.setPreferredSize(new Dimension(300,70));
        cancel.addActionListener(this);
        cancel.setFocusable(false);
        buttonpanel.add(cancel);

        add(labelpanel,BorderLayout.NORTH);
        add(informpanel,BorderLayout.CENTER);
        add(buttonpanel,BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    /*test
    public static void main(String[] args) {
        new SmartPayUI();

    }

     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel) {
            return_value = 0;
            timer.stop();
        }
    }
}
