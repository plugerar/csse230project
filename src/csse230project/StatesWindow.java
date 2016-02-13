package csse230project;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class StatesWindow extends Applet implements MouseListener {
	private Image img;
	private URL base;

	@Override
	public void init() {
		addMouseListener(this);
		this.base = getDocumentBase();
		this.img = getImage(this.base, "USbackground800j.jpg");
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(this.img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println("x " + me.getX() + " y " + me.getY());
	}

	@Override
	public void mouseEntered(MouseEvent me) {
	}

	@Override
	public void mouseExited(MouseEvent me) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
	}

	@Override
	public void mouseReleased(MouseEvent me) {
	}
}
