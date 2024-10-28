import java.io.File;

public class mv extends  CommandAbstract{

    private String src, dest;

    public void setPaths(String source, String destination){
        src = source;
        dest = destination;
    }

    @Override
    public void run(){
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
}
