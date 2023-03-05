package ui.buildingmode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import constants.GameConstants;
import domain.controller.GameController;
import domain.objects.obstacle.ObstacleConfiguration;
import domain.objects.obstacle.ObstaclePositionSetter;
import domain.objects.obstacle.types.Obstacle;

public class BuildingModeMainPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	//OVERVIEW: BuildingModeMainPanel is a panel for displaying and changing the positions of the obstacles
	
	public static int x;
	public static int y;
	private int prex; // previous x coordinate of an obstacle moved by the player
	private int prey; // previous y coordinate of an obstacle moved by the player
	private Rectangle rect;
	private Obstacle draggedObstacle;
	private Rectangle intersect;
	private ObstaclePositionSetter setter;
	private ArrayList<Obstacle> listOfObstacle;
	private ObstacleConfiguration obstacleConfiguration;

	public BuildingModeMainPanel() {

		obstacleConfiguration = GameController.getInstance().getObstacleConfiguration();
		JButton showButton = new JButton("Show Obstacles");
		showButton.addActionListener(e -> {
			repaint();
		});

		this.add(showButton);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.BLACK);
	}

	public void paintComponent(Graphics g) {
		listOfObstacle = GameController.getInstance().getListOfObstacles();
		super.paintComponent(g);

		for (Obstacle ob : listOfObstacle) {
			drawObstacle(g, ob);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
    try {
		int dx = e.getX() - x;
		int dy = e.getY() - y;

		setter = new ObstaclePositionSetter(draggedObstacle, draggedObstacle.getX_position() + dx,
				draggedObstacle.getY_position() + dy, obstacleConfiguration);
		setter.setPosition(prex, prey, draggedObstacle.getX_position() + dx, draggedObstacle.getY_position() + dy);

		x += dx;
		y += dy;
    }catch(NullPointerException ex) {
    	
    }
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {

		x = e.getX();
		y = e.getY();

		for (Obstacle ob : listOfObstacle) {
			rect = new Rectangle(ob.getX_position(), ob.getY_position(), ob.getLength(), ob.getWidth());
			if (rect.contains(x, y)) {

				draggedObstacle = ob;
				prex = draggedObstacle.getX_position();
				prey = draggedObstacle.getY_position();

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
		setter.fitToExistingPosition(prex, prey, x, y);
		intersect = new Rectangle(draggedObstacle.getX_position(), draggedObstacle.getY_position(),
				draggedObstacle.getLength(), draggedObstacle.getWidth());

		for (Obstacle ob : listOfObstacle) {
			rect = new Rectangle(ob.getX_position(), ob.getY_position(), ob.getLength(), ob.getWidth());
			if (ob != draggedObstacle) {

				if (Collision(rect, intersect)) {
					setter.setPosition(x, y, prex, prey);
				}
			}
		}
		// if the dragged obstacle's position is out of the editing panel (running mode
		// frame)
		if (e.getX() + draggedObstacle.getLength() > GameConstants.RUNNING_MODE_FRAME_WIDTH
				|| e.getX() - draggedObstacle.getLength() < 0
				|| e.getY() + draggedObstacle.getLength() > GameConstants.RUNNING_MODE_FRAME_HEIGHT / 2
				|| e.getY() < 0) {

			// if the dragged obstacle's position is within the trash zone, remove obstacle
			// from the obstacle list
			if ((GameConstants.BUILDING_MODE_FRAME_HEIGHT / 2 < (e.getY() + draggedObstacle.getLength()))
					&& (GameConstants.BUILDING_MODE_FRAME_HEIGHT > (e.getY() + draggedObstacle.getLength()))
					&& (GameConstants.RUNNING_MODE_FRAME_WIDTH < (e.getX() + draggedObstacle.getLength()))
					&& (GameConstants.BUILDING_MODE_FRAME_WIDTH > (e.getX() + draggedObstacle.getLength()))) {

				listOfObstacle.remove(draggedObstacle);

				// if it's not in the trash zone, set the obstacle's position back to its
				// previous position
			} else {
				setter.setPosition(x, y, prex, prey);
			}
		}
		
		}catch(NullPointerException ex) {
			
		}
		repaint();
	}

	public boolean Collision(Rectangle rect1, Rectangle rect2) {
		// EFFECTS: returns true if rect1 and rect2 intersects, else returns false

		if (rect1.intersects(rect2)) {
			return true;
		} else
			return false;
	}
	
	public static void drawObstacle(Graphics g, Obstacle ob) {

		int x_obst = ob.getX_position();
		int y_obst = ob.getY_position();
		int width_obst = ob.getWidth();
		int length_obst = ob.getLength();

		if (ob.getColor() == "blue") {
			g.setColor(Color.blue);
		} else if (ob.getColor() == "red") {
			g.setColor(Color.red);
		} else if (ob.getColor() == "yellow") {
			g.setColor(Color.yellow);
		} else if (ob.getColor() == "green") {
			g.setColor(Color.green);
		}
		if (ob.isIs_rectangle()) {
			g.fillRect(x_obst, y_obst, length_obst, width_obst);
			if (ob.getHasText()) {
				g.setColor(Color.DARK_GRAY);
				g.drawString(String.valueOf(ob.getLife()), x_obst + 10, y_obst + 15);
			}
		} else {
			g.fillOval(x_obst, y_obst, width_obst, width_obst);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
