package chord.database.refactor;
import java.util.HashMap;
import java.util.Map;

public class TranslationService {

    private static final Map<String, Map<String, String>> TRANSLATIONS = new HashMap<>();

    static {
        Map<String, String> selector = new HashMap<>();
        selector.put("german", "Notenauswahl");
        selector.put("english", "Note Selector");
        selector.put("roman", "Selettore di Note");

        Map<String, String> overview = new HashMap<>();
        overview.put("german", "komplette Übersicht");
        overview.put("english", "Complete List");
        overview.put("roman", "Elenco Completo");

        Map<String, String> about = new HashMap<>();
        about.put("german", "Über mich");
        about.put("english", "About");
        about.put("roman", "Su di Me");

        Map<String, String> instruction = new HashMap<>();
        instruction.put("german", "Berühre eine Taste, um alle Dur- und Moll Akkorde zu erhalten, in denen der Ton vorkommt");
        instruction.put("english", "Touch a key to get all major and minor chords in which that note occurs.");
        instruction.put("roman", "Tocca un tasto per ottenere tutti gli accordi maggiori e minori in cui compare quella nota");

        Map<String, String> welcome = new HashMap<>();
        welcome.put("german", "Herzlich Willkommen");
        welcome.put("english", "Welcome");
        welcome.put("roman", "Benvenuto");

        Map<String, String> title = new HashMap<>();
        title.put("german", "Akkord Datenbank");
        title.put("english", "Chord Database");
        title.put("roman", "Database degli Accordi");

        Map<String, String> todatabase = new HashMap<>();
        todatabase.put("german", "zur Akkord Datenbank.");
        todatabase.put("english", "to the chord database.");
        todatabase.put("roman", "alla database degli accordi.");

        Map<String, String> target = new HashMap<>();
        target.put("german", "Diese Seite hat zum Ziel Komponisten, Produzenten oder Schülern zu helfen, die passende musikalische Begleitung für ihre Melodien zu finden.");
        target.put("english", "This page aims to help composers, producers, or students find the appropriate musical accompaniment for their melodies.");
        target.put("roman", "Questa pagina ha lo scopo di aiutare compositori, produttori o studenti a trovare l’accompagnamento musicale adatto alle loro melodie.");

        Map<String, String> explanation = new HashMap<>();
        explanation.put("german", "Anders als andere Akkordübersichten, liefert diese Datenbank alle Akkorde, in denen der ausgewählte Ton vorkommt. So gibt die Akkordübersicht bei Eingabe des Tons \"C\" nicht nur die Akkorde c-moll oder C-Dur aus, sondern auch zum Beispiel a-moll.");
        explanation.put("english", "Unlike other chord overviews, this database provides all chords in which the selected note occurs. For example, when entering the note \"C\", it not only shows C minor or C major, but also chords such as A minor.");
        explanation.put("roman", "A differenza di altre panoramiche di accordi, questa banca dati fornisce tutti gli accordi in cui compare la nota selezionata. Inserendo la nota \"C\", ad esempio, non vengono mostrati solo Do maggiore o Do minore, ma anche accordi come La minore.");

        Map<String, String> othercords = new HashMap<>();
        othercords.put("german", "Andere Akkorde mit");
        othercords.put("english", "Other chords with");
        othercords.put("roman", "Altri accordi con");

        Map<String, String> asbase = new HashMap<>();
        asbase.put("german", "als Grundton");
        asbase.put("english", "as root note");
        asbase.put("roman", "come nota fondamentale");

        Map<String, String> chordswith = new HashMap<>();
        chordswith.put("german", "Akkorde mit");
        chordswith.put("english", "Chords with");
        chordswith.put("roman", "Accordi con");

        Map<String, String> sheetview = new HashMap<>();
        sheetview.put("german", "Notenansicht");
        sheetview.put("english", "Sheet Music View");
        sheetview.put("roman", "Vista dello Spartito");

        Map<String, String> pianoview = new HashMap<>();
        pianoview.put("german", "Klaviaturansicht");
        pianoview.put("english", "Keyboard View");
        pianoview.put("roman", "Vista della Tastiera");

        Map<String, String> parallelharmony = new HashMap<>();
        parallelharmony.put("german", "Paralleltonart");
        parallelharmony.put("english", "Relative Key");
        parallelharmony.put("roman", "Tonalità Relativa");

        TRANSLATIONS.put("parallelharmony", parallelharmony);
        TRANSLATIONS.put("sheetview", sheetview);
        TRANSLATIONS.put("pianoview", pianoview);
        TRANSLATIONS.put("title", title);
        TRANSLATIONS.put("todatabase", todatabase);
        TRANSLATIONS.put("target", target);
        TRANSLATIONS.put("explanation", explanation);
        TRANSLATIONS.put("othercords", othercords);
        TRANSLATIONS.put("asbase", asbase);
        TRANSLATIONS.put("chordswith", chordswith);
        TRANSLATIONS.put("selector", selector);
        TRANSLATIONS.put("overview", overview);
        TRANSLATIONS.put("about", about);
        TRANSLATIONS.put("instruction", instruction);
        TRANSLATIONS.put("welcome", welcome);
    }

    public static String getTranslation(String codewort, String sprache) {
        return TRANSLATIONS
                .getOrDefault(codewort, Map.of())
                .getOrDefault(sprache, "Übersetzung nicht gefunden");
    }
}

