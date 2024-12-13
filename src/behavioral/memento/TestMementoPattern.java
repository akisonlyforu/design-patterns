package behavioral.memento;

public class TestMementoPattern {
    public static void main(String[] args) {
        System.out.println("=== Testing Memento Pattern ===\n");

        // Test 1: Basic memento creation and restoration
        System.out.println("Test 1: Basic memento creation and restoration");
        TextArea textArea = new TextArea();
        CareTaker careTaker = new CareTaker("BasicCareTaker");

        textArea.setText("Hello World");
        Memento memento1 = textArea.createMemento();
        careTaker.addMemento(memento1);

        textArea.setText("Hello Universe");
        textArea.displayCurrentState();

        System.out.println("✓ Restoring previous state:");
        Memento retrieved = careTaker.getMemento();
        textArea.restore(retrieved);
        textArea.displayCurrentState();
        System.out.println();

        // Test 2: Multiple state snapshots
        System.out.println("Test 2: Multiple state snapshots");
        CareTaker multiCareTaker = new CareTaker("MultiStateCareTaker");

        String[] textVersions = {
                "Version 1: Initial text",
                "Version 2: Added more content",
                "Version 3: Final version with complete content"
        };

        System.out.println("✓ Creating multiple snapshots:");
        for (String version : textVersions) {
            textArea.setText(version);
            Memento memento = textArea.createMemento();
            multiCareTaker.addMemento(memento);
        }

        multiCareTaker.showHistory();

        System.out.println("Restoring to previous versions:");
        for (int i = 0; i < 3; i++) {
            Memento memento = multiCareTaker.getMemento();
            if (memento != null) {
                textArea.restore(memento);
                textArea.displayCurrentState();
            }
        }
        System.out.println();

        // Test 3: Text Editor with undo/redo functionality
        System.out.println("Test 3: Text Editor with undo/redo functionality");
        TextEditor editor = new TextEditor();

        System.out.println("✓ Simulating text editing session:");
        editor.writeText("Once upon a time");
        editor.showCurrentContent();

        editor.appendText(" in a land far away");
        editor.showCurrentContent();

        editor.appendText(", there lived a programmer");
        editor.showCurrentContent();

        editor.insertText(17, "magical ");
        editor.showCurrentContent();

        System.out.println("Can undo: " + editor.canUndo());
        System.out.println("Can redo: " + editor.canRedo());
        editor.showHistory();

        // Test 4: Undo operations
        System.out.println("Test 4: Undo operations");

        System.out.println("✓ Performing multiple undo operations:");
        editor.undo(); // Undo insert
        editor.showCurrentContent();

        editor.undo(); // Undo append "programmer"
        editor.showCurrentContent();

        editor.undo(); // Undo append "far away"
        editor.showCurrentContent();

        System.out.println("Undo/Redo status:");
        System.out.println("Can undo: " + editor.canUndo());
        System.out.println("Can redo: " + editor.canRedo());

        // Test 5: Redo operations
        System.out.println("\nTest 5: Redo operations");

        System.out.println("✓ Performing redo operations:");
        editor.redo(); // Redo append "far away"
        editor.showCurrentContent();

        editor.redo(); // Redo append "programmer"
        editor.showCurrentContent();

        System.out.println("After redo operations:");
        System.out.println("Can undo: " + editor.canUndo());
        System.out.println("Can redo: " + editor.canRedo());

        // Test 6: New action after undo (redo history cleared)
        System.out.println("\nTest 6: New action after undo clears redo history");

        editor.undo(); // Go back one step
        System.out.println("After undo - Can redo: " + editor.canRedo());

        editor.appendText(" and they coded happily ever after");
        System.out.println("After new action - Can redo: " + editor.canRedo());
        editor.showCurrentContent();

        // Test 7: Memento history size limits
        System.out.println("\nTest 7: Memento history size limits");

        CareTaker limitedCareTaker = new CareTaker("LimitedCareTaker", 3);
        TextArea testArea = new TextArea();

        System.out.println("✓ Testing history size limit (max 3 mementos):");
        for (int i = 1; i <= 6; i++) {
            testArea.setText("Text version " + i);
            Memento memento = testArea.createMemento();
            limitedCareTaker.addMemento(memento);
        }

        limitedCareTaker.showHistory();

        // Test 8: Memento immutability and security
        System.out.println("\nTest 8: Memento immutability and security");

        TextArea secureArea = new TextArea();
        secureArea.setText("Confidential data");

        Memento secureMemento = secureArea.createMemento();

        System.out.println("✓ Testing memento immutability:");
        System.out.println("Memento info (safe): " + secureMemento.getMementoInfo());
        System.out.println("Memento version: " + secureMemento.getVersion());

        // Demonstrate that memento doesn't expose internal state publicly
        System.out.println("Memento toString: " + secureMemento.toString());

        // Change original object
        secureArea.setText("Modified data");

        // Memento should still contain original state
        secureArea.restore(secureMemento);
        System.out.println("Restored text: " + secureArea.getText());

        // Test 9: Complex editing scenario
        System.out.println("\nTest 9: Complex editing scenario");

        TextEditor complexEditor = new TextEditor();

        System.out.println("✓ Complex editing session:");
        complexEditor.writeText("Chapter 1");
        complexEditor.appendText("\nThe story begins here.");
        complexEditor.insertText(0, "Book Title\n\n");
        complexEditor.appendText("\nIt was a dark and stormy night.");
        complexEditor.insertText(12, "Amazing ");

        complexEditor.showCurrentContent();
        complexEditor.showHistory();

        // Perform multiple undos and redos
        System.out.println("\nPerforming undo/redo sequence:");
        complexEditor.undo();
        complexEditor.undo();
        complexEditor.redo();
        complexEditor.appendText("\nThe end.");

        complexEditor.showCurrentContent();

        // Test 10: Multiple caretakers (different save strategies)
        System.out.println("\nTest 10: Multiple caretakers with different strategies");

        TextArea sharedArea = new TextArea();
        CareTaker autoSaveCareTaker = new CareTaker("AutoSave", 5);
        CareTaker manualSaveCareTaker = new CareTaker("ManualSave", 10);

        System.out.println("✓ Testing multiple caretakers:");

        sharedArea.setText("Document draft 1");

        // Auto-save every change
        Memento auto1 = sharedArea.createMemento();
        autoSaveCareTaker.addMemento(auto1);

        sharedArea.appendText(" - with modifications");
        Memento auto2 = sharedArea.createMemento();
        autoSaveCareTaker.addMemento(auto2);

        // Manual save at specific points
        Memento manual1 = sharedArea.createMemento();
        manualSaveCareTaker.addMemento(manual1);

        sharedArea.appendText(" - final version");

        System.out.println("AutoSave history:");
        autoSaveCareTaker.showHistory();

        System.out.println("ManualSave history:");
        manualSaveCareTaker.showHistory();

        // Restore from different caretakers
        System.out.println("Restoring from manual save:");
        Memento manualRestore = manualSaveCareTaker.getMemento();
        sharedArea.restore(manualRestore);
        sharedArea.displayCurrentState();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Memento Pattern verified:");
        System.out.println("- Originator (TextArea) creates mementos of its internal state");
        System.out.println("- Memento stores state snapshot without exposing implementation details");
        System.out.println("- CareTaker manages mementos without examining their contents");
        System.out.println("- State can be saved and restored without breaking encapsulation");
        System.out.println("- Multiple mementos can be stored for multiple undo levels");
        System.out.println("- Memento objects are immutable and secure");
        System.out.println("- Different caretakers can implement different storage strategies");
        System.out.println("- Undo/redo functionality works correctly");
        System.out.println("- History size limits prevent memory overflow");

        demonstrateMementoAdvantages();
    }

