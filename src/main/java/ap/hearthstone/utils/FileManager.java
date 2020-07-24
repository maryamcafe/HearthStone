package ap.hearthstone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    protected Logger logger = LogManager.getLogger(this.getClass());

    protected File getFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            Path path = Paths.get(filePath);
            try {
                Files.createFile(path);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return file;
    }

    protected Stream<Path> getAllFilesInDirectory(String url) throws IOException {
        return Files.list(Paths.get(url));

    }
}
