package gui;

import logic.DVM;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AddItemMenu extends JFrame implements ActionListener {

  private Timer timer = new Timer(180000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      timer.stop();
      return_value = -2;
    }
  });

  private JComboBox<String> yearSelect;
  private JComboBox<String> monthSelect;
  private JComboBox<String> daySelect;
  private JButton cancel;
  private JButton add;
  private String[] dayList1;
  private String[] dayList2;
  private String[] dayList3;
  private String[] dayList4;
  private int return_value = -1;
  private int return_date;

  public AddItemMenu() {
    timer.start();

    this.setPreferredSize(new Dimension(600, 800));
    this.setTitle("DVM " + DVM.getCurrentID());

    //라벨패널
    JPanel labelPanel = new JPanel();
    labelPanel.setPreferredSize(new Dimension(600, 300));
    JLabel label = new JLabel("추가할 재고의 유통기한을 입력하세요");
    label.setFont(label.getFont().deriveFont(15.0f));
    labelPanel.add(label);

    //유통기한 입력패널
    JPanel expDatePanel = new JPanel();
    JLabel y = new JLabel("년   ");
    JLabel m = new JLabel("월   ");
    JLabel d = new JLabel("일");
    y.setFont(y.getFont().deriveFont(20.0f));
    m.setFont(m.getFont().deriveFont(20.0f));
    d.setFont(d.getFont().deriveFont(20.0f));

    //선택창
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int date = cal.get(Calendar.DATE);
    String[] yearList = new String[50];
    String[] monthList = new String[12];
    dayList1 = new String[31];
    dayList2 = new String[30];
    dayList3 = new String[29];
    dayList4 = new String[28];
    for (int i = 0; i < yearList.length; i++) {
      yearList[i] = Integer.toString(year + i);
    }
    for (int i = 0; i < monthList.length; i++) {
      if (i + 1 < 10) {
        monthList[i] = "0".concat(Integer.toString(i + 1));
      } else {
        monthList[i] = Integer.toString(i + 1);
      }
    }
    for (int i = 0; i < dayList1.length; i++) {
      if (i + 1 < 10) {
        dayList1[i] = "0".concat(Integer.toString(i + 1));
      } else {
        dayList1[i] = Integer.toString(i + 1);
      }
    }
    for (int i = 0; i < dayList2.length; i++) {
      if (i + 1 < 10) {
        dayList2[i] = "0".concat(Integer.toString(i + 1));
      } else {
        dayList2[i] = Integer.toString(i + 1);
      }
    }
    for (int i = 0; i < dayList3.length; i++) {
      if (i + 1 < 10) {
        dayList3[i] = "0".concat(Integer.toString(i + 1));
      } else {
        dayList3[i] = Integer.toString(i + 1);
      }
    }
    for (int i = 0; i < dayList4.length; i++) {
      if (i + 1 < 10) {
        dayList4[i] = "0".concat(Integer.toString(i + 1));
      } else {
        dayList4[i] = Integer.toString(i + 1);
      }
    }
    yearSelect = new JComboBox<String>(yearList);
    monthSelect = new JComboBox<String>(monthList);
    daySelect = new JComboBox<String>(dayList1);
    yearSelect.setFont(yearSelect.getFont().deriveFont(20.0f));
    monthSelect.setFont(monthSelect.getFont().deriveFont(20.0f));
    daySelect.setFont(daySelect.getFont().deriveFont(20.0f));
    yearSelect.addActionListener(this);
    monthSelect.addActionListener(this);
    daySelect.addActionListener(this);
    monthSelect.setSelectedIndex(month);
    daySelect.setSelectedIndex(date - 1);
    expDatePanel.add(yearSelect);
    expDatePanel.add(y);
    expDatePanel.add(monthSelect);
    expDatePanel.add(m);
    expDatePanel.add(daySelect);
    expDatePanel.add(d);

    //버튼패널
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    buttonPanel.setPreferredSize(new Dimension(600, 70));
    cancel = new JButton("취소");
    add = new JButton("추가");
    cancel.addActionListener(this);
    add.addActionListener(this);
    buttonPanel.add(add);
    buttonPanel.add(cancel);

    add(labelPanel, BorderLayout.NORTH);
    add(expDatePanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);


  }

  @Override
  public void actionPerformed(ActionEvent e) {
    timer.restart();
    if (e.getSource() == yearSelect) {
      int y = Integer.parseInt((String) (yearSelect.getSelectedItem()));
      int m = Integer.parseInt((String) (monthSelect.getSelectedItem()));
      if (m == 2) {
        DefaultComboBoxModel<String> model;
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
          model = new DefaultComboBoxModel<>(dayList3);
        } else {
          model = new DefaultComboBoxModel<>(dayList4);
        }
        daySelect.setModel(model);
      }
    }
    if (e.getSource() == monthSelect) {
      timer.restart();
      int y = Integer.parseInt((String) (yearSelect.getSelectedItem()));
      int m = Integer.parseInt((String) (monthSelect.getSelectedItem()));
      DefaultComboBoxModel<String> model;
      if (m == 2) {
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) {
          model = new DefaultComboBoxModel<>(dayList3);
        } else {
          model = new DefaultComboBoxModel<>(dayList4);
        }
      } else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
        model = new DefaultComboBoxModel<>(dayList1);
      } else {
        model = new DefaultComboBoxModel<>(dayList2);
      }
      daySelect.setModel(model);
    }
    if (e.getSource() == daySelect) {
      timer.restart();
    }
    if (e.getSource() == add) {
      String y = (String) yearSelect.getSelectedItem();
      String m = (String) monthSelect.getSelectedItem();
      String d = (String) daySelect.getSelectedItem();
      return_date = Integer.parseInt(y.concat(m).concat(d));
      timer.stop();
      return_value = 1;
    }
    if (e.getSource() == cancel) {
      timer.stop();
      return_value = 0;
    }
  }

  public int getReturnValue() {
    return return_value;
  }

  public void setReturnValue(int returnValue) {
    this.return_value = returnValue;
  }

  public int getReturnDate() {
    return return_date;
  }

  public void setReturnDate(int returnDate) {
    this.return_date = returnDate;
  }

}

