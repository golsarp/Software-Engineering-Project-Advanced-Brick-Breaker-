package domain.actors.player.ability;

import domain.objects.phantasm.Cannon;
import domain.objects.phantasm.NoblePhantasm;

public class MagicalHex extends GameObjectDecorator {

	private double addXleft = 0;
	private double addYleft = 0;
	private double addXright = 0;
	private double addYright = 0;
	private Cannon canon;
	private NoblePhantasm nb;

	public MagicalHex(GameObject object, Cannon canon, NoblePhantasm nb) {
		this.nb = nb;
		this.object = object;
		this.canon = canon;
	}

	@Override
	public void enhance() {
		// TODO Auto-generated method stub
		int mul = 1;
		if (nb.getLength() != 120) {
			mul = 2;
		}
		double converted_in_degrees = -Math.PI / 180 * nb.getRot_measure();
		if (nb.getRot_measure() == 0) {
			addXleft = 0;
			addYleft = 0;
			addXright = 0;
			addYright = 0;
		} else if (nb.getRot_measure() > 0) {
			addYleft = -(nb.getLength() / 2 + (nb.getLength() / 2) * Math.sin(converted_in_degrees));
			addXleft = (nb.getLength() / 2) - (nb.getLength() / 2) * Math.cos(converted_in_degrees);

			addXright = mul * (-24) + (+nb.getLength() / 2 - (nb.getLength() / 2) * Math.cos(converted_in_degrees));
			addYright = mul * 20 + ((nb.getLength() / 2) + (nb.getLength() / 2) * Math.sin(converted_in_degrees));
			
		} else if (nb.getRot_measure() < 0) {
			addYleft = (mul * 20) + (nb.getLength() / 2 - (nb.getLength() / 2) * Math.cos(converted_in_degrees));
			addXleft = (mul * 24) - ((nb.getLength() / 2) - (nb.getLength() / 2) * Math.sin(converted_in_degrees));

			addYright = -(nb.getLength() / 2 - (nb.getLength() / 2) * Math.cos(converted_in_degrees));
			addXright = -((nb.getLength() / 2) - (nb.getLength() / 2) * Math.sin(converted_in_degrees));

		}

		double deltaX = 0;
		double deltaY = 0;
		converted_in_degrees = -Math.PI / 180 * nb.getRot_measure();
		if (nb.getRot_measure() == 0) {
			deltaX = 0;
			deltaY = -1;
		} else if (nb.getRot_measure() > 0) {
			deltaY = Math.sin(converted_in_degrees);
			deltaX = Math.cos(converted_in_degrees);
		} else if (nb.getRot_measure() < 0) {
			deltaY = -Math.sin(converted_in_degrees);
			deltaX = -Math.cos(converted_in_degrees);
		}

		canon.getCanonBulletList().addParticles(addXleft, addYleft, addXright, addYright, canon, deltaX, deltaY);
	}

}
