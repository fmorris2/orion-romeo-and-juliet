package org.mission.tasks.recover_items;

import org.mission.OrionRJ;
import org.mission.data.RJVars;
import org.mission.data.enums.QuestNPC;
import org.mission.data.enums.QuestObject;
import org.osbot.rs07.api.model.Item;
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
        return configs.get(144) == 50 && !inventory.contains(QuestObject.CADAVA_POTION.getItemID()) && inventory.contains(QuestObject.CADAVA_BERRY.getItemID()) && RJVars.get().has_checked_bank_for_potion || configs.get(144) == 50 && !inventory.contains(QuestObject.CADAVA_POTION.getItemID()) && !RJVars.get().has_checked_bank_for_potion;
    }

    @Override
    public void execute() {
        if (!RJVars.get().has_checked_bank_for_potion) {
            if (bank.isOpen()) {
                final Item item_to_withdraw = bank.getItem(QuestObject.CADAVA_POTION.getItemID());
                if (item_to_withdraw != null) {
                    final Item[] inventory_cache = inventory.getItems();
                    if (bank.withdraw(item_to_withdraw.getId(), 1))
                        if (Timing.waitCondition(() -> inventory_cache.length != inventory.getItems().length, 150, random(2000, 2500)))
                            RJVars.get().has_checked_bank_for_potion = true;
                } else {
                    RJVars.get().has_checked_bank_for_potion = true;
                }
            } else {
                if (bankUtils.isInBank()) {
                    if (bankUtils.open())
                        Timing.waitCondition(conditions.BANK_OPEN, 150, random(2000, 2500));
                } else {
                    if (getWalking().webWalk(bankUtils.getAllBanks(false, false)))
                        Timing.waitCondition(() -> bankUtils.isInBank(), 150, random(2000, 2500));
                }
            }
        } else {
            if (inventory.contains(QuestObject.CADAVA_BERRY.getItemID())) {
                apothecary = npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName());
                if (apothecary != null && map.canReach(apothecary)) {
                    iFact.dialogue("Talk-to", QuestNPC.APOTHECARY.getNPCName(), 20).execute();
                } else {
                    if (walkUtils.walkToArea(QuestNPC.APOTHECARY.getNPCArea(), () -> {
                        apothecary = npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName());
                        return apothecary != null && apothecary.isVisible() && map.canReach(apothecary);
                    })) {
                        Timing.waitCondition(() -> npcs.closest(QuestNPC.APOTHECARY.getNPCArea(), QuestNPC.APOTHECARY.getNPCName()) != null, 150, random(2000, 2500));
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Recovering potion";
    }

}
