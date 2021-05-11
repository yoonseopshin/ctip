package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Logic.Title;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoReturnItemUI extends JFrame{

	private JLabel label;
	private int s=4;
	public int return_value= -1;
	
	public InfoReturnItemUI(String name) {
		timer.start();
		
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		
		//라벨패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,300));
		label = new JLabel();
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//안내패널
		JPanel informpanel = new JPanel();
		//informpanel.setPreferredSize(new Dimension(600,300));
		JLabel infolabel = new JLabel("<html><center><strong>" + name + "</strong>"
				+ "<br>음료가 나왔습니다.</center></html>");
		infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
		informpanel.add(infolabel);
		
		add(labelpanel,BorderLayout.NORTH);
		add(informpanel,BorderLayout.CENTER);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InfoReturnItemUI("코카콜라");

	}
	public Timer timer = new Timer(1000, new ActionListener(){   
		public void actionPerformed (ActionEvent e) {
			label.setText(s+"초 후 메인화면으로 돌아갑니다.");
			if(s==0) {
				timer.stop();
				return_value=0;
			}
			s--;
		}
	});
}
