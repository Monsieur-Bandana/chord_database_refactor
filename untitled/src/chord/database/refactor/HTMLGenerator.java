package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HTMLGenerator {

    private final String baseTone;
    private final Path templateFrame;
    private final Path destPath;
    private List<Chord> mainChords;
    private List<Chord> relatedChords;

    public HTMLGenerator(String baseTone, List<Chord> chords) {
        SpecificChordList chordlist = new SpecificChordList(baseTone, chords);
        this.baseTone = baseTone;
        this.templateFrame = Path.of("server/templates/template-frame.html");
        this.destPath = Path.of("server", baseTone, "index.html");
        this.mainChords = chordlist.getBaseToneList();
        this.relatedChords = chordlist.getRelatedToneList();
    }

    private String generatePiano(List<Chord> chords) throws IOException {
        String pianoSection = "";
        for(Chord chord : chords){
            pianoSection += new HTMLPiano(chord).generatePage();
        }
        return pianoSection;
    }

    public void generatePage() throws IOException {

        // Template lesen
        String htmlString = Files.readString(templateFrame);

        // Platzhalter ersetzen
        htmlString = htmlString.replace("$basetone$", baseTone);
        htmlString = htmlString.replace("$pianoElementsBasetone$", this.generatePiano(mainChords));
        htmlString = htmlString.replace("$pianoElementsRelated$", this.generatePiano(relatedChords));

        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(destPath.getParent());

        // Datei schreiben
        Files.writeString(destPath, htmlString);
    }
}
