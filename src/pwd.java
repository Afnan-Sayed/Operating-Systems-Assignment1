public class pwd extends  CommandAbstract{
    @Override
    public void run(){
        System.out.println("Current Directory: " + System.getProperty("user.dir"));
    }
}
