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

public class ManItemMenu extends JFrame implements ActionListener{
	private JCheckBox[] Item_list;
	private JButton exit;
	private JButton add;
	private JButton delete;
	private static tempTitle3 Title;
	ArrayList<tempItem> temp;
	
	private static int return_value= -1;
	ArrayList<tempItem> return_itemlist;
	
	
	public ManItemMenu(tempTitle3 t) {
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		Title=t;
		temp= t.item_list;

		//라벨 패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("음료: "+t.name+"     변경할 재고를 선택하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//아이템패널
		JPanel itemlistpanel = new JPanel();
		JScrollPane itempanel = new JScrollPane(itemlistpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemlistpanel.setPreferredSize(new Dimension(600,56*temp.size()));
		Item_list=new JCheckBox[30];
		for(int i=0;i<temp.size();i++) {
			Item_list[i]=new JCheckBox("ID: "+temp.get(i).ID + "     유통기한: " + temp.get(i).expdate);
			Item_list[i].setPreferredSize(new Dimension(600,50));
			itemlistpanel.add(Item_list[i]);
		}
		
		//버튼패널
		JPanel buttonpanel = new JPanel(new GridLayout(1,3));
		buttonpanel.setPreferredSize(new Dimension(600,70));
		exit = new JButton("나가기");
		add = new JButton("재고 추가");
		delete = new JButton("재고 삭제");
		exit.addActionListener(this);
		add.addActionListener(this);
		delete.addActionListener(this);
		buttonpanel.add(add);
		buttonpanel.add(delete);
		buttonpanel.add(exit);

		add(labelpanel,BorderLayout.NORTH);
		add(itempanel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	//test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<tempItem> i=new ArrayList<tempItem>();
		for(int j=0;j<30;j++) {
			i.add(new tempItem(j+1,"2020-01-01"));
		}
		
		tempTitle3 t=new tempTitle3("코카콜라",i);
		new ManItemMenu(t);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		return_itemlist=new ArrayList<tempItem>();
		if(e.getSource()==add) {
			return_value=1;
		}
		if(e.getSource()==delete) {
			return_value=2;
			for(int i=0;i<temp.size();i++) {
				if(Item_list[i].isSelected()) {
				return_itemlist.add(temp.get(i));
				System.out.println(return_itemlist.get(return_itemlist.size()-1).ID);
				}
			}
		}
		if(e.getSource()==exit) {
			return_value=0;
			this.setVisible(false);
		}
		
	}
}

class tempTitle3{
	String name;
	ArrayList<tempItem> item_list;
	tempTitle3(String name,ArrayList<tempItem> i){
		this.item_list=i;
		this.name=name;
	}
}
class tempItem{
	int ID;
	String expdate;
	tempItem(int ID,String s){
		this.ID=ID;
		this.expdate=s;
	}
}
