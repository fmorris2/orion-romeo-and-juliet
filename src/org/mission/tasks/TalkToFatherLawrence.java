package org.mission.tasks;

import org.mission.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.mission.Mission;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class TalkToFatherLawrence extends Task {

    private NPC father_lawrence;

    public TalkToFatherLawrence(Mission mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 30;
    }

    @Override
    public void execute() {
        father_lawrence = npcs.closest(QuestNPC.FATHER_LAWRENCE.getNPCArea(), QuestNPC.FATHER_LAWRENCE.getNPCName());
        if (father_lawrence != null) {
            iFact.dialogue("Talk-to", QuestNPC.FATHER_LAWRENCE.getNPCName(), 20).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.FATHER_LAWRENCE.getNPCArea(), () -> {
                father_lawrence = npcs.closest(QuestNPC.FATHER_LAWRENCE.getNPCArea(), QuestNPC.FATHER_LAWRENCE.getNPCName());
                return father_lawrence != null && father_lawrence.isVisible();
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.FATHER_LAWRENCE.getNPCArea(), QuestNPC.FATHER_LAWRENCE.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Talking to Father Lawrence";
    }
}

