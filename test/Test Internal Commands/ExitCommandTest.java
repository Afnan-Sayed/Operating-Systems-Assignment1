import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {
    @Test
    void testExecute() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(null, exitCommand.execute()); 
    }
}
