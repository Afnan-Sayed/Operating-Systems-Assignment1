public class ExitCommand implements CommandInterface{
    @Override 
    public void execute(String... args) {
        System.out.println("Exiting...");
        System.exit(0);
    }
}