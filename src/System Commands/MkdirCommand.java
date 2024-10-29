import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MkdirCommand implements CommandInterface
{
    private ArrayList<String> directoryPath;
    public void setDirectoryPath(ArrayList<String> path)
    {
        directoryPath = path;
    }

    @Override 
    public String execute(String... args) 
    {
        String currDir = System.getProperty("user.dir");
        if(currDir==null)
        {
            currDir = Paths.get("").toAbsolutePath().toString();
         
        }

        for(String path: directoryPath)
        {
            File directory = new File(path);
            if(!directory.isAbsolute())
            {
                directory = new File(currDir,path);
                
            }

            if(!directory.exists())
            {
                boolean isCreated = directory.mkdirs();
                if(isCreated)continue;
                else
                {
                    return "Error while creating the "+directory+"\n";
                }
            }
            else
            {
                return "Directory already exists \n";
            }
        }
        return "All directories created successfully. \n";
    }
    
}