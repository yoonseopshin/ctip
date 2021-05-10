package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReqDVMMenu extends JFrame implements ActionListener{
	private JButton find;
	private JButton cancel;
	
	private static int return_value= -1;
	
	public ReqDVMMenu(Title t) {
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		
		//라벨패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,300));
		JLabel label = new JLabel("선택한 음료: "+t.name);
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//안내패널
		JPanel informpanel = new JPanel();
		//informpanel.setPreferredSize(new Dimension(600,300));
		JLabel infolabel = new JLabel("<html><center>해당 음료의 재고가 없습니다."
				+ "<br>구매 가능한 다른 자판기를 안내받으시겠습니까?</center></html>");
		infolabel.setFont(label.getFont().deriveFont(20.0f));
		informpanel.add(infolabel);
		//버튼패널
		JPanel buttonpanel = new JPanel(new GridLayout(2,1));
		buttonpanel.setPreferredSize(new Dimension(600,150));
		find=new JButton("구매 가능한 자판기 안내");
		cancel=new JButton("취소");
		find.addActionListener(this);
		cancel.addActionListener(this);
		buttonpanel.add(find);
		buttonpanel.add(cancel);
		
		add(labelpanel,BorderLayout.NORTH);
		add(informpanel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Title t=new Title("가나다",1,1);
		new ReqDVMMenu(t);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==find) {
			return_value=1;
		}
		if(e.getSource()==cancel) {
			return_value=0;
		}
		
	}

}
