package gui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import logic.Item;
import logic.Title;
import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManItemMenu extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      returnValue = -2;
    }
  });

  private JCheckBox[] itemList;
  private JButton exit;
  private JButton add;
  private JButton delete;
  private transient ArrayList<Item> temp;
  private int returnValue = -1;
  private ArrayList<Integer> returnItemList;

  public ManItemMenu(Title t) {
    timer.start();
    temp = t.getItemList();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨 패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 30));
    JLabel label = new JLabel("음료: " + t.getName() + "     변경할 재고를 선택하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //아이템패널
    JPanel itemListPanel = new JPanel();
    JScrollPane itemPanel = new JScrollPane(itemListPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    itemPanel.getVerticalScrollBar().setUnitIncrement(8);
    itemListPanel.setPreferredSize(new Dimension(600, 56 * temp.size()));
    itemList = new JCheckBox[30];
    for (int i = 0; i < temp.size(); i++) {
      String expdate = Integer.toString(temp.get(i).getExpirationDate());
      itemList[i] = new JCheckBox(
          "ID: " + (i + 1) + "     유통기한: " + expdate.substring(0, 4)
              + "-" + expdate.substring(4, 6) + "-" + expdate.substring(6, 8));
      itemList[i].setPreferredSize(new Dimension(600, 50));
      itemList[i].addActionListener(this);
      itemListPanel.add(itemList[i]);
    }

    //버튼패널
    JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
    buttonPanel.setPreferredSize(new Dimension(600, 70));
    exit = new JButton("나가기");
    add = new JButton("재고 추가");
    delete = new JButton("재고 삭제");
    exit.addActionListener(this);
    add.addActionListener(this);
    delete.addActionListener(this);
    buttonPanel.add(add);
    buttonPanel.add(delete);
    buttonPanel.add(exit);

    add(labelPanel, BorderLayout.NORTH);
    add(itemPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    returnItemList = new ArrayList<Integer>();
    for (int i = 0; i < temp.size(); i++) {
      if (e.getSource() == itemList[i]) {
        timer.restart();
      }
    }
    if (e.getSource() == add) {
      timer.stop();
      returnValue = 1;
    }
    if (e.getSource() == delete) {
      timer.stop();
      for (int i = 0; i < temp.size(); i++) {
        if (itemList[i].isSelected()) {
          returnItemList.add(i);
        }
      }
      returnValue = 2;
    }
    if (e.getSource() == exit) {
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

  public ArrayList<Integer> getReturnItemList() {
    return returnItemList;
  }

  public void setReturnItemList(ArrayList<Integer> returnItemList) {
    this.returnItemList = returnItemList;
  }
}

