import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CatCommandTest {
    @Test
    void testExecuteWithFile() throws IOException 
    {
        CatCommand catCommand = new CatCommand();
        String fileName = "testfile.txt";
        
        File testFile = new File(fileName);
        try (FileWriter writer = new FileWriter(testFile)) 
        {
            writer.write("Hii\n");
        }
        
        
        String result = catCommand.execute(fileName);
        assertEquals("Hii\n", result);

        testFile.delete();
    }

    @Test
    void testExecuteWithMissingFile() 
    {
        CatCommand catCommand = new CatCommand();
        String fileName = "nonexistentfile.txt";
        
        String result = catCommand.execute(fileName);
        assertEquals("cat: nonexistentfile.txt: No such file or directory", result);
    }
}
