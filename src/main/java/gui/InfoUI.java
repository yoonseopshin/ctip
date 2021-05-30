package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoUI extends JFrame implements ActionListener {

  private int s = 9;
  private Timer timer;
  private JButton confirm;
  private JLabel label;
  private int returnValue = -1;

  public InfoUI(String str, String str2) {
    timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        label.setText(s + "초 후 " + str2 + " 화면으로 돌아갑니다.");
        if (s == 0) {
          timer.stop();
          returnValue = 0;
        }
        s--;
      }
    });
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 300));
    label = new JLabel("10초 후 " + str2 + " 화면으로 돌아갑니다.");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //안내패널
    JPanel informPanel = new JPanel();
    JLabel infoLabel = new JLabel(str);
    infoLabel.setFont(infoLabel.getFont().deriveFont(20.0f));
    informPanel.add(infoLabel);

    //버튼패널
    JPanel buttonPanel = new JPanel();
    confirm = new JButton("확인");
    confirm.setPreferredSize(new Dimension(300, 70));
    confirm.addActionListener(this);
    buttonPanel.add(confirm);

    add(labelPanel, BorderLayout.NORTH);
    add(informPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == confirm) {
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
