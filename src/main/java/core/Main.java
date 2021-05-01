package core;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Main {

  /**
   * Application start.
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("CTIP");
    frame.setSize(600, 300);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
    JLabel label = new JLabel("Hello world!", SwingConstants.CENTER);
    frame.getContentPane().add(label);

    // Dirty code.
    Integer a = 3;
    double b = 3f;
    if (a == b) {
      foo();
    } else {
      bar();
    }

  }

  static void foo() {
  }

  static void bar() {
  }

}
