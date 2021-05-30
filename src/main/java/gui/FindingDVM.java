package gui;

import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FindingDVM extends JFrame implements ActionListener {

  private int s = 39;
  private Timer timer = new Timer(1000, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      if (s <= 10) {
        cancel.setText("확인");
        label.setText("남은 시간: 0");
        infoLabel.setText("<html><center>자판기를 찾는 것에 실패하였습니다<br>"
            + s + "초 후 메인화면으로 돌아갑니다.</center></html>");
        if (s == 0) {
          timer.stop();
          returnValue = 0;
        }
      } else {
        label.setText("남은 시간: " + (s - 10));
      }
      s--;
    }
  });

  private JLabel label;
  private JLabel infoLabel;
  private JButton cancel;
  private int returnValue = -1;

  public FindingDVM(String name) {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 300));
    label = new JLabel("남은시간: " + 30);
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //안내패널
    JPanel informPanel = new JPanel();
    infoLabel = new JLabel("<html><center><strong>" + name + "</strong>"
        + "<br>해당 음료가 있는 자판기를 검색중입니다.</center></html>");
    infoLabel.setFont(infoLabel.getFont().deriveFont(20.0f));
    informPanel.add(infoLabel);

    //버튼패널
    JPanel buttonPanel = new JPanel();
    cancel = new JButton("취소");
    cancel.setPreferredSize(new Dimension(300, 70));
    cancel.addActionListener(this);
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
