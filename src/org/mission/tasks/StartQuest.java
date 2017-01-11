package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class StartQuest extends Task<OrionRJ> {

    private NPC juliet;

    public StartQuest(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 0;
    }

    @Override
    public void execute() {
        juliet = npcs.closest(QuestNPC.JULIET.getNPCArea(), QuestNPC.JULIET.getNPCName());
        if (juliet != null) {
            iFact.dialogue("Talk-to", QuestNPC.APOTHECARY.getNPCName(), 20, 3).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.JULIET.getNPCArea(), () -> {
                juliet = npcs.closest(QuestNPC.JULIET.getNPCArea(), QuestNPC.JULIET.getNPCName());
                return juliet != null && juliet.isVisible();
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.JULIET.getNPCArea(), QuestNPC.JULIET.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Starting Romeo & Juliet";
    }

}

