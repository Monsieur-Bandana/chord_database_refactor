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

        TRANSLATIONS.put("selector", selector);
        TRANSLATIONS.put("overview", overview);
        TRANSLATIONS.put("about", about);
    }

    public static String getTranslation(String codewort, String sprache) {
        return TRANSLATIONS
                .getOrDefault(codewort, Map.of())
                .getOrDefault(sprache, "Übersetzung nicht gefunden");
    }
}

