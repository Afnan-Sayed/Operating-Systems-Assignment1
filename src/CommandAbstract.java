import java.io.File;
import java.util.Date;

abstract class CommandAbstract{
    public abstract  void run();

    protected String path;

    public void setPath(String pathName){
        path = pathName;
    }

    public static void updateLastModified(File file){

        //Create Date Object & updaate last modified date
        Date currentDate = new Date();
        file.setLastModified(currentDate.getTime());
    }
}
