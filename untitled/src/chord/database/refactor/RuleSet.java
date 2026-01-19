package chord.database.refactor;


public final class RuleSet {
    private RuleSet(){

    }

    public static final String[] germanNames = {"c", "cis", "des", "d", "dis", "es", "e", "eis", "f", "fis","ges", "g", "gis", "as", "a", "ais","b", "h", "his"};
    public static final String[] englishNames = {"c", "c sharp", "d flat", "d", "d sharp", "e flat", "e", "e sharp", "f", "f sharp","g flat", "g", "g sharp", "a flat", "a", "a sharp","b flat", "b", "b sharp"};
    public static final String[] romanNames = {"do", "do di", "re be", "re", "re di", "mi be", "mi", "mi di", "fa", "fa di","sol be", "sol", "sol di", "la be", "la", "la di","si be", "si", "si di"};
    public static final int[] fakeNotes = {7, 18};
    public static final Harmony majorChord = new Harmony(new int[]{0, 6, 11, 19}, "Dur", "major", "Maggiore" );
    public static final Harmony minorChord = new Harmony(new int[]{0, 5, 11, 19}, "Moll", "minor", "Minore");

}
