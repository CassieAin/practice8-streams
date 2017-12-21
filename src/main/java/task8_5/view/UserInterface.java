package task8_5.view;

import task8_5.model.FolderProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    public void showInterface(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, user!");
        System.out.println("Input the directory name to read from: ");
        String source = scanner.nextLine();
        File file = new File(source);
        if(!file.exists()){
            System.out.println("Wrong input! Please, try again: ");
            file = new File(source);
        }

        System.out.println("Input the file name to write to: ");
        String destination = scanner.nextLine();
        File outFile = new File(destination);
        if(!file.exists()){
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Input the letter to search for: ");
        String letter = scanner.nextLine();
        FolderProcessor folderProcessor = new FolderProcessor(file, outFile, letter);
        new Thread(folderProcessor).start();
        System.out.println("The operation is completed successfully");
    }
}
