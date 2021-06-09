package gui;

import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sleep extends JFrame implements MouseListener {

  private int returnValue = -1;

  public Sleep() {
    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());
    this.addMouseListener(this);
    this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));

    //라벨패널
    JPanel labelPanel = new JPanel();
    JLabel label = new JLabel("사용하려면 화면을 터치하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    add(labelPanel, BorderLayout.CENTER);
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // Do nothing

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // Do nothing

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    returnValue = 1;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // Do nothing

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // Do nothing

  }

  public int getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(int returnValue) {
    this.returnValue = returnValue;
  }

}
