package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DVMMenu extends JFrame implements ActionListener{
	private JButton[] DVM;
	private JButton cancel;
	private ArrayList<DVM> arr;
	private static int return_value= -1;
	
	public DVMMenu(ArrayList<DVM> DVM_list) {
		arr=DVM_list;
		
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		//라벨패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("선결제할 자판기를 고르세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//자판기패널
		JPanel DVMpanel = new JPanel(new GridLayout(10,1));
		DVMpanel.setPreferredSize(new Dimension(600,700));
		
		DVM=new JButton[10];
		for(int i=0;i<DVM_list.size();i++) {
			DVM[i]=new JButton("DVM ID : "+DVM_list.get(i).ID+"      위치 : "+
		DVM_list.get(i).X+", "+DVM_list.get(i).Y);
			DVM[i].addActionListener(this);
			DVMpanel.add(DVM[i]);
		}
		
		//취소패널
		JPanel cancelpanel = new JPanel();
		cancel = new JButton("취소");
		cancel.setPreferredSize(new Dimension(600,70));
		cancel.addActionListener(this);
		cancelpanel.add(cancel,BorderLayout.SOUTH);
		
		add(labelpanel,BorderLayout.NORTH);
		add(DVMpanel,BorderLayout.CENTER);
		add(cancelpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		DVM a=new DVM(1,1.0,1.0);
		DVM b=new DVM(2,2.0,2.0);
		DVM c=new DVM(3,3.0,3.0);
		DVM d=new DVM(4,4.0,4.0);
		ArrayList<DVM> darr=new ArrayList<DVM>();
		darr.add(a);darr.add(b);darr.add(c);darr.add(d);
		
		new DVMMenu(darr);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<arr.size();i++) {
			if(e.getSource()==DVM[i]) {
				return_value=arr.get(i).ID;
				System.out.println(return_value);
				this.setVisible(false);
			}
		}
		if(e.getSource()==cancel) {
			return_value=0;
			this.setVisible(false);
		}
			
	}

}
class DVM{
	int ID;
	double X;
	double Y;
	DVM(int id, double x, double y){
		this.ID=id;
		this.X=x;
		this.Y=y;
		
	}
}
