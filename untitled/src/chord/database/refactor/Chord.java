package chord.database.refactor;

public class Chord {
    public String baseTone;
    public String scdTone;
    public String thirdTone;
    public String highPitchBaseTone;
    public String harmony;

    public Chord(String baseTone, String scdTone, String thirdTone, String highPitchBaseTone, String harmony) {
        this.baseTone = baseTone;
        this.scdTone = scdTone;
        this.thirdTone = thirdTone;
        this.highPitchBaseTone = highPitchBaseTone;
        this.harmony = harmony;
    }
}
