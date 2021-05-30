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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class InputLine extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      returnValue = -2;
    }
  });

  private JTextField txt;
  private JButton[] num;
  private JButton delete;
  private JButton enter;
  private JButton cancel;
  private int returnValue = -1;

  public InputLine() {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨 패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 30));
    JLabel label = new JLabel("선결제 인증번호를 입력하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //입력패널
    JPanel inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(600, 100));
    txt = new JTextField("", SwingConstants.CENTER);
    txt.setHorizontalAlignment(SwingConstants.CENTER);
    txt.setPreferredSize(new Dimension(400, 100));
    txt.setFont(txt.getFont().deriveFont(40.0f));
    delete = new JButton("지우기");
    delete.setPreferredSize(new Dimension(130, 100));
    delete.setFont(delete.getFont().deriveFont(20.0f));
    delete.addActionListener(this);
    inputPanel.add(txt, BorderLayout.WEST);
    inputPanel.add(delete, BorderLayout.EAST);

    //숫자패널
    JPanel numPanel = new JPanel(new GridLayout(4, 3));
    numPanel.setPreferredSize(new Dimension(600, 630));
    num = new JButton[10];
    enter = new JButton("확인");
    cancel = new JButton("취소");
    enter.setFont(enter.getFont().deriveFont(20.0f));
    cancel.setFont(cancel.getFont().deriveFont(20.0f));
    enter.addActionListener(this);
    cancel.addActionListener(this);
    for (int i = 0; i < num.length; i++) {
      num[i] = new JButton("" + i);
      num[i].setFont(num[i].getFont().deriveFont(20.0f));
      num[i].addActionListener(this);
    }
    for (int i = 1; i < num.length; i++) {
      numPanel.add(num[i]);
    }
    numPanel.add(cancel);
    numPanel.add(num[0]);
    numPanel.add(enter);

    add(labelPanel, BorderLayout.NORTH);
    add(inputPanel, BorderLayout.CENTER);
    add(numPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < num.length; i++) {
      if (e.getSource() == num[i]) {
        timer.restart();
        if (txt.getText().length() < 6) {
          txt.setText(txt.getText() + e.getActionCommand());
        }
      }
    }
    if (e.getSource() == delete) {
      timer.restart();
      String before = txt.getText();
      int k = before.length();
      if (k > 0) {
        String after = before.substring(0, k - 1);
        txt.setText(after);
      }
    }
    if (e.getSource() == enter) {
      timer.stop();
      if (txt.getText().length() < 6) {
        returnValue = 666;
      } else {
        int input = Integer.parseInt(txt.getText());
        if (input == 0) {
          returnValue = 666;
        } else {
          returnValue = input;
        }
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
