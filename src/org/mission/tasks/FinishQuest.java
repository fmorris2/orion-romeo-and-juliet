package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class FinishQuest extends Task<OrionRJ> {

    private NPC romeo;

    public FinishQuest(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 60;
    }

    @Override
    public void execute() {
        romeo = npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName());
        if (romeo != null || dialogues.inDialogue()) {
            iFact.dialogue("Talk-to", QuestNPC.ROMEO.getNPCName(), 20).execute();
        } else {
            if (configs.get(1021) == 192)
                return;

            if (walkUtils.walkToArea(QuestNPC.ROMEO.getNPCArea(), () -> {
                romeo = npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName());
                return romeo != null && romeo.isVisible() && map.canReach(romeo);
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.ROMEO.getNPCArea(), QuestNPC.ROMEO.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Finishing Romeo & Juliet";
    }
}

