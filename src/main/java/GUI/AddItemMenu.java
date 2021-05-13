package main.java.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;

public class AddItemMenu extends JFrame implements ActionListener{
	public Timer timer = new Timer(180000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			return_value = -2;
			timer.stop();
		}
	});
	
	private JComboBox<String> Yearselect; 
	private JComboBox<String> Monthselect;
	private JComboBox<String> Dayselect;

	private JButton cancel;
	private JButton add;
	
	public int return_value= -1;
	public int return_date;
	
	public AddItemMenu(){
		timer.start();

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
		String[] year_list=new String[50];
		String[] month_list=new String[12];
		String[] day_list=new String[31];
		for(int i=0;i<year_list.length;i++) {
			year_list[i]=Integer.toString(year+i);
		}
		for(int i=0;i<month_list.length;i++) {
			if(i+1<10)
				month_list[i]="0".concat(Integer.toString(i+1));
			else
				month_list[i]=Integer.toString(i+1);
		}
		for(int i=0;i<day_list.length;i++) {
			if(i+1<10)
				day_list[i]="0".concat(Integer.toString(i+1));
			else
				day_list[i]=Integer.toString(i+1);
		}
		Yearselect= new JComboBox<String>(year_list);
		Monthselect= new JComboBox<String>(month_list);
		Dayselect= new JComboBox<String>(day_list);
		Yearselect.setFont(Yearselect.getFont().deriveFont(20.0f));
		Monthselect.setFont(Monthselect.getFont().deriveFont(20.0f));
		Dayselect.setFont(Dayselect.getFont().deriveFont(20.0f));
		Yearselect.addActionListener(this);
		Monthselect.addActionListener(this);
		Dayselect.addActionListener(this);
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
	/*test
	public static void main(String[] args) {
		new AddItemMenu();
	}
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Yearselect||e.getSource()==Monthselect||e.getSource()==Dayselect)
			timer.restart();
		if(e.getSource()==add) {
			String y=(String)Yearselect.getSelectedItem();
			String m=(String)Monthselect.getSelectedItem();
			String d=(String)Dayselect.getSelectedItem();
			return_date=Integer.parseInt(y.concat(m).concat(d));
			timer.stop();
			return_value=1;
		}
		if(e.getSource()==cancel) {
			timer.stop();
			return_value=0;
		}
	}

}
