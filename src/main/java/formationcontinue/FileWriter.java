package formationcontinue;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class FileWriter {

    public static void writeFileIntoString(String data, String filePath, String fileEncoding) throws IOException {
        IOUtils.write(data, new FileOutputStream(filePath), fileEncoding);
    }
}
