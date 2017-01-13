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
public class RecoverPotion extends Task<OrionRJ> {

    private NPC apothecary;

    public RecoverPotion(OrionRJ mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(144) == 50 && inventory.contains(QuestObject.CADAVA_BERRY.getItemID()) && !inventory.contains(QuestObject.CADAVA_POTION.getItemID());
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
