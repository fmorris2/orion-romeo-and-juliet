package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestObject;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.RS2Object;

import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class PickCadavaBerry extends Task<OrionRJ> {
	
    public PickCadavaBerry(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) < 60 && !inventory.contains(QuestObject.CADAVA_BERRY.getItemID()) && !inventory.contains(QuestObject.CADAVA_POTION.getItemID());
    }

    @Override
    public void execute() {
    	final Area AREA = QuestObject.CADAVA_BERRY.getObjectArea();
    	if(AREA.contains(myPosition()))
    		walkUtils.walkToArea(AREA);
    	else
    	{
    		RS2Object bush = objects.closest(QuestObject.CADAVA_BERRY.getObjectIDs());
    		if(bush != null && iFact.clickObject("Pick-from", bush).execute())
    			Timing.waitCondition(() -> inventory.contains(QuestObject.CADAVA_BERRY.getItemID()), 5000);
    	}
 
    }

    @Override
    public String toString() {
        return "Picking Cadava Berry";
    }
}

