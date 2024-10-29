import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



public class RmdirCommand implements CommandInterface{
    private String dir;

    public void setDir(String path)
    {
        dir = path;
    }

    @Override 
    public String execute(String... args) 
    {
        try 
        {
            removeDir(dir);
            return "Directory removed!" ;
        } 
        catch (IOException e) 
        {
            return e.getMessage()+"\n";
        }
    }

    private void removeDir(String dir) throws  IOException
    {
        Path path = Paths.get(dir);

        if(!Files.exists(path))
        {
            System.out.println("Error directory does not exits");
            return;
        }

        if(!Files.isDirectory(path)){
            System.out.println("The specified path is not a directory");
            return;
        }

        if(Files.list(path).findAny().isPresent()){
            System.out.println("Directory is not empty");
            return;
        }
        Files.delete(path);
    }
}


