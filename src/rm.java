import java.io.File;

public class rm extends CommandAbstract {

    @Override
    public void run(){
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
