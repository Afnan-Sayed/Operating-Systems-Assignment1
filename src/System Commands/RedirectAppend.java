import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RedirectAppend implements RedirectionInterface 
{
    @Override
    public void redirectOutput(String commandOutput, String fileName) 
    {
        //case: no argument specified
        if (fileName == null || fileName.trim().isEmpty()) 
        {
            System.out.println("Syntax Error: File name is required");
            return;
        }
        
        else 
        {
            File file = new File(fileName);
            if (!file.isFile())
            {
                System.out.println(fileName+ "is a directory");
                return;
            }
            
            else if (commandOutput.equals("cat")) 
            {
                System.out.println("Enter text to be written to the file: " + fileName + " (type 'stop' to finish): ");
                
                //the following will automatically append if the file exists or creates it in case it does not exist
                try (Scanner scanner = new Scanner(System.in);
                     FileWriter writer = new FileWriter(file, true))
                {
                    String line;
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("stop")) {
                        writer.write(line + System.lineSeparator());
                        writer.flush();
                    }
                }
                
                catch (IOException e) 
                {
                    System.out.println("Error writing to file: " + fileName + ", " + e.getMessage());
                }
            }
            
            // default case: command >> file
            else 
            {
                try (FileWriter writer = new FileWriter(file, true))
                {
                    writer.write(commandOutput);
                    System.out.println("Output appended to file: " + fileName); 
                }
                
                catch (IOException e) 
                {
                    System.out.println("Error writing to file: " + fileName);
                }
            }
        }
    }
}