package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {
    public List<String> getSearchTexts(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
