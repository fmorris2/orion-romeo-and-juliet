package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestObject;
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
        return configs.get(144) < 60 && !inventory.contains(QuestObject.CADAVA_BERRY.getItemID()) && !inventory.contains(QuestObject.CADAVA_POTION.getItemID());
    }

    @Override
    public void execute() {
        cadava_bush = objects.closest(QuestObject.CADAVA_BERRY.getObjectArea(), QuestObject.CADAVA_BERRY.getObjectIDs());
        if (cadava_bush != null && map.canReach(cadava_bush)) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            if (cadava_bush.interact(QuestObject.CADAVA_BERRY.getAction()))
                Timing.waitCondition(() -> inventory.getItems().length != inventory_cache.length || myPlayer().isMoving(), 150, random(2000, 2500));
        } else {
            empty_cadava_bush = objects.closest(QuestObject.CADAVA_BERRY.getObjectArea(), 23627);
            if (empty_cadava_bush == null) {
                if (walkUtils.walkToArea(QuestObject.CADAVA_BERRY.getObjectArea(), () -> {
                    cadava_bush = objects.closest(QuestObject.CADAVA_BERRY.getObjectArea(), QuestObject.CADAVA_BERRY.getObjectIDs());
                    return cadava_bush != null && cadava_bush.isVisible() && map.canReach(cadava_bush);
                })) {
                    Timing.waitCondition(() -> objects.closest(QuestObject.CADAVA_BERRY.getObjectArea(), QuestObject.CADAVA_BERRY.getObjectIDs()) != null, 150, random(2000, 2500));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Picking Cadava Berry";
    }
}

