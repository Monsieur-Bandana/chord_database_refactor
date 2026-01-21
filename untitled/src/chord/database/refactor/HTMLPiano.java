package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
}