package chord.database.refactor;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class HTMLPiano {



    private String[] ladder;
    private LanguageHelper languageHelper;
    private String server;
    private String language;

    public HTMLPiano(String language, String[] ladder) {
        this.language = language;
        this.languageHelper = new LanguageHelper(language);
        this.ladder = ladder;
        this.server = languageHelper.getServer();
    }

    private String generatKey(String name, String color) throws IOException {
        String htmlString = TemplateHelper.extractString("template-piano-key");
        htmlString = htmlString.replace("$note$", name);
        htmlString = htmlString.replace("$notelink$", server + name);
        htmlString = htmlString.replace("$color$", color);
        return htmlString;
    }

    public String buildPiano() throws IOException {

        String pianoString = "";
        for (int i =0; i < ladder.length; i++){
            String color = "black";
            int finalI = i;
            if(Arrays.stream(RuleSet.fakeNotes).anyMatch(n -> n == finalI)) continue;
            int helper = i;
            if(helper >= 8){
                helper-=8;
            }
            if(helper % 3 == 0){
                color = "white";
            }
            pianoString += this.generatKey(ladder[i], color);
        }

        return pianoString;
    }

    private String addParallelChord(Chord chord) throws IOException {
        if(chord.parallelChord.isEmpty()){
            return "";
        }
        String htmlString = TemplateHelper.extractString("template-parallel");
        htmlString = htmlString.replace("$parallel$", chord.parallelChord);
        htmlString = htmlString.replace("$parallelname$", chord.parallelHarmony);
        htmlString = htmlString.replace("$parallelharmony$", languageHelper.getTranslation("parallelharmony"));
        return htmlString;
    }

    public String buildChordPiano(Chord chord) throws IOException {
        String htmlString = TemplateHelper.extractString("template-piano");
        String pianoString = "";
        for (int i =0; i < ladder.length; i++){
            String color = "black";
            int finalI = i;
            //int[] x = chord.database.refactor.RuleSet.fakeNotes;

            if(Arrays.stream(RuleSet.fakeNotes).anyMatch(n -> n == finalI) || Arrays.stream(RuleSet.fakeNotes).anyMatch(n -> n + 19 == finalI) ){
                continue;
            }
            int helper = i;
            if(helper >= 19){
                helper-=19;
            }
            if(helper >= 8){
                helper-=8;
            }
            if(helper % 3 == 0){
                color = "white";
            }
            if(Arrays.stream(chord.numericTones).anyMatch(n -> n == finalI)){
                color += " red";
            }
            pianoString += this.generatKey(ladder[i], color);
        }
        htmlString = htmlString.replace("$pianokeys$", pianoString);
        htmlString = htmlString.replace("$chordname$", chord.chordname);
        htmlString = htmlString.replace("$chordnameshort$", chord.shortName);
        htmlString = htmlString.replace("$parallelssection$", addParallelChord(chord));
        String subserver = "";
        if(!language.equals("german")){
            subserver = "/" + language;
        }
        htmlString = htmlString.replace("$server$", subserver);
        String audiofilename = Arrays.stream(chord.numericTones)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("_"));;
        htmlString = htmlString.replace("$audiofilename$", audiofilename);
        return htmlString;
    }
}