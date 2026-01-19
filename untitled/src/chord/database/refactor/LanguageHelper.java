package chord.database.refactor;

import java.util.Arrays;
import java.util.stream.Stream;

public class LanguageHelper {
    private String language;

    public LanguageHelper(String language) {
        this.language = language;
    }

    public String[] getBasicLadder() throws Exception {
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
        return ladderhelper;
    }

    public String[] getLongLadder() throws Exception{
        String[] ladderhelper = getBasicLadder();
        return Stream.concat(
                Arrays.stream(ladderhelper),
                Arrays.stream(ladderhelper)
        ).toArray(String[]::new);

    }

    public String getChordName(Harmony harmony) throws Exception {
        String chordname = "";
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
                throw new Exception("Unbekannte Sproch: " + language);
        }
        return chordname;
    }

    public String getServer(){
        String server = "";
        if(!language.equals("german")){
            server = language + "/";
        }

        return server;
    }

    public String getTranslation(String word){
        return TranslationService.getTranslation(word, language);
    }
}
