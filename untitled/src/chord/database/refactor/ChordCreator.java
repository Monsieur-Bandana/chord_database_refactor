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
                Arrays.stream(RuleSet.hasheslist),
                Arrays.stream(RuleSet.hasheslist)
        ).toArray(String[]::new);
    }



    public List<String> getChord() throws Exception {
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
            retChord.add(ladder[ladder_pos]);
        }
        return retChord;
    }
}
