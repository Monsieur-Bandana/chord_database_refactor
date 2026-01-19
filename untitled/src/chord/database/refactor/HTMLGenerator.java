package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HTMLGenerator {

    private String language;
    private List<Chord> allChords;
    private String server;
    private LanguageHelper languageHelper;

    public HTMLGenerator(List<Chord> chords, String language) {
        this.allChords = chords;
        this.language = language;
        String serverAddOn = "";
        if(!language.equals("german")){
            serverAddOn = "/" +  language;
        }
        this.server = "server" + serverAddOn;
        this.languageHelper = new LanguageHelper(language);
    }

    private String generateHeader() throws IOException {
        Path templateHeader = Path.of("server/templates/template-header.html");
        String htmlString = Files.readString(templateHeader);
        htmlString = htmlString.replace("$language$", "/" +  languageHelper.getServer());
        htmlString = htmlString.replace("$selector$", languageHelper.getTranslation("selector"));
        htmlString = htmlString.replace("$overview$", languageHelper.getTranslation("overview"));
        htmlString = htmlString.replace("$about$", languageHelper.getTranslation("about"));
        return htmlString;
    }

    private String generatePianos(List<Chord> chords) throws Exception {
        String pianoSection = "";
        for(Chord chord : chords){
            pianoSection += new HTMLPiano(chord, language).generatePage();
        }
        return pianoSection;
    }

    private String generateMenu(List<Chord> chords) throws IOException {
        String menuSection = "";
        for(Chord chord : chords){
            menuSection += new HTMLMenu(chord).generateEl();
        }
        return menuSection;
    }

    public void generateSinglePage(String baseTone) throws Exception {
        Path templateFrame = Path.of("server/templates/template-frame.html");
        Path destPath = Path.of(server, baseTone, "index.html");
        SpecificChordList chordlist = new SpecificChordList(baseTone, allChords);
        List<Chord> mainChords = chordlist.getBaseToneList();
        List<Chord> relatedChords = chordlist.getRelatedToneList();
        // Template lesen
        String htmlString = Files.readString(templateFrame);

        // Platzhalter ersetzen
        htmlString = htmlString.replace("$header$", this.generateHeader());
        htmlString = htmlString.replace("$basetone$", baseTone);
        htmlString = htmlString.replace("$pianoElementsBasetone$", this.generatePianos(mainChords));
        htmlString = htmlString.replace("$pianoElementsRelated$", this.generatePianos(relatedChords));
        htmlString = htmlString.replace("$jumperElementsChord$", this.generateMenu(mainChords));
        htmlString = htmlString.replace("$jumperElementsRelated$", this.generateMenu(relatedChords));

        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(destPath.getParent());

        // Datei schreiben
        Files.writeString(destPath, htmlString);
    }

    public void generateCompleteList() throws Exception {
        Path completedtemplate = Path.of("server/templates/template-frame-all.html");
        Path dest = Path.of(server, "completelist","index.html");
        String htmlString = Files.readString(completedtemplate);
        htmlString = htmlString.replace("$jumperElements$", this.generateMenu(allChords));
        htmlString = htmlString.replace("$pianoElements$", this.generatePianos(allChords));
        htmlString = htmlString.replace("$header$", this.generateHeader());

        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(dest.getParent());

        // Datei schreiben
        Files.writeString(dest, htmlString);
    }
}
