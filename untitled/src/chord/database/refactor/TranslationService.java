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

        TRANSLATIONS.put("selector", selector);
        TRANSLATIONS.put("overview", overview);
        TRANSLATIONS.put("about", about);
        TRANSLATIONS.put("instruction", instruction);
    }

    public static String getTranslation(String codewort, String sprache) {
        return TRANSLATIONS
                .getOrDefault(codewort, Map.of())
                .getOrDefault(sprache, "Übersetzung nicht gefunden");
    }
}

