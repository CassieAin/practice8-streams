package task8_5.view;

import task8_5.model.FolderProcessor;

import java.io.File;
import java.util.Scanner;

public class UserInterface {

    public void showInterface(){
        System.out.println("Hello, user!");
        System.out.println("Input the directory name to read from: ");
        File file = new File(readLine());
        System.out.println("Input the directory name to write to: ");
        File outFile = new File(readLine());
        System.out.println("Input the letter to search for: ");
        String letter= readLine();
        FolderProcessor folderProcessor = new FolderProcessor(file, outFile, letter);
        new Thread(folderProcessor).start();
    }

    public String readLine(){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }
}
