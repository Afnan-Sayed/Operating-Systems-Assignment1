package org.example;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RedirectAppend extends RedirectionAbstract implements RedirectionInterface 
{
    @Override
    public void redirectOutput(String commandOutput, String fileName) 
    {
        //case: no argument specified
        if (fileName == null || fileName.trim().isEmpty()) 
        {
            NoFileArgumentCase();
        }
        
        //case: not a file
        else 
        {
            File file = new File(fileName);
            if (!file.isFile())
            {
                NotFileCase(fileName);
            }
            
            else if (commandOutput.equals("cat")) 
            {
                System.out.println("Enter text to be written to the file: " + fileName + " (type 'stop' to finish): ");
                StringBuilder inputText = new StringBuilder();

                //the following will automatically append if the file exists or creates it in case it does not exist
                try (Scanner scanner = new Scanner(System.in))
                {
                    String line;
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("stop")) 
                    {
                        if (inputText.length() > 0) {
                            inputText.append(System.lineSeparator());
                        }
                        inputText.append(line);
                    }
                }

                try (FileWriter writer = new FileWriter(file, true))
                {
                    if (inputText.length() > 0) {
                        writer.write(inputText.toString());
                    }
                    writer.flush();
                }
                catch (IOException e) 
                {
                    System.out.println("Error writing to file: " + fileName + ", " + e.getMessage());
                }
            }
            
        // case: >> file, handle it separately
        if (commandOutput == null || commandOutput.trim().isEmpty()) 
        {
            NoCommandOutputCase(file);
            return;
        } 
            
        
        // default case: command >> file 
        else 
        {
            // check if the file exists, append if y, create and write if n
            try (FileWriter writer = new FileWriter(file, true)) 
            {
                writer.write(commandOutput + System.lineSeparator());
                System.out.println("Output appended to file: " + fileName);
            } 
            
            catch (IOException e) 
            {
                System.out.println("Error writing to file: " + fileName + ", " + e.getMessage());
            }
        }
    }
}
}