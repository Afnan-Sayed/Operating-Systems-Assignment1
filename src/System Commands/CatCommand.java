import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CatCommand implements Command 
{
    @Override 
    public void execute(String... args) 
    {
        // case no arguments specified 
        if (args.length == 0) 
        {
            System.out.println("Please specify at least one file.");
            return;
        }

        for (String fileName : args) {
            File file = new File(fileName);

            // case no file not found  
            if (!file.exists()) 
                System.out.println("cat: " + fileName + ": No such file or directory");
            
            else if (!file.isFile())
                System.out.println("cat: " + fileName + ": is a directory, not a file");
            
            else {
                // trying to read the file content
                try (Scanner scanner = new Scanner(file))
                {
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
                } 
                
                catch (FileNotFoundException e) {
                    System.out.println("cat: " + fileName + ": Unable to read the file");
                } 
            }
        }
    }
}