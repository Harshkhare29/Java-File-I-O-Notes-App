import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String NOTES_FILE = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Text-Based Notes Manager ===");
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    searchNotes();
                    break;
                case 5:
                    System.out.println("Thank you for using Notes Manager!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add a new note");
        System.out.println("2. View all notes");
        System.out.println("3. Delete a note");
        System.out.println("4. Search notes");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        
        if (note.trim().isEmpty()) {
            System.out.println("Note cannot be empty!");
            return;
        }

        try (FileWriter writer = new FileWriter(NOTES_FILE, true)) {
            writer.write(note + "\n");
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            System.out.println("No notes found!");
            return;
        }

        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            int noteNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(noteNumber + ". " + line);
                    noteNumber++;
                }
            }
            if (noteNumber == 1) {
                System.out.println("No notes found!");
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private static void deleteNote() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            System.out.println("No notes found!");
            return;
        }

        viewNotes();
        System.out.print("Enter the note number to delete: ");
        
        try {
            int noteNumber = Integer.parseInt(scanner.nextLine());
            deleteNoteByNumber(noteNumber);
        } catch (NumberFormatException e) {
            System.out.println("Invalid note number!");
        }
    }

    private static void deleteNoteByNumber(int noteNumber) {
        File inputFile = new File(NOTES_FILE);
        File tempFile = new File("temp_notes.txt");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(tempFile)) {
            
            String line;
            int currentLine = 1;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    if (currentLine != noteNumber) {
                        writer.write(line + "\n");
                    } else {
                        found = true;
                    }
                    currentLine++;
                }
            }
            
            if (found) {
                if (inputFile.delete()) {
                    if (tempFile.renameTo(inputFile)) {
                        System.out.println("Note deleted successfully!");
                    } else {
                        System.out.println("Error renaming file!");
                    }
                } else {
                    System.out.println("Error deleting original file!");
                }
            } else {
                System.out.println("Note number not found!");
                tempFile.delete();
            }
            
        } catch (IOException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
    }

    private static void searchNotes() {
        File file = new File(NOTES_FILE);
        if (!file.exists()) {
            System.out.println("No notes found!");
            return;
        }

        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        if (searchTerm.trim().isEmpty()) {
            System.out.println("Search term cannot be empty!");
            return;
        }

        System.out.println("\n--- Search Results ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            int noteNumber = 1;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty() && line.toLowerCase().contains(searchTerm)) {
                    System.out.println(noteNumber + ". " + line);
                    found = true;
                }
                noteNumber++;
            }
            
            if (!found) {
                System.out.println("No notes found containing: " + searchTerm);
            }
        } catch (IOException e) {
            System.out.println("Error searching notes: " + e.getMessage());
        }
    }
}
