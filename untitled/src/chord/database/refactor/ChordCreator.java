package chord.database.refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChordCreator {
    private String startingTone;
    private String chordType;
    private String[] ladder;
    private String language;
    private LanguageHelper languageHelper;

    public ChordCreator(String startingTone, String chordType, String language) throws Exception {
        this.startingTone = startingTone;
        this.chordType = chordType;
        this.language = language;
        this.languageHelper = new LanguageHelper(language);
        this.ladder = languageHelper.getLongLadder();
    }

    private int replaceFakeNotes(int n){
        for (int i = 0; i < RuleSet.fakeNotes.length; i++){
            int fakeVal = RuleSet.fakeNotes[i];
            if(fakeVal == n || fakeVal + ladder.length -1 == n){
                n++;
            }
        }

        return n;
    }

    public Chord getChord() throws Exception {
        Harmony harmony;

        int[] mChords = {};
        switch (chordType){
            case "major":
                harmony = RuleSet.majorChord;
                break;
            case "minor":
                harmony = RuleSet.minorChord;
                break;
            default:
                throw new Exception("Unbekannter chordType: " + chordType);
        }
        String chordname = languageHelper.getChordName(harmony);

        mChords = harmony.chord;
        int additioner = 0;
        for (int i = 0; i < ladder.length; i++) {
            if(ladder[i].equals(startingTone)){
                additioner = i;
                break;
            }

        }
        List<String> retChord = new ArrayList<>();

        int[] intChords = mChords.clone();

        for(int i = 0; i< mChords.length; i++){
            int ladder_pos = mChords[i] + additioner;
        //    ladder_pos = this.replaceFakeNotes(ladder_pos);
            intChords[i] = ladder_pos;
            retChord.add(ladder[ladder_pos]);
        }

        return new Chord(retChord.get(0), retChord.get(1), retChord.get(2), retChord.get(3), chordname, intChords, harmony);
    }
}
