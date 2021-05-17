package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Stack;

import Logic.*;

import javax.swing.*;
import static GUI.Sleep.*;

public class DVMMenu extends JFrame implements ActionListener {

    private Timer timer = new Timer(180000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            return_value = -2;
            timer.stop();
        }
    });

    private JButton[] DVM;
    private JButton cancel;
    private Stack<DVM> stk;
    public int return_value = -1;

    public DVMMenu(Stack<DVM> DVM_stack) {
        timer.start();
        stk = DVM_stack;

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM "+ CurrentID);
        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 30));
        JLabel label = new JLabel("선결제할 자판기를 고르세요");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
        //자판기패널
        JPanel DVMpanel = new JPanel(new GridLayout(10, 1));
        DVMpanel.setPreferredSize(new Dimension(600, 700));

        DVM = new JButton[10];
        for (int i = 0; i < stk.size(); i++) {
            DVM[i] = new JButton("DVM ID : " + stk.get(i).getID() + "      위치 : " +
                    DVM_stack.get(i).getAddress_X() + ", " + DVM_stack.get(i).getAddress_Y());
            DVM[i].addActionListener(this);
            DVMpanel.add(DVM[i]);
        }

        //취소패널
        JPanel cancelpanel = new JPanel();
        cancel = new JButton("취소");
        cancel.setPreferredSize(new Dimension(600, 70));
        cancel.addActionListener(this);
        cancelpanel.add(cancel, BorderLayout.SOUTH);

        add(labelpanel, BorderLayout.NORTH);
        add(DVMpanel, BorderLayout.CENTER);
        add(cancelpanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    /*test
    public static void main(String[] args) {
      DVM a=new DVM(1,1.0,1.0);
      DVM b=new DVM(2,2.0,2.0);
      DVM c=new DVM(3,3.0,3.0);
      DVM d=new DVM(4,4.0,4.0);
      Stack<DVM> darr=new Stack<DVM>();
      darr.push(a);darr.push(b);darr.push(c);darr.push(d);

      new DVMMenu(darr);


    }
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < stk.size(); i++) {
            if (e.getSource() == DVM[i]) {
                timer.stop();
                return_value = stk.get(i).getID();
                System.out.println(return_value);
                //this.setVisible(false);
            }
        }
        if (e.getSource() == cancel) {
            timer.stop();
            return_value = 0;
            //this.setVisible(false);
        }
    }
}

