package TestCommands;
import SystemCommands.*;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TouchTest {

    private touch touchCommand;

    @Before
    void setUp() {
        touchCommand = new touch();
    }

    @After
    void tearDown() {
        // Clean up any test files created
        if (touchCommand.multiplePaths != null) {
            for (String path : touchCommand.multiplePaths) {
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    @Test
    void testCreateNewFile() {
        // Test creating a new file
        touchCommand.multiplePaths = new String[]{"testFile.txt"};
        String result = touchCommand.execute();
        assertEquals("File Created Successfuly", result);

        File file = new File("testFile.txt");
        assertTrue(file.exists(), "File should be created");
    }

    @Test
    void testUpdateExistingFile() {
        // Pre-create the file
        File existingFile = new File("existingFile.txt");
        try {
            existingFile.createNewFile();
            long initialLastModified = existingFile.lastModified();

            // Wait a bit to see a change in the last modified timestamp
            Thread.sleep(1000);

            // execute the touch command
            touchCommand.multiplePaths = new String[]{"existingFile.txt"};
            String result = touchCommand.execute();
            assertEquals("File Timestamp Updated", result);

            // Check if the timestamp was updated
            assertTrue(existingFile.lastModified() > initialLastModified, "File timestamp should be updated");

        } catch (Exception e) {
            fail("Exception thrown during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    void testFileCreationException() {
        // Set an invalid path to trigger an exception
        touchCommand.multiplePaths = new String[]{"invalidPath/illegalFile.txt"};
        String result = touchCommand.execute();
        assertTrue(result.contains("Exception"), "An exception should be caught and returned");
    }
}
