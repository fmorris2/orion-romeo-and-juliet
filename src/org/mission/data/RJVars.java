package org.mission.data;

/**
 * Created by Sphiinx on 1/10/2017.
 */
public class RJVars {

    public static RJVars vars;

    public static RJVars get() {
        return vars == null ? vars = new RJVars() : vars;
    }

    public static void reset() {
        vars = null;
    }

    public boolean has_emptied_inventory;
    public boolean has_checked_bank_for_potion;

}

