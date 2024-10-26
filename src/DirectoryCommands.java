import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryCommands {
    public void touch(String pathName){
        try {
            //Create File Object
            File newFile = new File(pathName);

            if(!newFile.createNewFile()){
                //IF file exists, Update its modification
                updateLastModified(newFile);   
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Create a Date Format Object
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void updateLastModified(File file){

        //Create Date Object & updaate last modified date
        Date currentDate = new Date();
        file.setLastModified(currentDate.getTime());
    }

    public void pwd(){
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
    }

    public void mv(String src, String dest){
        //Check if file exists in the same path
        if(src == dest){
            System.out.println("File Already Exists");
            return;
        }
        try {
            //Create File Objects
            File soruce = new File(src);
            File destination = new File(dest);

            //Update file's Modification Date
            updateLastModified(destination);

            //Rename or Move File
            if(!soruce.renameTo(destination)){
                throw new Exception("Can't Move Or Rename File");
            }
                
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void rm(String path){
        //Create File Object
        File file = new File(path);

        try {
            //Delete File
            if(!file.delete()){
                throw new Exception("Failed To Delete File");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
