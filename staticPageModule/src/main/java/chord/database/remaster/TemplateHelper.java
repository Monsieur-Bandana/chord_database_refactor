package chord.database.remaster;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateHelper {

    public static String extractString(String templateAddress) throws IOException {
        String res = "/server/templates/" + templateAddress + ".html";

        try (InputStream is = TemplateHelper.class.getResourceAsStream(res)) {
            if (is == null) {
                throw new FileNotFoundException("Template not found: " + res);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
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
