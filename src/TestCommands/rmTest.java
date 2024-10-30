package TestCommands;
import SystemCommands.*;
import org.junit.jupiter.api.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class RmTest {

    private rm rmCommand;

    @Before
    void setUp() {
        rmCommand = new rm();
    }

    @Test
    void testDeleteExistingFile() {
        try {
            // Create a test file
            File file = new File("testFile.txt");
            assertTrue(file.createNewFile(), "Test file should be created successfully");

            // Set path for rm command
            rmCommand.multiplePaths = new String[]{"testFile.txt"};
            String result = rmCommand.execute();

            // Verify that the file is deleted
            assertEquals("File Deleted", result);
            assertFalse(file.exists(), "File should be deleted");

        } catch (Exception e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    void testDeleteNonExistentFile() {
        // Set path to a file that doesn't exist
        rmCommand.multiplePaths = new String[]{"nonExistentFile.txt"};
        String result = rmCommand.execute();

        // Expect failure message for non-existent file
        assertEquals("Failed To Delete File", result);
    }

    @Test
    void testDeleteDirectory() {
        try {
            // Create a test directory
            File directory = new File("testDirectory");
            assertTrue(directory.mkdir(), "Test directory should be created");

            // Set path for rm command
            rmCommand.multiplePaths = new String[]{"testDirectory"};
            String result = rmCommand.execute();

            // Verify that deletion fails as it's a directory
            assertEquals("Failed To Delete File", result);
            assertTrue(directory.exists(), "Directory should not be deleted");

        } catch (Exception e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    void testMultipleFilesDeletion() {
        try {
            // Create multiple test files
            File file1 = new File("testFile1.txt");
            File file2 = new File("testFile2.txt");
            assertTrue(file1.createNewFile(), "Test file 1 should be created");
            assertTrue(file2.createNewFile(), "Test file 2 should be created");

            // Set paths for rm command
            rmCommand.multiplePaths = new String[]{"testFile1.txt", "testFile2.txt"};
            String result = rmCommand.execute();

            // Verify that both files are deleted
            assertEquals("File Deleted", result);
            assertFalse(file1.exists(), "Test file 1 should be deleted");
            assertFalse(file2.exists(), "Test file 2 should be deleted");

        } catch (Exception e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }
}
