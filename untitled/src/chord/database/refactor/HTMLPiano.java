package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class HTMLPiano {
    private final Path templatePiano;
    private Chord chord;
    private String[] ladder;
    private LanguageHelper languageHelper;

    public HTMLPiano(Chord chord, String language) throws Exception {
        this.chord = chord;
        this.templatePiano = Path.of("server/templates/template-piano.html");
        this.languageHelper = new LanguageHelper(language);
        this.ladder = languageHelper.getLongLadder();
    }

    public String generatePage() throws IOException {
        String server = languageHelper.getServer();

        // Template lesen
        String htmlString = Files.readString(templatePiano);

        // Platzhalter ersetzen
        htmlString = htmlString.replace("$chordname$", chord.chordname);
        htmlString = htmlString.replace("$audiofilename$", chord.baseTone.toUpperCase()+"-"+chord.harmonyH.german);
        htmlString = htmlString.replace("$language$", server);

        Pattern pattern = Pattern.compile("\\$(\\d+)\\$");
        Matcher matcher = pattern.matcher(htmlString);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            int key = Integer.parseInt(matcher.group(1));
            matcher.appendReplacement(result, ladder[key]);
        }

        matcher.appendTail(result);
        htmlString = result.toString();


        Pattern COLORING_PATTERN = Pattern.compile("\\$coloring\\(([^:]+):([^)]+)\\)\\$");

        matcher = COLORING_PATTERN.matcher(htmlString);

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
