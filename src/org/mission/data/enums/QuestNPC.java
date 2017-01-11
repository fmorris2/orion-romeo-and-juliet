package org.mission.data.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public enum QuestNPC {

    JULIET("Juliet", new Area(new int[][]{
            {3147, 3430, 1},
            {3166, 3429, 1},
            {3165, 3424, 1},
            {3149, 3424, 1}
    })),
    ROMEO("Romeo", new Area(new int[][]{
            {3204, 3438},
            {3224, 3437},
            {3223, 3419},
            {3205, 3420}
    })),
    FATHER_LAWRENCE("Father Lawrence", new Area(new int[][]{
            {3250, 3489},
            {3261, 3489},
            {3261, 3469},
            {3251, 3469}
    })),
    APOTHECARY("Apothecary", new Area(new int[][]{
            {3191, 3408},
            {3199, 3408},
            {3200, 3400},
            {3191, 3400}
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

