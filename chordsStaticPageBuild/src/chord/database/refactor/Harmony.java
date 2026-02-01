package chord.database.refactor;

public class Harmony{
    public int[] chord;
    public String german;
    public String english;
    public String roman;
    public String indonesian;
    public String shortGerman;
    public String shortEnglish;
    public String shortItalian;
    public String shortIndonesian;

    public Harmony(int[] chord, String german, String english, String roman, String indonesian, String shortGerman, String shortEnglish, String shortItalian, String shortIndonesian) {
        this.chord = chord;
        this.german = german;
        this.english = english;
        this.roman = roman;
        this.indonesian = indonesian;
        this.shortGerman = shortGerman;
        this.shortEnglish = shortEnglish;
        this.shortItalian = shortItalian;
        this.shortIndonesian = shortIndonesian;
    }
}
