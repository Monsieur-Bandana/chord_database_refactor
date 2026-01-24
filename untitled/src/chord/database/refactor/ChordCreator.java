package chord.database.refactor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class ChordCreator {
    private String startingTone;
    private Harmony chordType;
    private String[] ladder;
    private String language;
    private LanguageHelper languageHelper;

    public ChordCreator(String startingTone, Harmony chordType, String language) throws Exception {
        this.startingTone = startingTone;
        this.chordType = chordType;
        this.language = language;
        this.languageHelper = new LanguageHelper(language);
        this.ladder = languageHelper.getLongLadder();
    }

    private int replaceFakeNotes(int n){
        for (int i = 0; i < RuleSet.fakeNotes.length; i++){
            int fakeVal = RuleSet.fakeNotes[i];
            if(fakeVal == n || fakeVal + ladder.length -1 == n){
                n++;
            }
        }

        return n;
    }

    private String findParallel(int position) throws Exception {
        String parallel = "";
        if(chordType.equals(RuleSet.majorChord)){
            if(position <= 5){
                position+=19;
            }

            position -= 5;
        }else if(chordType.equals(RuleSet.minorChord)){


            position += 6;
        }else{
            return parallel;
        }
        // gewünschtes format "/cis/#cis-moll"
        parallel = ladder[position];

        int finalPosition = position;
        if(Arrays.stream(RuleSet.fakeNotes).anyMatch(n -> n == finalPosition)){
            System.out.println("Found fake note: " + RuleSet.germanNames[position]);
            parallel = "";
        }

        return parallel;
    }

    public Chord getChord() throws Exception {
        Harmony harmony = chordType;
        String parallelH = "";
        int[] mChords = {};

        if(harmony.equals(RuleSet.majorChord)){
            parallelH = languageHelper.getChordName(RuleSet.minorChord);
        }else if(harmony.equals(RuleSet.minorChord)){
            parallelH = languageHelper.getChordName(RuleSet.majorChord);
        }
        String chordname = languageHelper.getChordName(harmony);
        String parallelTone = "";
        mChords = harmony.chord;
        int additioner = 0;
        for (int i = 0; i < ladder.length; i++) {
            if(ladder[i].equals(startingTone)){
                additioner = i;
                parallelTone = findParallel(i);
                break;
            }



        }
        List<String> retChord = new ArrayList<>();

        int[] intChords = mChords.clone();

        for(int i = 0; i< mChords.length; i++){
            int ladder_pos = mChords[i] + additioner;
        //    ladder_pos = this.replaceFakeNotes(ladder_pos);
            intChords[i] = ladder_pos;
            retChord.add(ladder[ladder_pos]);
        }

        new SoundCreator(intChords).generateSound();

        String shortname = retChord.get(0) + "-" + languageHelper.getChordNameShort(harmony);

        return new Chord(retChord.get(0), retChord.get(1), retChord.get(2), retChord.get(3), chordname, intChords, harmony, parallelTone, parallelH, shortname);
    }
}
