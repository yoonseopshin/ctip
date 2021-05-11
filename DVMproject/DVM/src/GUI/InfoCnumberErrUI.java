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
import javax.swing.Timer;

import Logic.Title;

public class InfoCnumberErrUI extends JFrame implements ActionListener{
	public Timer timer = new Timer(1000, this);
	private JButton confirm;
	private JLabel label;
	private int s=5;
	public int return_value= -1;
	
	public InfoCnumberErrUI() {
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
		JLabel infolabel = new JLabel("<html><center>해당 인증번호에 대한 선결제 정보를 확인할 수 없습니다."
				+ "<br>다시 입력하세요</center></html>");
		infolabel.setFont(infolabel.getFont().deriveFont(20.0f));
		informpanel.add(infolabel);
		//버튼패널
		JPanel buttonpanel = new JPanel();
		confirm=new JButton("확인");
		confirm.setPreferredSize(new Dimension(300,70));
		confirm.addActionListener(this);
		buttonpanel.add(confirm);
		
		add(labelpanel,BorderLayout.NORTH);
		add(informpanel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new InfoCnumberErrUI();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirm) {
			return_value=0;
		}
		label.setText(s+"초 후 이전화면으로 돌아갑니다.");
		if(s==0) {
			return_value=0;
			timer.stop();
		}
		s--;
	}

}
