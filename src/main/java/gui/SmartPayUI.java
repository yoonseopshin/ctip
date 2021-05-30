package gui;

import javax.swing.ImageIcon;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class SmartPayUI extends JFrame implements ActionListener {

  private int s = 39;
  private Timer timer = new Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (s <= 10) {
        cancel.setText("확인");
        label.setText("남은 시간: 0");
        infoLabel.setText("<html><center>입력시간이 초과되어 결제가 취소되었습니다.<br>"
            + s + "초 후 메인화면으로 돌아갑니다.</center></html>");
        if (s == 10) {
          informPanel.remove(imageLabel);
          informPanel.updateUI();
          labelPanel.setPreferredSize(new Dimension(600, 300));
          infoLabel.setFont(infoLabel.getFont().deriveFont(20.0f));
          informPanel.add(infoLabel);
        }
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

  private JButton cancel;
  private JLabel label;
  private JLabel imageLabel;
  private JLabel infoLabel = new JLabel();
  private ImageIcon img;
  private JPanel labelPanel;
  private JPanel informPanel;
  private JPanel buttonPanel;
  private int returnValue = -1;

  public SmartPayUI() {
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Do nothing because no need to
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // Do nothing because no need to
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (s > 10) {
          if (e.getKeyChar() == 's') {
            timer.stop();
            returnValue = 1;
          } else if (e.getKeyChar() == 'f') {
            timer.stop();
            returnValue = -3;
          }
        }
      }
    });

    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 150));
    label = new JLabel("남은 시간: " + 30);
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //안내패널
    informPanel = new JPanel();
    informPanel.setPreferredSize(new Dimension(600, 500));
    URL url = ClassLoader.getSystemResource("QR.jpg");
    if (url != null) {
      img = new ImageIcon(url);
      imageLabel = new JLabel(img);
    } else {
      labelPanel.setPreferredSize(new Dimension(600, 300));
      imageLabel = new JLabel("QR 이미지를 가져올 수 없습니다.");
      imageLabel.setFont(infoLabel.getFont().deriveFont(20.0f));
    }
    informPanel.add(imageLabel);


    //버튼패널
    buttonPanel = new JPanel();
    cancel = new JButton("취소");
    cancel.setPreferredSize(new Dimension(300, 70));
    cancel.addActionListener(this);
    cancel.setFocusable(false);
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
