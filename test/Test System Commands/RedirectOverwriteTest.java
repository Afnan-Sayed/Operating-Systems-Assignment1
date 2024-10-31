import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectOverwriteTest 
{

    private RedirectOverwrite redirectOverwrite;

    @BeforeEach
    public void setUp() 
    {
        redirectOverwrite = new RedirectOverwrite();
    }

    @Test
    public void testOverwriteOutputToFile() throws IOException 
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

        redirectOverwrite.redirectOutput("Hello World", tempFile.getName());

        String content = Files.readString(tempFile.toPath());
        assertEquals("Hello World", content);
    }

    @Test
    public void testOverwriteWithCatCommand() throws IOException 
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

    
        try (FileWriter writer = new FileWriter(tempFile))
        {
            writer.write("Initial Content\n");
        }

        redirectOverwrite.redirectOutput("cat", tempFile.getName());

        String content = Files.readString(tempFile.toPath());
        assertFalse(content.contains("Initial Content"));
    }

    @Test
    public void testNoFileArgument() 
    {
        assertDoesNotThrow(() -> redirectOverwrite.redirectOutput("Hello", null));
    }

    @Test
    public void testDirectoryAsFile() throws IOException 
    {
        File tempDir = Files.createTempDirectory("testDir").toFile();
        tempDir.deleteOnExit();

        assertDoesNotThrow(() -> redirectOverwrite.redirectOutput("Hello", tempDir.getPath()));
    }

    @Test
    public void testNoCommandOutput() throws IOException 
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

        redirectOverwrite.redirectOutput("", tempFile.getName());
        String content = Files.readString(tempFile.toPath());
        assertTrue(content.isEmpty());
    }
}
