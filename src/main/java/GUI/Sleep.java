package GUI;

import Logic.DVM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sleep extends JFrame implements MouseListener {

    private int return_value = -1;

    public Sleep() {
        this.setPreferredSize(new Dimension(600, 800));
        this.setTitle("DVM " + DVM.getCurrentID());
        this.addMouseListener(this);
        this.setBackground(new Color((float) 1.0, (float) 1.0, (float) 1.0));

        //라벨패널
        JPanel labelpanel = new JPanel();
        JLabel label = new JLabel("사용하려면 화면을 터치하세요");
        label.setFont(label.getFont().deriveFont(15.0f));
        labelpanel.add(label);

        add(labelpanel, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return_value = 1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public int getReturn_value() { return return_value; }

    public void setReturn_value(int return_value) { this.return_value = return_value; }

}
