package chord.database.refactor;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        /*
        for (int i = 0; i < test.toArray().length; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            Chord chord = test.get(i);
            System.out.println(chord.baseTone + "-" + chord.harmony +" -> "+chord.baseTone+","+chord.scdTone+","+chord.thirdTone+","+chord.highPitchBaseTone);
        }
        */

        String[] langList = new String[]{"german", "roman", "english"};

        for(int y = 0; y < langList.length; y++){
            List<Chord> test = new ChordListCreator(langList[y]).generateChords();

            List<String> scaleTest = new LegalScale(langList[y]).getScale();
            HTMLGenerator htmlGenerator = new HTMLGenerator(test, langList[y]);
            for (int i = 0; i < scaleTest.toArray().length; i++){
                String baseTone = scaleTest.get(i);
                System.out.printf(" " + baseTone +" ");
                htmlGenerator.generateSinglePage(baseTone);
            }
            htmlGenerator.generateIndexPage();
            htmlGenerator.generateCompleteList();
        }


    }
}