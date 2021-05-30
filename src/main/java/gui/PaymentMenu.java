package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import logic.Title;
import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentMenu extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      returnValue = -2;
    }
  });

  private JButton cardPay;
  private JButton smartPay;
  private JButton cancel;
  private int returnValue = -1;

  public PaymentMenu(Title title) {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨 패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 150));
    JLabel label = new JLabel("결제 수단을 선택하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //결제수단 패널
    JPanel paymentPanel = new JPanel();
    paymentPanel.setPreferredSize(new Dimension(600, 350));
    JLabel bev = new JLabel("<html><center>선택한 음료 : <strong>"
        + title.getName() + "</strong><br>가격 : <strong>" + title.getPrice()
        + "원</strong></center></html>");
    bev.setPreferredSize(new Dimension(600, 50));
    bev.setHorizontalAlignment(SwingConstants.CENTER);
    bev.setFont(label.getFont().deriveFont(15.0f));
    cardPay = new JButton("카드 결제");
    smartPay = new JButton("간편 결제");
    cardPay.setPreferredSize(new Dimension(270, 200));
    smartPay.setPreferredSize(new Dimension(270, 200));
    cardPay.addActionListener(this);
    smartPay.addActionListener(this);
    paymentPanel.add(bev, BorderLayout.NORTH);
    paymentPanel.add(cardPay, BorderLayout.WEST);
    paymentPanel.add(smartPay, BorderLayout.EAST);

    //취소
    JPanel cancelPanel = new JPanel();
    cancelPanel.setPreferredSize(new Dimension(600, 200));
    cancel = new JButton("취소");
    cancel.setPreferredSize(new Dimension(300, 100));
    cancel.addActionListener(this);
    cancelPanel.add(cancel, BorderLayout.SOUTH);

    add(labelPanel, BorderLayout.NORTH);
    add(paymentPanel, BorderLayout.CENTER);
    add(cancelPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cardPay) {
      timer.stop();
      returnValue = 1;
    }
    if (e.getSource() == smartPay) {
      timer.stop();
      returnValue = 2;
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

