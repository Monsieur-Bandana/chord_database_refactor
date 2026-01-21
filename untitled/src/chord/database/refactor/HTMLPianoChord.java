package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLPianoChord {
    private final Path templatePiano;
    private Chord chord;
    private String[] ladder;
    private LanguageHelper languageHelper;
    private String server;

    public HTMLPianoChord(Chord chord, String language, String[] ladder) throws Exception {
        this.chord = chord;
        this.templatePiano = Path.of("server/templates/template-piano.html");
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

    private String buildPiano(String[] localLadder) throws IOException {
        String color = "black";
        String pianoString = "";
        for (int i =0; i < localLadder.length; i++){
            if(i % 3 == 0){
                color = "white";
            }
            pianoString += this.generatKey(localLadder[i], color);
        }

        return pianoString;
    }

    public String generatePage() throws IOException {


        // Template lesen
        String htmlString = Files.readString(templatePiano);

        // Platzhalter ersetzen
        htmlString = htmlString.replace("$chordname$", chord.chordname);
        htmlString = htmlString.replace("$audiofilename$", chord.baseTone.toUpperCase()+"-"+chord.harmonyH.german);
        htmlString = htmlString.replace("$language$", server);

        htmlString = TemplateHelper.replaceInts(htmlString, ladder);


        Pattern COLORING_PATTERN = Pattern.compile("\\$coloring\\(([^:]+):([^)]+)\\)\\$");

        Matcher matcher = COLORING_PATTERN.matcher(htmlString);

        while (matcher.find()) {
            int key = Integer.parseInt(matcher.group(1));

            String value = matcher.group(2);
            String color = value;
            for (int tone : chord.numericTones) {
                if (tone == key) {
                    color = value + " red";
                    break;
                }
            }

            htmlString = htmlString.replace("$coloring("+key+":"+value+")$", color);

        }

        return htmlString;

    }
}
