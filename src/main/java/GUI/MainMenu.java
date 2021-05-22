package GUI;

import Logic.Title;
import Logic.DVM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenu extends JFrame implements ActionListener {

    private Timer timer = new Timer(180000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timer.stop();
            return_value = -2;
        }
    });

    private JButton[] menu;
    private JButton CnumberInput;
    private int return_value = -1;

    public MainMenu(ArrayList<Title> Title_List) {
        timer.start();

        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());

        //라벨 패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 30));
        JLabel label = new JLabel("음료를 선택하세요");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);

        //메뉴패널
        JPanel menupanel = new JPanel(new GridLayout(5, 4));
        menupanel.setPreferredSize(new Dimension(600, 700));
        menu = new JButton[Title_List.size()];
        for (int i = 0; i < menu.length; i++) {
            menu[i] = new JButton(Title_List.get(i).getName());
            menupanel.add(menu[i]);
            menu[i].addActionListener(this);
        }

        //추가패널
        JPanel addpanel = new JPanel(new GridLayout(1, 1));
        addpanel.setPreferredSize(new Dimension(600, 70));
        CnumberInput = new JButton("선결제 인증번호 입력");
        CnumberInput.addActionListener(this);
        addpanel.add(CnumberInput);

        add(labelpanel, BorderLayout.NORTH);
        add(menupanel, BorderLayout.CENTER);
        add(addpanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < menu.length; i++) {
            if (e.getSource() == menu[i]) {
                timer.stop();
                return_value = i + 1;

            }
        }
        if (e.getSource() == CnumberInput) {
            timer.stop();
            return_value = 0;
        }
    }

    public int getReturn_value() { return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }

}
