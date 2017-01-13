package org.mission;

import org.mission.tasks.*;

import org.mission.tasks.recover_items.RecoverNote;
import org.mission.tasks.recover_items.RecoverPotion;
import viking.framework.goal.GoalList;
import viking.framework.mission.Mission;
import viking.framework.script.VikingScript;
import viking.framework.task.TaskManager;

public class OrionRJ extends Mission {

    private final TaskManager<OrionRJ> TASK_MANAGER = new TaskManager<>(this);

    public OrionRJ(VikingScript script) {
        super(script);
    }

    @Override
    public boolean canEnd() {
        return configs.get(144) == 100;
    }

    @Override
    public String getMissionName() {
        return "Orion R&J";
    }

    @Override
    public String getCurrentTaskName() {
        return TASK_MANAGER.getStatus();
    }

    @Override
    public String getEndMessage() {
        return "Orion R&J has ended!";
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public String[] getMissionPaint() {
        return null;
    }

    @Override
    public int execute() {
        TASK_MANAGER.loop();
        return 150;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void onMissionStart() {
        TASK_MANAGER.addTask(new RJ_DepositItems(this), new RecoverNote(this), new PickCadavaBerry(this), new RecoverPotion(this), new StartQuest(this), new DeliverNote(this), new TalkToFatherLawrence(this), new TalkToApothecary(this), new DeliverPotion(this), new FinishQuest(this));
    }

    @Override
    public void resetPaint() {
    }

}
