package csse230project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapPanel extends JPanel implements ActionListener{
	public MapPanel(){
		this.add(new JLabel("map!"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}