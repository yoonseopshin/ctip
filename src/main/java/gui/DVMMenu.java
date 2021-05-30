package gui;

import logic.DVM;
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
      returnValue = -2;
    }
  });

  private JButton[] DVMList;
  private JButton cancel;
  private transient Stack<DVM> stk;
  private int returnValue = -1;

  public DVMMenu(Stack<DVM> DVMStack) {
    timer.start();
    stk = DVMStack;

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 30));
    JLabel label = new JLabel("선결제할 자판기를 고르세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //자판기패널
    JPanel DVMPanel = new JPanel(new GridLayout(10, 1));
    DVMPanel.setPreferredSize(new Dimension(600, 700));
    DVMList = new JButton[10];
    for (int i = 0; i < stk.size(); i++) {
      DVMList[i] = new JButton("DVM ID : " + stk.get(i).getId() + "      위치 : " +
          DVMStack.get(i).getAddressX() + ", " + DVMStack.get(i).getAddressY());
      DVMList[i].addActionListener(this);
      DVMPanel.add(DVMList[i]);
    }

    //취소패널
    JPanel cancelPanel = new JPanel();
    cancel = new JButton("취소");
    cancel.setPreferredSize(new Dimension(600, 70));
    cancel.addActionListener(this);
    cancelPanel.add(cancel, BorderLayout.SOUTH);

    add(labelPanel, BorderLayout.NORTH);
    add(DVMPanel, BorderLayout.CENTER);
    add(cancelPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < stk.size(); i++) {
      if (e.getSource() == DVMList[i]) {
        timer.stop();
        returnValue = stk.get(i).getId();
        System.out.println(returnValue);
      }
    }
    if (e.getSource() == cancel) {
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

