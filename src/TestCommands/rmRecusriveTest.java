package TestCommands;
import SystemCommands.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class RmRecursiveTest {

    private rmRecursive rmRecursiveCommand;

    @Before
    void setUp() {
        rmRecursiveCommand = new rmRecursive();
    }

    @Test
    void testDeleteNonEmptyDirectory() {
        try {
            // Create a test directory with files
            File dir = new File("testDir");
            dir.mkdir();
            File file1 = new File("testDir/file1.txt");
            File file2 = new File("testDir/file2.txt");
            file1.createNewFile();
            file2.createNewFile();

            // Set directory path for rmRecursive command
            rmRecursiveCommand.multiplePaths = new String[]{"testDir"};
            String result = rmRecursiveCommand.execute();

            // Verify directory and its contents are deleted
            assertEquals("Directory Deleted Successfully", result);
            assertFalse(dir.exists(), "Directory should be deleted");
            assertFalse(file1.exists(), "File 1 should be deleted");
            assertFalse(file2.exists(), "File 2 should be deleted");

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    void testDeleteNonExistentDirectory() {
        // Set path to a non-existent directory
        rmRecursiveCommand.multiplePaths = new String[]{"nonExistentDir"};
        String result = rmRecursiveCommand.execute();

        // Expect error message for non-existent directory
        assertEquals("Error Occured while deletion", result);
    }

    @Test
    void testDeleteFileInsteadOfDirectory() {
        try {
            // Create a test file
            File file = new File("testFile.txt");
            file.createNewFile();

            // Set file path instead of directory
            rmRecursiveCommand.multiplePaths = new String[]{"testFile.txt"};
            String result = rmRecursiveCommand.execute();

            // Expect the file to be deleted successfully
            assertEquals("Directory Deleted Successfully", result);
            assertFalse(file.exists(), "File should be deleted");

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    void testDeleteNestedDirectories() {
        try {
            // Create a nested directory structure
            File parentDir = new File("parentDir");
            File childDir = new File("parentDir/childDir");
            parentDir.mkdir();
            childDir.mkdir();
            File fileInChild = new File("parentDir/childDir/file.txt");
            fileInChild.createNewFile();

            // Set path for rmRecursive command
            rmRecursiveCommand.multiplePaths = new String[]{"parentDir"};
            String result = rmRecursiveCommand.execute();

            // Verify parent directory, child directory, and file are deleted
            assertEquals("Directory Deleted Successfully", result);
            assertFalse(parentDir.exists(), "Parent directory should be deleted");
            assertFalse(childDir.exists(), "Child directory should be deleted");
            assertFalse(fileInChild.exists(), "File in child directory should be deleted");

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }
}
