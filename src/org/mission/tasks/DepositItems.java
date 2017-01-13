package org.mission.tasks;

import org.mission.OrionRJ;
import org.mission.data.RJVars;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/12/2017.
 */
public class DepositItems extends Task<OrionRJ> {

    public DepositItems(OrionRJ mission) {
        super(mission);
    }

    public boolean validate() {
        return inventory.getItems().length > 0 && !RJVars.get().has_emptied_inventory;
    }

    public void execute() {
        if (bank.isOpen()) {
            if (bank.depositAll())
                if (Timing.waitCondition(() -> !inventory.isFull(), 150, random(2000, 2500)))
                    RJVars.get().has_emptied_inventory = true;
        } else {
            if (bankUtils.isInBank()) {
                if (bankUtils.open())
                    Timing.waitCondition(conditions.BANK_OPEN, 150, random(2000, 2500));
            } else {
                if (getWalking().webWalk(bankUtils.getAllBanks(false, false)))
                    Timing.waitCondition(() -> bankUtils.isInBank(), 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Depositing items";
    }

}

