package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputLine extends JFrame implements ActionListener{
	public Timer timer = new Timer(60000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			return_value = -2;
		}
	});

	private JTextField txt;
	private JButton[] num;
	private JButton Delete;
	private JButton Enter;
	private JButton Cancel;
	
	public int return_value= -1;
	
	public InputLine() {
		timer.start();
		
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		//라벨 패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("선결제 인증번호를 입력하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		
		//입력패널
		JPanel inputpanel = new JPanel();
		inputpanel.setPreferredSize(new Dimension(600,100));
		txt = new JTextField("", SwingConstants.CENTER);
		txt.setHorizontalAlignment(JTextField.CENTER);
		txt.setPreferredSize(new Dimension(450,100));
		txt.setFont(txt.getFont().deriveFont(40.0f));
		Delete = new JButton("지우기");
		Delete.setPreferredSize(new Dimension(130,100));
		Delete.setFont(Delete.getFont().deriveFont(20.0f));
		Delete.addActionListener(this);
		inputpanel.add(txt, BorderLayout.WEST);
		inputpanel.add(Delete, BorderLayout.EAST);
		
		//숫자패널
		JPanel numpanel = new JPanel(new GridLayout(4,3));
		numpanel.setPreferredSize(new Dimension(600,630));
		num = new JButton[10];
		Enter = new JButton("확인");
		Cancel = new JButton("취소");
		Enter.setFont(Enter.getFont().deriveFont(20.0f));
		Cancel.setFont(Cancel.getFont().deriveFont(20.0f));
		Enter.addActionListener(this);
		Cancel.addActionListener(this);
		
		for(int i=0;i<num.length;i++) {
			num[i]= new JButton(""+i);
			num[i].setFont(num[i].getFont().deriveFont(20.0f));
			num[i].addActionListener(this);
		}
		for(int i=1;i<num.length;i++) {
			numpanel.add(num[i]);
		}
		numpanel.add(Cancel);
		numpanel.add(num[0]);
		numpanel.add(Enter);
		
		add(labelpanel, BorderLayout.NORTH);
		add(inputpanel, BorderLayout.CENTER);
		add(numpanel, BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new InputLine();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<num.length;i++) {
			if(e.getSource()==num[i]) {
				if(txt.getText().length()<8)
					txt.setText(txt.getText()+e.getActionCommand());
				timer.restart();
						
			}
		}
		if(e.getSource()==Delete) {
			String before=txt.getText();
			int k=before.length();
			if(k>0) {
			String after=before.substring(0, k-1);
			txt.setText(after);
			}
			timer.restart();
		}
		if(e.getSource()==Enter) {
			if(txt.getText().length()<8) {
				return_value=666;
			}
			else {
				int input=Integer.parseInt(txt.getText());
				if(input==0)
					return_value=666;
				else
					return_value=input;
			}
			timer.stop();
		}
		if(e.getSource()==Cancel) {
			return_value = 0;
			timer.stop();
		}
	}
}
