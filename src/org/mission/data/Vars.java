package org.mission.data;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

