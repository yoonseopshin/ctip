package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener{
	private JButton[] menu;
	private JButton CnumberInput;
	private static int return_value=-1;
	
	
	public MainMenu() {
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");

		//라벨 패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,30));
		JLabel label = new JLabel("음료를 선택하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		//메뉴패널
		JPanel menupanel = new JPanel(new GridLayout(5,4));
		menupanel.setPreferredSize(new Dimension(600,700));

		menu = new JButton[20];
		menu[0] = new JButton("코카콜라");
		menu[1] = new JButton("나랑드사이다");
		menu[2] = new JButton("솔의눈");
		menu[3] = new JButton("게토레이");
		menu[4] = new JButton("스프라이트");
		menu[5] = new JButton("포카리 스웨트");
		menu[6] = new JButton("닥터페퍼");
		menu[7] = new JButton("맥콜");
		menu[8] = new JButton("제티");
		menu[9] = new JButton("제주삼다수");
		menu[10] = new JButton("데자와");
		menu[11] = new JButton("아침햇살");
		menu[12] = new JButton("밀키스");
		menu[13] = new JButton("레쓰비");
		menu[14] = new JButton("조지아");
		menu[15] = new JButton("칠성사이다");
		menu[16] = new JButton("티오피");
		menu[17] = new JButton("몬스터");
		menu[18] = new JButton("핫식스");
		menu[19] = new JButton("레드불");
		for(int i=0;i<menu.length;i++) {
			menupanel.add(menu[i]);
			menu[i].addActionListener(this);
		}
		//추가패널
		JPanel addpanel=new JPanel(new GridLayout(1,1));
		addpanel.setPreferredSize(new Dimension(600,70));
		
		CnumberInput = new JButton("선결제 인증번호 입력");
		CnumberInput.addActionListener(this);
		addpanel.add(CnumberInput);
		
		add(labelpanel,BorderLayout.NORTH);
		add(menupanel,BorderLayout.CENTER);
		add(addpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public int return_value() {
		return return_value;
	}
	//테스트용
	public static void main(String[] args) {
		new MainMenu();
		

	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<menu.length;i++) {
			if(e.getSource()==menu[i]) {
				return_value = i+1;
				//this.setVisible(false);
				
				
			}
		}
		if(e.getSource()==CnumberInput) {
			return_value = 0;
			System.out.print(return_value);
			//this.setVisible(false);
		}
	}

}
