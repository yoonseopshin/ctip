package gui;

import logic.Title;
import logic.DVM;
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
      returnValue = -2;
    }
  });

  private JButton[] menu;
  private JButton cNumberInput;
  private int returnValue = -1;

  public MainMenu(ArrayList<Title> Title_List) {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨 패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 30));
    JLabel label = new JLabel("음료를 선택하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //메뉴패널
    JPanel menuPanel = new JPanel(new GridLayout(5, 4));
    menuPanel.setPreferredSize(new Dimension(600, 700));
    menu = new JButton[Title_List.size()];
    for (int i = 0; i < menu.length; i++) {
      menu[i] = new JButton(Title_List.get(i).getName());
      menuPanel.add(menu[i]);
      menu[i].addActionListener(this);
    }

    //추가패널
    JPanel addPanel = new JPanel(new GridLayout(1, 1));
    addPanel.setPreferredSize(new Dimension(600, 70));
    cNumberInput = new JButton("선결제 인증번호 입력");
    cNumberInput.addActionListener(this);
    addPanel.add(cNumberInput);

    add(labelPanel, BorderLayout.NORTH);
    add(menuPanel, BorderLayout.CENTER);
    add(addPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < menu.length; i++) {
      if (e.getSource() == menu[i]) {
        timer.stop();
        returnValue = i + 1;

      }
    }
    if (e.getSource() == cNumberInput) {
      timer.stop();
      returnValue = 0;
    }
  }

  public int getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(int returnValue) {
    this.returnValue = returnValue;
  }

}
