package chord.database.refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecificChordList {
    String baseTone;
    List<Chord> allChords;

    public SpecificChordList(String baseTone, List<Chord> allChords) {
        this.baseTone = baseTone;
        this.allChords = allChords;
    }

    public List<Chord> getBaseToneList(){
        List<Chord> retChord = new ArrayList<>();
        for (Chord chord : allChords){
            if(chord.baseTone.equals(baseTone)){
                retChord.add(chord);
            }
        }
        return retChord;
    }

    public List<Chord> getRelatedToneList(){
        List<Chord> retChord = new ArrayList<>();
        for (Chord chord : allChords){
            if(Arrays.asList(chord.tones).contains(baseTone)  && !Arrays.asList(chord.tones).get(0).equals(baseTone)){
                retChord.add(chord);
            }
        }
        return retChord;
    }
}
