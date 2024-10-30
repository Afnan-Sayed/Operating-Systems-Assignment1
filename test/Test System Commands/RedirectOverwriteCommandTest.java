import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

class RedirectOverwriteTest 
{
    @Test
    void testRedirectOutputOverwriteFile() throws IOException 
    {
        RedirectOverwrite redirectOverwrite = new RedirectOverwrite();
        String fileName = "overwriteTestFile.txt";
        String commandOutput = "New content";

        File testFile = new File(fileName);
        Files.writeString(testFile.toPath(), "Old content");

        redirectOverwrite.redirectOutput(commandOutput, fileName);

        String content = Files.readString(testFile.toPath());
        assertEquals("New content\n", content);
      
        testFile.delete();
    }
}
