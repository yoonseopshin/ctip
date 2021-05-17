package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import Logic.*;
import static GUI.Sleep.*;

public class ManItemMenu extends JFrame implements ActionListener {

    private Timer timer = new Timer(180000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            return_value = -2;
            timer.stop();
        }
    });

    private JCheckBox[] Item_list;
    private JButton exit;
    private JButton add;
    private JButton delete;
    private ArrayList<Item> temp;

    public int return_value = -1;
    public ArrayList<Integer> return_itemlist;


    public ManItemMenu(Title t) {
        timer.start();
        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM "+ CurrentID);
        temp = t.getItem_List();

        //라벨 패널
        JPanel labelpanel = new JPanel();
        labelpanel.setPreferredSize(new Dimension(600, 30));
        JLabel label = new JLabel("음료: " + t.getName() + "     변경할 재고를 선택하세요");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);
        //아이템패널
        JPanel itemlistpanel = new JPanel();
        JScrollPane itempanel = new JScrollPane(itemlistpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemlistpanel.setPreferredSize(new Dimension(600, 50 * temp.size()));
        Item_list = new JCheckBox[30];
        for (int i = 0; i < temp.size(); i++) {
            String expdate = Integer.toString(temp.get(i).getExpiration_Date());
            Item_list[i] = new JCheckBox(
                    "ID: " + (i + 1) + "     유통기한: " + expdate.substring(0, 4) + "-" +
                            expdate.substring(4, 6) + "-" + expdate.substring(6, 8));
            Item_list[i].setPreferredSize(new Dimension(600, 50));
            Item_list[i].addActionListener(this);
            itemlistpanel.add(Item_list[i]);
        }

        //버튼패널
        JPanel buttonpanel = new JPanel(new GridLayout(1, 3));
        buttonpanel.setPreferredSize(new Dimension(600, 70));
        exit = new JButton("나가기");
        add = new JButton("재고 추가");
        delete = new JButton("재고 삭제");
        exit.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        buttonpanel.add(add);
        buttonpanel.add(delete);
        buttonpanel.add(exit);

        add(labelpanel, BorderLayout.NORTH);
        add(itempanel, BorderLayout.CENTER);
        add(buttonpanel, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
	/*test

	public static void main(String[] args) {
		Controller c=new Controller();
    	c.Title_List.get(0).AddItem(new Item(20200101));
    	c.Title_List.get(0).AddItem(new Item(20200102));
    	c.Title_List.get(0).AddItem(new Item(20200103));
    	c.Title_List.get(0).AddItem(new Item(20200104));
    	c.Title_List.get(0).AddItem(new Item(20200105));
    	c.Title_List.get(0).AddItem(new Item(20200106));
    	c.Title_List.get(0).AddItem(new Item(20200107));
    	c.Title_List.get(0).AddItem(new Item(20200108));
    	c.Title_List.get(0).AddItem(new Item(20200109));
    	new ManItemMenu(c.Title_List.get(0));

	}
	 */


    @Override
    public void actionPerformed(ActionEvent e) {
        return_itemlist = new ArrayList<Integer>();
        for (int i = 0; i < temp.size(); i++) {
            if (e.getSource() == Item_list[i]) {
                timer.restart();
            }
        }
        if (e.getSource() == add) {
            timer.stop();
            return_value = 1;
        }
        if (e.getSource() == delete) {
            timer.stop();
            for (int i = 0; i < temp.size(); i++) {
                if (Item_list[i].isSelected()) {
                    return_itemlist.add(i);
                }
            }
            return_value = 2;
        }
        if (e.getSource() == exit) {
            timer.stop();
            return_value = 0;
        }

    }
}

