package ap.hearthstone.utils;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Type;
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

    protected File getFile(String dir, String fileName) {
        new File(dir).mkdir();
        return getFile(dir + fileName);
    }

    protected boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    protected Object load(String filePath, Type fileType) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(getFile(filePath))) {
            return gson.fromJson(reader, fileType);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    protected List<String> getAllFilesInDirectory(String url) {
        try {
            return Files.list(Paths.get(url)).map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
