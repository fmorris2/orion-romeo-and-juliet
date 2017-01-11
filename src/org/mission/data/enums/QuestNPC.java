package org.mission.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public enum QuestNPC {

    JULIET("Juliet", new Area(new Position[]{
            new Position(3154, 3427, 1),
            new Position(3162, 3427, 1),
            new Position(3162, 3424, 1),
            new Position(3154, 3424, 1)
    })),
    ROMEO("Romeo", new Area(new Position[]{
            new Position(3199, 3440, 0),
            new Position(3223, 3440, 0),
            new Position(3224, 3409, 0),
            new Position(3200, 3409, 0)
    })),
    FATHER_LAWRENCE("Father Lawrence", new Area(new Position[]{
            new Position(3250, 3489, 0),
            new Position(3261, 3489, 0),
            new Position(3261, 3469, 0),
            new Position(3251, 3469, 0)
    })),
    APOTHECARY("Apothecary", new Area(new Position[]{
            new Position(3191, 3408, 0),
            new Position(3199, 3408, 0),
            new Position(3200, 3400, 0),
            new Position(3191, 3400, 0)
    }));

    private final String NPC_NAME;
    private final Area NPC_AREA;

    QuestNPC(String npc_name, Area npc_area) {
        this.NPC_NAME = npc_name;
        this.NPC_AREA = npc_area;
    }

    public String getNPCName() {
        return NPC_NAME;
    }

    public Area getNPCArea() {
        return NPC_AREA;
    }

}

