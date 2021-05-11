package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import Logic.*;

public class ManTitleMenu extends JFrame implements ActionListener{
	private Timer timer = new Timer(180000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			return_value = -2;
			timer.stop();
		}
	});

	private JButton[] Title_list;
	private JButton exit;
	private ArrayList<Title> temp;
	
	public int return_value= -1;
	
	public ManTitleMenu(ArrayList<Title> tlist) {
		timer.start();

		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		
		temp=tlist;

		//라벨 패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("재고를 변경할 음료를 선택하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);

		JPanel titlelistpanel = new JPanel(new GridLayout(tlist.size(),1));
		JScrollPane titlepanel = new JScrollPane(titlelistpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		titlelistpanel.setPreferredSize(new Dimension(600,70*tlist.size()));
		Title_list=new JButton[tlist.size()];
		for(int i=0;i<tlist.size();i++) {
			Title_list[i]=new JButton("<html><center><strong>" + tlist.get(i).Name()+ "</strong><br>재고수량 : " +
		tlist.get(i).Item_List().size()+"</center></html>");
			//Title_list[i].setPreferredSize(new Dimension(600,70));
			Title_list[i].addActionListener(this);
			titlelistpanel.add(Title_list[i]);
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
		
		Controller c= new Controller();
		new ManTitleMenu(c.Title_List);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<temp.size();i++) {
			if(e.getSource()==Title_list[i]) {
				timer.stop();
				return_value=i+1;
			}
		}
		if(e.getSource()==exit) {
			timer.stop();
			return_value=0;
			this.setVisible(false);
		}

	}

}

