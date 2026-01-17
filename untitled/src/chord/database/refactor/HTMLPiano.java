package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLPiano {
    private final Path templatePiano;
    Chord chord;

    public HTMLPiano(Chord chord) {
        this.chord = chord;
        this.templatePiano = Path.of("server/templates/template-piano.html");
    }

    public String generatePage() throws IOException {

        // Template lesen
        String htmlString = Files.readString(templatePiano);

        // Platzhalter ersetzen
        htmlString = htmlString.replace("$chordname$", chord.chordname);

        Pattern COLORING_PATTERN = Pattern.compile("\\$coloring\\(([^:]+):([^)]+)\\)\\$");

        Matcher matcher = COLORING_PATTERN.matcher(htmlString);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            String color = value;
            if(Arrays.asList(chord.tones).contains(key)){
                color = value + " red";
            }

            htmlString = htmlString.replace("$coloring("+key+":"+value+")$", color);

        }

        return htmlString;

    }
}
