package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class CardPayUI extends JFrame implements ActionListener{
    private int s=23;
    private Scanner sc;
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
                    sc.close();
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

    public CardPayUI(){
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
        infolabel = new JLabel("카드를 카드 투입구에 넣어주세요");
        infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
        informpanel.add(infolabel);
        //버튼패널
        buttonpanel = new JPanel();
        cancel =new JButton("취소");
        cancel.setPreferredSize(new Dimension(300,70));
        cancel.addActionListener(this);
        buttonpanel.add(cancel);

        add(labelpanel,BorderLayout.NORTH);
        add(informpanel,BorderLayout.CENTER);
        add(buttonpanel,BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        if(return_value==-1) {
            int ID;
            sc = new Scanner(System.in);
            ID = sc.nextInt();
            sc.close();
            if (ID == 1)
                return_value=ID;
            else
                return_value=-3;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new CardPayUI();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel) {
            return_value = 0;
            timer.stop();
            sc.close();
            this.setVisible(false);
        }
    }
}
