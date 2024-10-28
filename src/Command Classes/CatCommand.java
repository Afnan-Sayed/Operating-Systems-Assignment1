import java.io.File;  //for class File for file handling
import java.io.FileNotFoundException;
import java.util.Scanner;  //to read from file line by line or read from keyboard 3ade


public class CatCommand implements Command
{
    @Override 
    public void execute(String... args) 
    {
        //case no arguments specified 
        if (args.length == 0) 
        {
            System.out.println("Please specify at least one file.");
            return;
        }


    
    for (String fileName:args) 
    {
        File file = new File(fileName);

        if (file.exists() && file.isFile()) 
        {
            try (Scanner scanner = new Scanner(file)) 
            {
                while (scanner.hasNextLine()) 
                {
                    System.out.println(scanner.nextLine());
                }
            } 
            
            catch (FileNotFoundException e) 
            {
                System.out.println(fileName + "not found");
            }

        } 
        
        else 
            System.out.println("cat: "+ fileName + ": No such file or directory");
        
    }

    }
}