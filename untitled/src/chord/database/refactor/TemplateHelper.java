package chord.database.refactor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemplateHelper {

    public static String extractString(String templateAdress) throws IOException {
        Path template = Path.of("server/templates/"+templateAdress+".html");
        return Files.readString(template);
    }
}
