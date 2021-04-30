package core;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {

  /**
   * Application start.
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("CTIP");
    frame.setSize(600, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    JLabel label = new JLabel("Hello world!", SwingConstants.CENTER);
    frame.getContentPane().add(label);

    // Dirty code test.
    Object arr[] = {"3", 16L};
    System.out.println(arr[0].toString() + arr[1]);;

    // Dirty code test 2: integer overflow.
    // 0x7fffffff = Integer.MAX_VALUE
    int a = 0x7fffffff;
    int b = 1000;
    System.out.println(a + b > Integer.MAX_VALUE);
  }

}
