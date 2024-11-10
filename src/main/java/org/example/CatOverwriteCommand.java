package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class CatOverwriteCommand implements CommandInterface {
    public String fileName;

    @Override
    public String execute(String... args) {
        fileName = args[0];
        
        String currentDirectory = System.getProperty("user.dir");
        
        File file = new File(currentDirectory, fileName);

        try (FileWriter writer = new FileWriter(file, false)) {

            System.out.println("Enter content to overwrite the file (type 'stop' to finish):");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String content = scanner.nextLine();
                if (content.equalsIgnoreCase("stop")) break;
                writer.write(content + System.lineSeparator());
            }
            System.out.println("File overwritten successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
        return null;
    }
}
