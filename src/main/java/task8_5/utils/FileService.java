package task8_5.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileService {

    public static long countLetters(File file, String letter) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = null;
        for (String s : lines) {
             words = s.split(" ");
        }
        return Arrays.stream(words).filter(line -> line.startsWith(letter)).count();
    }

    public static void writeToFile(Map<String, Long> map, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            for(Map.Entry<String, Long> entry : map.entrySet()){
                String resultLine = entry.getKey() + "-" + entry.getValue() + "\n";
                out.write(resultLine.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
