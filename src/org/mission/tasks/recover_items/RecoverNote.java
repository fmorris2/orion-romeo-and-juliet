package org.mission.tasks.recover_items;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestNPC;
import org.mission.data.enums.QuestObject;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/12/2017.
 */
public class RecoverNote extends Task<OrionRJ> {

    private NPC juliet;

    public RecoverNote(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 20 && !inventory.contains(QuestObject.MESSAGE.getItemID());
    }

    @Override
    public void execute() {
        juliet = npcs.closest(QuestNPC.JULIET.getNPCArea().setPlane(1), QuestNPC.JULIET.getNPCName());
        if (juliet != null) {
            iFact.dialogue("Talk-to", QuestNPC.JULIET.getNPCName(), 20).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.JULIET.getNPCArea().setPlane(1), () -> {
                juliet = npcs.closest(QuestNPC.JULIET.getNPCArea().setPlane(1), QuestNPC.JULIET.getNPCName());
                return juliet != null && juliet.isVisible();
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.JULIET.getNPCArea().setPlane(1), QuestNPC.JULIET.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Recovering note";
    }

}

