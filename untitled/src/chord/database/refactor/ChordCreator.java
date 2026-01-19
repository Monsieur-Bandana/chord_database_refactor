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

    public ChordCreator(String startingTone, String chordType, String language) {
        this.startingTone = startingTone;
        this.chordType = chordType;
        this.language = language;
        String[] ladderhelper;
        switch (language){
            case "german":
                ladderhelper = RuleSet.germanNames;
                break;
            case "english":
                ladderhelper = RuleSet.englishNames;
                break;
            case "roman":
                ladderhelper = RuleSet.romanNames;
                break;
            default:
                throw new Exception("Unbekannte Sprache: " + language);
        }
        this.ladder = Stream.concat(
                Arrays.stream(ladderhelper),
                Arrays.stream(ladderhelper)
        ).toArray(String[]::new);
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
        String chordname;
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
        switch (language){
            case "german":
                chordname = harmony.german;
                break;
            case "english":
                chordname = harmony.english;
                break;
            case "roman":
                chordname = harmony.roman;
                break;
            default:
                throw new Exception("Unbekannter Sproch: " + language);
        }

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
