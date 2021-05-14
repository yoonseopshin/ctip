package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Logic.*;

public class PaymentMenu extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      return_value = -2;
      timer.stop();
    }
  });

  private JButton cardpay;
  private JButton smartpay;
  private JButton cancel;

  public int return_value = -1;

  public PaymentMenu(Title title) {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM");

    //라벨 패널
    JPanel labelpanel = new JPanel();
    labelpanel.setPreferredSize(new Dimension(600, 150));
    JLabel label = new JLabel("결제 수단을 선택하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelpanel.add(label);

    //결제수단 패널
    JPanel paymentpanel = new JPanel();
    paymentpanel.setPreferredSize(new Dimension(600, 350));
    JLabel bev = new JLabel("<html><center>선택한 음료 : <strong>" + title.Name() +
        "</strong><br>가격 : <strong>" + title.Price() + "원</strong></center></html>");
    bev.setPreferredSize(new Dimension(600, 50));
    bev.setHorizontalAlignment(JLabel.CENTER);
    bev.setFont(label.getFont().deriveFont(15.0f));
    cardpay = new JButton("카드 결제");
    smartpay = new JButton("간편 결제");
    cardpay.setPreferredSize(new Dimension(270, 200));
    smartpay.setPreferredSize(new Dimension(270, 200));
    cardpay.addActionListener(this);
    smartpay.addActionListener(this);
    paymentpanel.add(bev, BorderLayout.NORTH);
    paymentpanel.add(cardpay, BorderLayout.WEST);
    paymentpanel.add(smartpay, BorderLayout.EAST);

    //취소
    JPanel cancelpanel = new JPanel();
    cancelpanel.setPreferredSize(new Dimension(600, 200));
    cancel = new JButton("취소");
    cancel.setPreferredSize(new Dimension(300, 100));
    cancel.addActionListener(this);
    cancelpanel.add(cancel, BorderLayout.SOUTH);

    add(labelpanel, BorderLayout.NORTH);
    add(paymentpanel, BorderLayout.CENTER);
    add(cancelpanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

  //테스트용
	/*
	public static void main(String[] args) {
		Title a=new Title("코카콜라",1,(float)1);
		new PaymentMenu(a);
		

	}
	*/
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cardpay) {
      timer.stop();
      return_value = 1;
    }
    if (e.getSource() == smartpay) {
      timer.stop();
      return_value = 2;
    }
    if (e.getSource() == cancel) {
      timer.stop();
      return_value = 0;
    }
  }
}

