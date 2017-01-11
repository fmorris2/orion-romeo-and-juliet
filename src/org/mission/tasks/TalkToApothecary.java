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
public class TalkToApothecary extends Task<OrionRJ> {

    private NPC apothecary;

    public TalkToApothecary(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 40 || (configs.get(144) == 50 && inventory.contains(QuestItem.CADAVA_BERRY.getItemID()));
    }

    @Override
    public void execute() {
        apothecary = npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName());
        if (apothecary != null) {
            iFact.dialogue("Talk-to", QuestNPC.APOTHECARY.getNPCName(), 20).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.APOTHECARY.getNPCArea(), () -> {
                apothecary = npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName());
                return apothecary != null && apothecary.isVisible();
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Talking to Apothecary";
    }

}

