package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestItem;
import org.mission.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class DeliverNote extends Task<OrionRJ> {

    private NPC romeo;

    public DeliverNote(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 20 && inventory.contains(QuestItem.MESSAGE.getItemID());
    }

    @Override
    public void execute() {
        romeo = npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName());
        if (romeo != null) {
            iFact.dialogue("Talk-to", QuestNPC.ROMEO.getNPCName(), 20, 4).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.ROMEO.getNPCArea(), () -> {
                romeo = npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName());
                return romeo != null && romeo.isVisible();
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Delivering note to Romeo";
    }

}

