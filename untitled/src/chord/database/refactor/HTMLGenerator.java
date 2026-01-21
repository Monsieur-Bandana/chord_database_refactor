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
        String[] ladder = languageHelper.getLongLadder();
        String pianoSection = "";
        for(Chord chord : chords){
            pianoSection += new HTMLPiano(language, ladder).buildChordPiano(chord);
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

    public String generateText(String htmlString, String key){
        htmlString = htmlString.replace("$"+key+"$", languageHelper.getTranslation(key));
        return htmlString;
    }

    public String translateMultipleKeys(String htmlString, String[] textKeys){

        for (String el : textKeys){
            htmlString = generateText(htmlString, el);
        }
        return htmlString;
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
        htmlString = HTMLDecorator(htmlString);
        htmlString = htmlString.replace("$basetone$", baseTone);
        htmlString = htmlString.replace("$pianoElementsBasetone$", this.generatePianos(mainChords));
        htmlString = htmlString.replace("$pianoElementsRelated$", this.generatePianos(relatedChords));
        htmlString = htmlString.replace("$jumperElementsChord$", this.generateMenu(mainChords));
        htmlString = htmlString.replace("$jumperElementsRelated$", this.generateMenu(relatedChords));

        String[] textKeys = {"chordswith", "asbase", "othercords"};
        htmlString = translateMultipleKeys(htmlString, textKeys);


        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(destPath.getParent());

        // Datei schreiben
        Files.writeString(destPath, htmlString);
    }

    public void generateCompleteList() throws Exception {
        Path completedtemplate = Path.of("server/templates/template-frame-all.html");

        String htmlString = Files.readString(completedtemplate);
        htmlString = htmlString.replace("$jumperElements$", this.generateMenu(allChords));
        htmlString = htmlString.replace("$pianoElements$", this.generatePianos(allChords));
        htmlString = HTMLDecorator(htmlString);

        Path dest = Path.of(server, "completelist","index.html");
        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(dest.getParent());

        // Datei schreiben
        Files.writeString(dest, htmlString);
    }


    public void generateIndexPage() throws Exception {
        String htmlString = TemplateHelper.extractString("template-index");
        String[] ladder = languageHelper.getBasicLadder();


        String htmlPiano = new HTMLPiano(language, ladder).buildPiano();

        htmlString = htmlString.replace("$keyset$", htmlPiano);
        htmlString = HTMLDecorator(htmlString);
        htmlString = htmlString.replace("$server$",  languageHelper.getServer());
        htmlString = TemplateHelper.replaceInts(htmlString, ladder);

        String[] textKeys = {"instruction", "welcome", "title", "todatabase", "target", "explanation", "sheetview", "pianoview"};
        htmlString = translateMultipleKeys(htmlString, textKeys);

        Path dest = Path.of(server, "index.html");
        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(dest.getParent());

        // Datei schreiben
        Files.writeString(dest, htmlString);
    }

    public void generateAboutPage() throws Exception{
        String htmlString = TemplateHelper.extractString("template-about");
        htmlString = HTMLDecorator(htmlString);

        Path dest = Path.of(server, "aboutme","index.html");
        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(dest.getParent());

        // Datei schreiben
        Files.writeString(dest, htmlString);
    }

    public void generateDSGVO() throws Exception{
        String htmlString = TemplateHelper.extractString("template-dsgvo");
        htmlString = HTMLDecorator(htmlString);

        Path dest = Path.of(server, "dsgvo","index.html");
        // Zielordner anlegen (falls nicht vorhanden)
        Files.createDirectories(dest.getParent());

        // Datei schreiben
        Files.writeString(dest, htmlString);
    }

    public String generateFooter(){
        return "<a href=\"/"+server+"dsgvo\">DSGVO</a>";

    }

    public String HTMLDecorator(String htmlString) throws IOException {
        htmlString = htmlString.replace("$header$",  this.generateHeader());
        htmlString = htmlString.replace("$header$",  this.generateHeader());
        htmlString = htmlString.replace("$footer$", this.generateFooter());
        return htmlString;
    }
}
