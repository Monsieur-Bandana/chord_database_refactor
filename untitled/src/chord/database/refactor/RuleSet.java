package chord.database.refactor;


public final class RuleSet {
    private RuleSet(){

    }

    public static final String[] hasheslist = {"c", "cis", "d", "dis", "e", "f", "fis", "g", "gis", "a", "ais", "h"};
    public static final String[] blist = {"c", "des", "d", "es", "e", "f", "ges", "g", "as", "a", "b", "h"};
    public static final int[] majorChord = {0, 4, 7, 12};
    public static final int[] minorChord = {0, 3, 7, 12};
}