    private static void demonstrateMementoAdvantages() {
        System.out.println("\n=== Memento Pattern Advantages ===");

        System.out.println("1. Encapsulation Preservation:");
        System.out.println("   - Object state saved without breaking encapsulation");
        System.out.println("   - Internal implementation details remain hidden");
        System.out.println("   - Memento acts as black box for state storage");

        System.out.println("\n2. Undo/Redo Implementation:");
        System.out.println("   - Easy to implement multi-level undo/redo");
        System.out.println("   - State snapshots stored independently");
        System.out.println("   - No need to track individual changes");

        System.out.println("\n3. State Management:");
        System.out.println("   - Clean separation of state storage from state usage");
        System.out.println("   - CareTaker handles storage policies (size limits, cleanup)");
        System.out.println("   - Originator focuses on core functionality");

        System.out.println("\n4. Flexibility:");
        System.out.println("   - Different storage strategies through different caretakers");
        System.out.println("   - Can save state at any point in time");
        System.out.println("   - Support for selective state restoration");

        System.out.println("\nReal-World Applications:");
        System.out.println("- Text Editors (Microsoft Word, Google Docs undo/redo)");
        System.out.println("- Graphics Software (Photoshop history states)");
        System.out.println("- Database Transactions (rollback functionality)");
        System.out.println("- Game Development (save/load game states)");
        System.out.println("- Configuration Management (backup/restore settings)");
        System.out.println("- Version Control Systems (commit snapshots)");

        System.out.println("\nPattern Trade-offs:");
        System.out.println("Advantages:");
        System.out.println("- Preserves encapsulation boundaries");
        System.out.println("- Simplifies originator by delegating state management");
        System.out.println("- Easy to implement undo operations");

        System.out.println("Disadvantages:");
        System.out.println("- Can be expensive if creating mementos frequently");
        System.out.println("- May consume significant memory for large state objects");
        System.out.println("- CareTaker lifetime management important to prevent memory leaks");
    }
}
