package gui;

import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InfoNoItemUI extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      returnValue = -2;
    }
  });

  private JButton find;
  private JButton cancel;
  private int returnValue = -1;

  public InfoNoItemUI(String name) {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 300));
    JLabel label = new JLabel("선택한 음료: " + name);
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //안내패널
    JPanel informPanel = new JPanel();
    JLabel infoLabel = new JLabel("<html><center>해당 음료의 재고가 없습니다."
        + "<br>구매 가능한 다른 자판기를 안내받으시겠습니까?</center></html>");
    infoLabel.setFont(infoLabel.getFont().deriveFont(20.0f));
    informPanel.add(infoLabel);

    //버튼패널
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
    buttonPanel.setPreferredSize(new Dimension(600, 150));
    find = new JButton("구매 가능한 자판기 안내");
    cancel = new JButton("취소");
    find.addActionListener(this);
    cancel.addActionListener(this);
    buttonPanel.add(find);
    buttonPanel.add(cancel);

    add(labelPanel, BorderLayout.NORTH);
    add(informPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == find) {
      timer.stop();
      returnValue = 1;
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
