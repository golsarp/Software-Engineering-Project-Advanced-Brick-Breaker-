package domain.listeners;

import domain.actors.ymir.ability.YmirAbility;

public interface YmirListener {
	void ymirActivatedEvent(YmirAbility ability);
}
