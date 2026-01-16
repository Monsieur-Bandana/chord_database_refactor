package chord.database.refactor;

import java.util.ArrayList;
import java.util.List;

public class ChordListCreator {
    private String[] ladder;
    private List<String> forbiddenTones;
    public ChordListCreator(String languageSetting) throws Exception {

        switch (languageSetting){
            case "german":
                ladder = RuleSet.germanNames;
                break;
            default:
                throw new Exception("Unbekannte Sprache: " + languageSetting);
        }

        forbiddenTones = setForbiddenTones();
    }

    private List<String> setForbiddenTones(){

        List<String> someTones = new ArrayList<>();
        int[] forbiddenTonesIndex = RuleSet.fakeNotes;
        for(int i = 0; i < forbiddenTonesIndex.length; i++){
           String tone = ladder[forbiddenTonesIndex[i]];
            someTones.add(tone);

        }
        return someTones;
    }

    private boolean isValid(Chord chord){
        boolean isValid = true;
        for (String item : forbiddenTones) {
            if(chord.scdTone.equals(item) | chord.thirdTone.equals(item)){
                // continue äußeres Loop
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    public List<Chord> generateChords() throws Exception {
        List<Chord> retList= new ArrayList<>();
        for(int i = 0; i < ladder.length; i++){
            if(forbiddenTones.contains(ladder[i])){
                continue;
            };
            Chord mjaorChord = new ChordCreator(ladder[i], "major").getChord();
            Chord minorChord = new ChordCreator(ladder[i], "minor").getChord();
            if (isValid(mjaorChord)) {
                retList.add(mjaorChord);
            }
            if (isValid(minorChord)) {
                retList.add(minorChord);
            }
        }
        return retList;
    }
}
