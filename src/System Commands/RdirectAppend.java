import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class RdirectAppend implements RedirectionInterface{
    @Override
    public void redirectOutput(String commandOutput, String fileName)
    {
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(file, false)) 
        { 
            writer.write(commandOutput);
        }
        
        catch (IOException e) 
        {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}


