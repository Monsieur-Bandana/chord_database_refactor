package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HTMLMenu {
    Chord chord;
    private final Path templateFrame;

    public HTMLMenu(Chord chord) {
        this.chord = chord;
        templateFrame = Path.of("server/templates/template-jumpermenu.html");
    }

    public String generateEl() throws IOException {

        String htmlString = Files.readString(templateFrame);

        htmlString = htmlString.replace("$chordname$", chord.chordname);
        return htmlString;
    }
}
