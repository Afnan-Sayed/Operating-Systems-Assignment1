import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectAppendTest 
{

    private RedirectAppend redirectAppend;

    @BeforeEach
    public void setUp() 
    {
        redirectAppend = new RedirectAppend();
    }

    @Test
    public void testAppendOutputToFile() throws IOException 
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
    
        redirectAppend.redirectOutput("Hello World", tempFile.getName());
        
        String content = Files.readString(tempFile.toPath());
        assertEquals("Hello World\n", content);
    }

    @Test
    public void testAppendWithCatCommand() throws IOException
    {
       
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile, true)) 
        {
            writer.write("Hello from cat\n");
        }
        
   
        redirectAppend.redirectOutput("cat", tempFile.getName());

        String content = Files.readString(tempFile.toPath());
        assertTrue(content.contains("Hello from cat"));
    }

    @Test
    public void testNoFileArgument() 
    {
        assertDoesNotThrow(() -> redirectAppend.redirectOutput("Hello", null));
    }

    @Test
    public void testDirectoryAsFile() throws IOException 
    {
        File tempDir = Files.createTempDirectory("testDir").toFile();
        tempDir.deleteOnExit();

        assertDoesNotThrow(() -> redirectAppend.redirectOutput("Hello", tempDir.getPath()));
    }

    @Test
    public void testNoCommandOutput() throws IOException 
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

        redirectAppend.redirectOutput("", tempFile.getName());
        String content = Files.readString(tempFile.toPath());
        assertTrue(content.isEmpty());
    }
}
