package chord.database.refactor;

import java.util.ArrayList;
import java.util.List;

public class Chord {
    public String baseTone;
    public String scdTone;
    public String thirdTone;
    public String highPitchBaseTone;
    public String harmony;
    public String[] tones;
    public String chordname;
    public int[] numericTones;
    public Harmony harmonyH;
    public String parallelChord;
    public String parallelHarmony;
    public String shortName;

    public Chord(String baseTone, String scdTone, String thirdTone, String highPitchBaseTone, String harmony, int[] numericTones, Harmony harmonyH, String parallelChord, String parallelHarmony, String shortName) {
        this.baseTone = baseTone;
        this.scdTone = scdTone;
        this.thirdTone = thirdTone;
        this.highPitchBaseTone = highPitchBaseTone;
        this.harmony = harmony;
        this.numericTones = numericTones;
        this.harmonyH = harmonyH;
        this.parallelChord = parallelChord;
        this.parallelHarmony = parallelHarmony;
        this.shortName = shortName;

        tones = new String[]{this.baseTone, this.scdTone, this.thirdTone, this.highPitchBaseTone};
        chordname = baseTone.toUpperCase() + "-" + harmony;
    }
}
