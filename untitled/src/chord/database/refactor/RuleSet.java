package chord.database.refactor;


import java.util.ArrayList;
import java.util.List;

public final class RuleSet {
    private RuleSet(){

    }

    public static final String[] germanNames = {"c", "cis", "des", "d", "dis", "es", "e", "eis", "f", "fis","ges", "g", "gis", "as", "a", "ais","b", "h", "his"};
    public static final String[] englishNames = {"c", "c sharp", "d flat", "d", "d sharp", "e flat", "e", "e sharp", "f", "f sharp","g flat", "g", "g sharp", "a flat", "a", "a sharp","b flat", "b", "b sharp"};
    public static final String[] romanNames = {"do", "do di", "re be", "re", "re di", "mi be", "mi", "mi di", "fa", "fa di","sol be", "sol", "sol di", "la be", "la", "la di","si be", "si", "si di"};
    public static final String[] indonesianNames = {
            "do",        // c
            "do kres",   // cis
            "re mol",    // des
            "re",        // d
            "re kres",   // dis
            "mi mol",    // es
            "mi",        // e
            "mi kres",   // eis (theoretisch)
            "fa",        // f
            "fa kres",   // fis
            "sol mol",   // ges
            "sol",       // g
            "sol kres",  // gis
            "la mol",    // as
            "la",        // a
            "la kres",   // ais
            "si mol",    // b flat
            "si",        // b
            "si kres"    // his (theoretisch)
    };

    public static final int[] fakeNotes = {7, 18};
    public static final Harmony majorChord    = new Harmony(new int[]{0, 6, 11, 19}, "Dur",  "major", "Maggiore", "mayor");
    public static final Harmony minorChord    = new Harmony(new int[]{0, 5, 11, 19}, "Moll", "minor", "Minore",   "minor");
    public static final Harmony dominantSept  = new Harmony(new int[]{0, 6, 11, 16}, "7",    "7",     "7",        "7");
/*
    // Triads
    public static final Harmony diminishedChord  = new Harmony(new int[]{0, 5, 10, 19}, "Vermindert", "diminished", "Diminuito"); // 1-b3-b5
    public static final Harmony augmentedChord   = new Harmony(new int[]{0, 6, 12, 19}, "Übermäßig", "augmented",  "Aumentato");  // 1-3-#5

    // 7th chords
    public static final Harmony majorSept        = new Harmony(new int[]{0, 6, 11, 17}, "maj7", "maj7", "maj7"); // 1-3-5-7
    public static final Harmony minorSept        = new Harmony(new int[]{0, 5, 11, 16}, "m7",   "m7",   "m7");   // 1-b3-5-b7
    public static final Harmony halfDiminished7  = new Harmony(new int[]{0, 5, 10, 16}, "ø7",   "m7b5","ø7");   // 1-b3-b5-b7
    public static final Harmony diminished7      = new Harmony(new int[]{0, 5, 10, 15}, "dim7", "dim7","dim7"); // 1-b3-b5-bb7

    // Suspended
    public static final Harmony sus2Chord        = new Harmony(new int[]{0, 3, 11, 19}, "sus2", "sus2", "sus2"); // 1-2-5
    public static final Harmony sus4Chord        = new Harmony(new int[]{0, 8, 11, 19}, "sus4", "sus4", "sus4"); // 1-4-5
*/
    public static final Harmony[] allHarmonies = {majorChord, minorChord, dominantSept};

}
