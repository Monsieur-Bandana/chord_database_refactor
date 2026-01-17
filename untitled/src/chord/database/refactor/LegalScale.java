package chord.database.refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegalScale {
    private String[] ladder;
    private String languageSetting;
    public LegalScale(String languageSetting) throws Exception {
        this.languageSetting = languageSetting;

    }

    public List<String> getScale() throws Exception{
        switch (languageSetting){
            case "german":
                ladder = RuleSet.germanNames;
                break;
            default:
                throw new Exception("Unbekannte Sprache: " + languageSetting);
        }



        int[] forbiddenTonesIndex = RuleSet.fakeNotes;
        List<String> someTones = new ArrayList<>(Arrays.stream(ladder).toList());
        for(int i = forbiddenTonesIndex.length - 1; i >= 0; i--){
          //  System.out.println(i+": "+forbiddenTonesIndex[i]+", length: "+someTones.toArray().length);
            int tone = forbiddenTonesIndex[i];

            someTones.remove(tone);



        }

        return someTones;
    }
}
