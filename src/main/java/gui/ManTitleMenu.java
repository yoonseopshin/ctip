package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import logic.Title;
import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManTitleMenu extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      returnValue = -2;
    }
  });

  private JButton[] titleList;
  private JButton exit;
  private transient ArrayList<Title> temp;
  private int returnValue = -1;

  public ManTitleMenu(ArrayList<Title> tList) {
    timer.start();
    temp = tList;

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨 패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 30));
    JLabel label = new JLabel("재고를 변경할 음료를 선택하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //타이틀 패널
    JPanel titleListPanel = new JPanel(new GridLayout(tList.size(), 1));
    JScrollPane titlePanel = new JScrollPane(titleListPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    titlePanel.getVerticalScrollBar().setUnitIncrement(8);
    titleListPanel.setPreferredSize(new Dimension(600, 70 * tList.size()));
    titleList = new JButton[tList.size()];
    for (int i = 0; i < tList.size(); i++) {
      titleList[i] = new JButton(
          "<html><center><strong>" + tList.get(i).getName() + "</strong><br>재고수량 : "
              + tList.get(i).getItemList().size() + "</center></html>");
      titleList[i].addActionListener(this);
      titleListPanel.add(titleList[i]);
    }

    //취소패널
    JPanel exitPanel = new JPanel();
    exit = new JButton("나가기");
    exit.setPreferredSize(new Dimension(600, 70));
    exit.addActionListener(this);
    exitPanel.add(exit, BorderLayout.SOUTH);

    add(labelPanel, BorderLayout.NORTH);
    add(titlePanel, BorderLayout.CENTER);
    add(exitPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < temp.size(); i++) {
      if (e.getSource() == titleList[i]) {
        timer.stop();
        returnValue = i + 1;
      }
    }
    if (e.getSource() == exit) {
      timer.stop();
      returnValue = 0;
      this.setVisible(false);
    }
  }

  public int getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(int returnValue) {
    this.returnValue = returnValue;
  }

}

