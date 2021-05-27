package GUI;

import Logic.DVM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DVMMenu extends JFrame implements ActionListener {

    private Timer timer = new Timer(180000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            return_value = -2;
        }
    });

    private JButton[] DVMlist;
    private JButton cancel;
    private transient Stack<DVM> stk;
    private int return_value = -1;

    public DVMMenu(Stack<DVM> DVM_stack) {
        timer.start();
        stk = DVM_stack;

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());

        //라벨패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 30));
        JLabel label = new JLabel("선결제할 자판기를 고르세요");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);

        //자판기패널
        JPanel DVMpanel = new JPanel(new GridLayout(10, 1));
        DVMpanel.setPreferredSize(new Dimension(600, 700));
        DVMlist = new JButton[10];
        for (int i = 0; i < stk.size(); i++) {
            DVMlist[i] = new JButton("DVM ID : " + stk.get(i).getID() + "      위치 : " +
                    DVM_stack.get(i).getAddress_X() + ", " + DVM_stack.get(i).getAddress_Y());
            DVMlist[i].addActionListener(this);
            DVMpanel.add(DVMlist[i]);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < stk.size(); i++) {
            if (e.getSource() == DVMlist[i]) {
                timer.stop();
                return_value = stk.get(i).getID();
                System.out.println(return_value);
            }
        }
        if (e.getSource() == cancel) {
            timer.stop();
            return_value = 0;
        }
    }

    public int getReturn_value() { return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }
}

