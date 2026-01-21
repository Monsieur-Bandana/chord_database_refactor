package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateHelper {

    public static String extractString(String templateAdress) throws IOException {
        Path template = Path.of("server/templates/"+templateAdress+".html");
        return Files.readString(template);
    }

    public static String replaceInts(String htmlString, String[] ladder){
        Pattern pattern = Pattern.compile("\\$(\\d+)\\$");
        Matcher matcher = pattern.matcher(htmlString);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            int key = Integer.parseInt(matcher.group(1));
            matcher.appendReplacement(result, ladder[key]);
        }

        matcher.appendTail(result);
        htmlString = result.toString();
        return htmlString;
    }
}
