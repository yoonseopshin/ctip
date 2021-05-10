package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManTitleMenu extends JFrame implements ActionListener{
	private JButton[] Title_list;
	private JButton exit;
	private static Controller controller;
	ArrayList<tempTitle2> temp= controller.return_stock();
	
	private static int return_value= -1;
	
	public ManTitleMenu() {
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");

		//라벨 패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("재고를 변경할 음료를 선택하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//타이틀패널
		JPanel titlepanel = new JPanel(new GridLayout(10,1));
		titlepanel.setPreferredSize(new Dimension(600,700));
		
		
		Title_list=new JButton[10];
		for(int i=0;i<temp.size();i++) {
			Title_list[i]=new JButton(" "+temp.get(i).name + "     재고수량: " + temp.get(i).stock);
			Title_list[i].addActionListener(this);
			titlepanel.add(Title_list[i]);
		}
		
		//취소패널
		JPanel exitpanel = new JPanel();
		exit = new JButton("나가기");
		exit.setPreferredSize(new Dimension(600,70));
		exit.addActionListener(this);
		exitpanel.add(exit,BorderLayout.SOUTH);

		add(labelpanel,BorderLayout.NORTH);
		add(titlepanel,BorderLayout.CENTER);
		add(exitpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
//테스트용
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<tempTitle2> Title_list = new ArrayList<tempTitle2>();
		tempTitle2 a= new tempTitle2("A",9,1);
		tempTitle2 b= new tempTitle2("B",3,2);
		tempTitle2 c= new tempTitle2("C",0,3);
		tempTitle2 d= new tempTitle2("D",2,4);
		tempTitle2 e= new tempTitle2("E",4,5);
		
		Title_list.add(a); Title_list.add(b); Title_list.add(c); Title_list.add(d); Title_list.add(e);
		
		controller=new Controller(Title_list);
		
		new ManTitleMenu();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<temp.size();i++) {
			if(e.getSource()==Title_list[i]) {
				return_value=temp.get(i).ID;
				System.out.println(return_value);
				this.setVisible(false);
			}
		}
		if(e.getSource()==exit) {
			return_value=0;
			this.setVisible(false);
		}

		
	}

}

class tempTitle2{
	String name;
	int stock;
	int ID;
	
	tempTitle2(String name,int stock,int ID){
		this.name=name;
		this.stock=stock;
		this.ID = ID;
	}



}
class Controller{
	ArrayList<tempTitle2> Title_list;
	Controller(ArrayList<tempTitle2> t){
		this.Title_list=t;
	}
	ArrayList<tempTitle2> return_stock(){
		return this.Title_list;
	}
}
