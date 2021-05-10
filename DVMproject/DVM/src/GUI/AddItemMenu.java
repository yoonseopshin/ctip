package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//jnkj
public class AddItemMenu extends JFrame implements ActionListener{
	
	private JComboBox Yearselect; 
	private JComboBox Monthselect;
	private JComboBox Dayselect;

	private JButton cancel;
	private JButton add;
	
	private static int return_value= -1;
	private static String return_date;
	
	public AddItemMenu(){
		this.setPreferredSize(new Dimension(600,800));
		this.setTitle("DVM");
		
		//라벨패널
		JPanel labelpanel = new JPanel();
		labelpanel.setPreferredSize(new Dimension(600,300));
		JLabel label = new JLabel("추가할 재고의 유통기한을 입력하세요");
		label.setFont(label.getFont().deriveFont(15.0f));
		labelpanel.add(label);
		
		//유통기한 입력패널
		JPanel expdatepanel=new JPanel();
		JLabel y = new JLabel("년   ");
		JLabel m = new JLabel("월   ");
		JLabel d = new JLabel("일");
		y.setFont(y.getFont().deriveFont(20.0f));
		m.setFont(m.getFont().deriveFont(20.0f));
		d.setFont(d.getFont().deriveFont(20.0f));
		
		//선택창
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Integer[] year_list=new Integer[50];
		Integer[] month_list=new Integer[12];
		Integer[] day_list=new Integer[31];
		for(int i=0;i<year_list.length;i++) {
			year_list[i]=year+i;
		}
		for(int i=0;i<month_list.length;i++) {
			month_list[i]=i+1;
		}
		for(int i=0;i<day_list.length;i++) {
			day_list[i]=i+1;
		}
		Yearselect= new JComboBox(year_list);
		Monthselect= new JComboBox(month_list);
		Dayselect= new JComboBox(day_list);
		Yearselect.setFont(Yearselect.getFont().deriveFont(20.0f));
		Monthselect.setFont(Monthselect.getFont().deriveFont(20.0f));
		Dayselect.setFont(Dayselect.getFont().deriveFont(20.0f));
		expdatepanel.add(Yearselect);
		expdatepanel.add(y);
		expdatepanel.add(Monthselect);
		expdatepanel.add(m);
		expdatepanel.add(Dayselect);
		expdatepanel.add(d);
		//버튼패널
		JPanel buttonpanel = new JPanel(new GridLayout(1,2));
		buttonpanel.setPreferredSize(new Dimension(600,70));
		cancel = new JButton("취소");
		add = new JButton("추가");
		cancel.addActionListener(this);
		add.addActionListener(this);
		buttonpanel.add(add);
		buttonpanel.add(cancel);
		
		
		
		add(labelpanel,BorderLayout.NORTH);
		add(expdatepanel,BorderLayout.CENTER);
		add(buttonpanel,BorderLayout.SOUTH);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddItemMenu();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add) {
			return_value=0;
			String y=Yearselect.getSelectedItem().toString();
			String m=Monthselect.getSelectedItem().toString();
			String d=Dayselect.getSelectedItem().toString();
			return_date=y.concat("-").concat(m).concat("-").concat(d);
			System.out.println(return_date);
			
		}
		if(e.getSource()==cancel) {
			return_value=0;
		}
	}

}
