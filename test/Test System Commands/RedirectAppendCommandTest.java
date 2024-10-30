import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

class RedirectAppendTest {
    @Test
    void testRedirectOutputAppendToFile() throws IOException 
    {
        RedirectAppend redirectAppend = new RedirectAppend();
        String fileName = "appendTestFile.txt";
        String commandOutput = "Some appended content";

        
        File testFile = new File(fileName);
        try (FileWriter writer = new FileWriter(testFile)) 
        {
            writer.write("Initial content\n");
        }
        
    
        redirectAppend.redirectOutput(commandOutput, fileName);
        
    
        String content = Files.readString(testFile.toPath());
        assertTrue(content.contains("Initial content"));
        assertTrue(content.contains("Some appended content"));
        
        testFile.delete();
    }
}
