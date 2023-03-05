package domain.objects.particles;

import java.util.ArrayList;
import java.util.List;

import domain.objects.obstacle.types.Obstacle;

public class GiftBoxList {
	private List<GiftBox> boxList = new ArrayList<>();
	private static GiftBoxList instance;
	
	private GiftBoxList() {}

	public static GiftBoxList getInstance() {
		if (instance == null) {
			instance = new GiftBoxList();
		}
		return instance;
	}

	public void initilizeList() {
		boxList = new ArrayList<>();
	}

	public void addGiftBox(Obstacle obstacle) {
		GiftBox GiftBox = new GiftBox();
		GiftBox.setX(obstacle.getX_position());
		GiftBox.setY(obstacle.getY_position());
		GiftBox.setUpwardPathX(obstacle.getX_position());
		GiftBox.setUpwardPathY(obstacle.getY_position());
		boxList.add(GiftBox);
	}
	public void addGiftBox2(GiftBox gb) {
	
		boxList.add(gb);
	}
	
	public List<GiftBox> getList() {
		return boxList;
	}
	public void setList(List<GiftBox> boxList2) {
		 this.boxList=boxList2;
	}
	

}