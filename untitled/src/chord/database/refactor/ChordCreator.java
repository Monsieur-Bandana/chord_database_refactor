package chord.database.refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ChordCreator {
    private String startingTone;
    private String chordType;
    private String[] ladder;

    public ChordCreator(String startingTone, String chordType) {
        this.startingTone = startingTone;
        this.chordType = chordType;
        ladder = Stream.concat(
                Arrays.stream(RuleSet.germanNames),
                Arrays.stream(RuleSet.germanNames)
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
        int[] mChords = {};
        switch (chordType){
            case "major":
                mChords = RuleSet.majorChord;
                break;
            case "minor":
                mChords = RuleSet.minorChord;
                break;
            default:
                throw new Exception("Unbekannter chordType: " + chordType);
        }

        int additioner = 0;
        for (int i = 0; i < ladder.length; i++) {
            if(ladder[i].equals(startingTone)){
                additioner = i;
                break;
            }

        }
        List<String> retChord = new ArrayList<>();

        for(int i = 0; i< mChords.length; i++){
            int ladder_pos = mChords[i] + additioner;
        //    ladder_pos = this.replaceFakeNotes(ladder_pos);
            retChord.add(ladder[ladder_pos]);
        }

        Chord chorc = new Chord(retChord.get(0), retChord.get(1), retChord.get(2), retChord.get(3), chordType);

        return chorc;
    }
}
