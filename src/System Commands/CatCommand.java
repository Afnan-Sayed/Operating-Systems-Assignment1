import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CatCommand implements CommandInterface 
{
    @Override 
    public String execute(String... args)
    {
        // case no arguments specified 
        if (args.length == 0) 
        {
            return "Please specify at least one file. \n";
        }

        for (String fileName : args) 
        {
            File file = new File(fileName);

            // case no file not found  
            if (!file.exists()) 
                return "cat: " + fileName + ": No such file or directory";
            
            else if (!file.isFile())
                return "cat: " + fileName + ": is a directory, not a file";
            
            else 
            {
                // trying to read the file content
                try (Scanner scanner = new Scanner(file))
                {
                    String str=null;
                    while (scanner.hasNextLine()) 
                    {
                        str+= scanner.nextLine()+"\n";
                    }
                    return str;
                } 
                
                catch (FileNotFoundException e) 
                {
                    return "cat: " + fileName + ": Unable to read the file";
                } 
            }
        }
        return null;
    }
}