import java.io.File;
import java.nio.file.Paths;

public class CdCommand implements CommandInterface
{
    private String path;
    public  void setPath(String path)
    {
        this.path = path.trim();
    }

    @Override 
    public String execute(String... args) 
    {
        String currentDir = System.getProperty("user.dir");
        File targetDir;

        if(path.equals(".."))
        {
            targetDir = new File(currentDir).getParentFile();
            if(targetDir==null)
            {
                return "You are in the root directory";
            }
        }
        else
        {
            targetDir = new File(path);
            if(!targetDir.isAbsolute()){
                targetDir = Paths.get(currentDir,path).toFile();
            }
        }

        if(targetDir.exists() && targetDir.isDirectory())
        {
            System.setProperty("user.dir", targetDir.getAbsolutePath());
            return "Changed directory to: " + targetDir.getAbsolutePath();
        }

        else
        {
            return "Directory does not exist: " + targetDir.getAbsolutePath();
        }
    }
}

