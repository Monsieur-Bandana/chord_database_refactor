package chord.database.refactor;

import java.io.IOException;
import java.util.Arrays;

public class HTMLPiano {



    private String[] ladder;
    private LanguageHelper languageHelper;
    private String server;

    public HTMLPiano(String language, String[] ladder) throws Exception {

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
            if(Arrays.stream(RuleSet.fakeNotes).anyMatch(n -> n == finalI)){
                continue;
            }
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

    public String buildChordPiano(Chord chord) throws IOException {
        String htmlString = TemplateHelper.extractString("template-piano");
        String pianoString = "";
        for (int i =0; i < ladder.length; i++){
            String color = "black";
            int finalI = i;
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
        htmlString = htmlString.replace("$parallel$", chord.parallelChord);
        htmlString = htmlString.replace("$parallelname$", chord.parallelHarmony);
        htmlString = htmlString.replace("$parallelharmony$", languageHelper.getTranslation("parallelharmony"));
        htmlString = htmlString.replace("$audiofilename$", RuleSet.germanNames[chord.numericTones[0]].toUpperCase()+"-"+chord.harmonyH.german);
        return htmlString;
    }
}