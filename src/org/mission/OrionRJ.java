package org.mission;

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
        return false;
    }

    @Override
    public String getMissionName() {
        return null;
    }

    @Override
    public String getCurrentTaskName() {
        return null;
    }

    @Override
    public String getEndMessage() {
        return null;
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

    @Override
    public void onMissionStart() {
    }

    @Override
    public void resetPaint() {
    }

}
