package chord.database.refactor;

import java.util.ArrayList;
import java.util.List;

public class ChordListCreator {
    private String[] ladder;
    private List<String> forbiddenTones;
    private String languageSetting;
    public ChordListCreator(String languageSetting) throws Exception {
        this.languageSetting = languageSetting;
        this.ladder = new LanguageHelper(languageSetting).getBasicLadder();

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

            for (Harmony h : RuleSet.allHarmonies){
                Chord chord = new ChordCreator(ladder[i], h, languageSetting).getChord();
                if (isValid(chord)) {
                    retList.add(chord);
                }
            }

        }
        return retList;
    }
}
