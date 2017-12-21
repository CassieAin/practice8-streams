package task8_5.model;

import task8_5.utils.FileService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FolderProcessor implements Runnable {
    private File source;
    private File destination;
    private String letter;

    public FolderProcessor(File source, File destination, String letter) {
        this.source = source;
        this.destination = destination;
        this.letter = letter;
    }

    @Override
    public void run() {
        File[] files = source.listFiles();
        Map<String, Long> words = new HashMap<>();

        for (File file : files) {
            if (file.isDirectory()) {
                FolderProcessor folderProcessor = new FolderProcessor(file, destination, letter);
                new Thread(folderProcessor).start();
            } else {
                long count = FileService.countLetters(file, letter);
                if(count > 0)
                    words.put(file.getName(), count);
            }
        }
        //write map to file
        if(!words.isEmpty())
            synchronized (destination) {
                FileService.writeToFile(words, destination);
            }
    }
}
