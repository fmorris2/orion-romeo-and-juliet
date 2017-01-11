package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class PickCadavaBerry extends Task<OrionRJ> {

    private RS2Object cadava_bush;
    private RS2Object empty_cadava_bush;

    public PickCadavaBerry(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return !inventory.contains(QuestItem.CADAVA_BERRY.getItemID()) && configs.get(144) < 50;
    }

    @Override
    public void execute() {
        cadava_bush = objects.closest(QuestItem.CADAVA_BERRY.getObjectArea(), QuestItem.CADAVA_BERRY.getObjectIDs());
        if (cadava_bush != null) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            if (cadava_bush.interact(QuestItem.CADAVA_BERRY.getAction()))
                Timing.waitCondition(() -> inventory.getItems().length != inventory_cache.length || myPlayer().isMoving(), 150, random(2000, 2500));
        } else {
            empty_cadava_bush = objects.closest(QuestItem.CADAVA_BERRY.getObjectArea(), 23627);
            if (empty_cadava_bush == null) {
                if (walkUtils.walkToArea(QuestItem.CADAVA_BERRY.getObjectArea(), () -> {
                    cadava_bush = objects.closest(QuestItem.CADAVA_BERRY.getObjectArea(), QuestItem.CADAVA_BERRY.getObjectIDs());
                    return cadava_bush != null && cadava_bush.isVisible();
                })) {
                    Timing.waitCondition(() -> objects.closest(QuestItem.CADAVA_BERRY.getObjectArea(), QuestItem.CADAVA_BERRY.getObjectIDs()) != null, 150, random(2000, 2500));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Picking Cadava Berry";
    }
}

