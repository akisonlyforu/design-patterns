package behavioral.memento;

import java.util.*;

/**
 * Memento class - stores state snapshot
 * Immutable object that captures state without revealing implementation details
 */
class Memento {
    private final String text;
    private final int version;
    private final String textToSave;

    // Package-private constructor - only Originator can create mementos
    Memento(String textToSave, int version) {
        this.textToSave = textToSave;
        this.text = textToSave;
        this.version = version;
    }

    // Package-private getter - only Originator can access saved state
    String getSavedText() {
        return textToSave;
    }

    public int getVersion() {
        return version;
    }

    // Public method for displaying memento info (without revealing internal state)
    public String getMementoInfo() {
        return "Memento v" + version + " (Length: " + textToSave.length() + " characters)";
    }

    @Override
    public String toString() {
        return "Memento{version=" + version + ", textLength=" + textToSave.length() + "}";
    }
}

// ============================================
// ORIGINATOR CLASS
// ============================================

/**
 * Originator class - TextArea that can save and restore its state
 * Creates mementos and uses them to restore previous states
 */
class TextArea {
    private String text;
    private int version;

    public TextArea() {
        this.text = "";
        this.version = 0;
    }

    public void setText(String text) {
        this.text = text;
        this.version++;
        System.out.println("[TextArea] Text updated to version " + version + ": \"" +
                (text.length() > 50 ? text.substring(0, 50) + "..." : text) + "\"");
    }

    public String getText() {
        return text;
    }

    public int getVersion() {
        return version;
    }

    /**
     * Creates memento containing current state
     * This is where the snapshot is taken
     */
    public Memento createMemento() {
        System.out.println("[TextArea] Creating memento for version " + version);
        return new Memento(this.text, this.version);
    }

    /**
     * Restores state from memento
     * This is where the state is restored
     */
    public void restore(Memento memento) {
        if (memento != null) {
            this.text = memento.getSavedText();
            this.version = memento.getVersion();
            System.out.println("[TextArea] State restored from " + memento.getMementoInfo());
            System.out.println("[TextArea] Current text: \"" +
                    (text.length() > 50 ? text.substring(0, 50) + "..." : text) + "\"");
        } else {
            System.out.println("[TextArea] Cannot restore - memento is null");
        }
    }

    public void displayCurrentState() {
        System.out.println("[TextArea] Current State - Version: " + version +
                ", Text: \"" + text + "\"");
    }

    // Simulate text editing operations
    public void appendText(String additionalText) {
        this.text += additionalText;
        this.version++;
        System.out.println("[TextArea] Text appended - Version: " + version);
    }

    public void clearText() {
        this.text = "";
        this.version++;
        System.out.println("[TextArea] Text cleared - Version: " + version);
    }

    public void insertText(int position, String insertText) {
        if (position >= 0 && position <= text.length()) {
            this.text = text.substring(0, position) + insertText + text.substring(position);
            this.version++;
            System.out.println("[TextArea] Text inserted at position " + position + " - Version: " + version);
        } else {
            System.out.println("[TextArea] Invalid insertion position: " + position);
        }
    }
}

/**
 * CareTaker class - manages mementos
 * Responsible for storing and retrieving mementos but never examines their contents
 */
class CareTaker {
    private Stack<Memento> mementoHistory;
    private String caretakerName;
    private int maxHistorySize;

    public CareTaker(String caretakerName) {
        this.caretakerName = caretakerName;
        this.mementoHistory = new Stack<>();
        this.maxHistorySize = 10; // Default maximum history size
    }

    public CareTaker(String caretakerName, int maxHistorySize) {
        this.caretakerName = caretakerName;
        this.mementoHistory = new Stack<>();
        this.maxHistorySize = maxHistorySize;
    }

    /**
     * Add memento to history
     */
    public void addMemento(Memento memento) {
        // Maintain maximum history size
        if (mementoHistory.size() >= maxHistorySize) {
            // Remove oldest memento (bottom of stack)
            Memento oldest = mementoHistory.remove(0);
            System.out.println("[" + caretakerName + "] Removed oldest memento: " + oldest.getMementoInfo());
        }

        mementoHistory.push(memento);
        System.out.println("[" + caretakerName + "] Memento saved: " + memento.getMementoInfo());
        System.out.println("[" + caretakerName + "] History size: " + mementoHistory.size());
    }

    /**
     * Get most recent memento (for undo operation)
     */
    public Memento getMemento() {
        if (!mementoHistory.isEmpty()) {
            Memento memento = mementoHistory.pop();
            System.out.println("[" + caretakerName + "] Retrieved memento: " + memento.getMementoInfo());
            System.out.println("[" + caretakerName + "] Remaining history size: " + mementoHistory.size());
            return memento;
        } else {
            System.out.println("[" + caretakerName + "] No mementos available for retrieval");
            return null;
        }
    }

    /**
     * Peek at most recent memento without removing it
     */
    public Memento peekMemento() {
        if (!mementoHistory.isEmpty()) {
            return mementoHistory.peek();
        }
        return null;
    }

    /**
     * Get memento at specific position (0 = most recent)
     */
    public Memento getMementoAt(int position) {
        if (position >= 0 && position < mementoHistory.size()) {
            int index = mementoHistory.size() - 1 - position; // Convert to stack index
            Memento memento = mementoHistory.get(index);
            System.out.println("[" + caretakerName + "] Retrieved memento at position " + position +
                    ": " + memento.getMementoInfo());
            return memento;
        } else {
            System.out.println("[" + caretakerName + "] Invalid position: " + position);
            return null;
        }
    }

    public void showHistory() {
        System.out.println("[" + caretakerName + "] Memento History (" + mementoHistory.size() + " items):");
        if (mementoHistory.isEmpty()) {
            System.out.println("  No mementos in history");
        } else {
            for (int i = mementoHistory.size() - 1; i >= 0; i--) {
                Memento memento = mementoHistory.get(i);
                int position = mementoHistory.size() - 1 - i;
                System.out.println("  " + position + ". " + memento.getMementoInfo());
            }
        }
    }

    public boolean hasHistory() {
        return !mementoHistory.isEmpty();
    }

    public int getHistorySize() {
        return mementoHistory.size();
    }

    public void clearHistory() {
        mementoHistory.clear();
        System.out.println("[" + caretakerName + "] History cleared");
    }
}

/**
 * TextEditor class - demonstrates complete memento pattern usage
 * Provides high-level operations that use the memento pattern
 */
class TextEditor {
    private TextArea textArea;
    private CareTaker undoCareTaker;
    private CareTaker redoCareTaker;

    public TextEditor() {
        this.textArea = new TextArea();
        this.undoCareTaker = new CareTaker("UndoManager", 20);
        this.redoCareTaker = new CareTaker("RedoManager", 20);
    }

    public void writeText(String text) {
        // Save current state before making changes
        saveCurrentState();
        textArea.setText(text);

        // Clear redo history when new action is performed
        redoCareTaker.clearHistory();
    }

    public void appendText(String text) {
        saveCurrentState();
        textArea.appendText(text);
        redoCareTaker.clearHistory();
    }

    public void insertText(int position, String text) {
        saveCurrentState();
        textArea.insertText(position, text);
        redoCareTaker.clearHistory();
    }

    public void clearText() {
        saveCurrentState();
        textArea.clearText();
        redoCareTaker.clearHistory();
    }

    private void saveCurrentState() {
        Memento memento = textArea.createMemento();
        undoCareTaker.addMemento(memento);
    }

    public void undo() {
        System.out.println("\n[TextEditor] Performing UNDO operation");

        if (undoCareTaker.hasHistory()) {
            // Save current state to redo stack
            Memento currentState = textArea.createMemento();
            redoCareTaker.addMemento(currentState);

            // Restore previous state
            Memento previousState = undoCareTaker.getMemento();
            textArea.restore(previousState);
        } else {
            System.out.println("[TextEditor] Nothing to undo");
        }
    }

    public void redo() {
        System.out.println("\n[TextEditor] Performing REDO operation");

        if (redoCareTaker.hasHistory()) {
            // Save current state to undo stack
            Memento currentState = textArea.createMemento();
            undoCareTaker.addMemento(currentState);

            // Restore next state
            Memento nextState = redoCareTaker.getMemento();
            textArea.restore(nextState);
        } else {
            System.out.println("[TextEditor] Nothing to redo");
        }
    }

    public void showCurrentContent() {
        System.out.println("[TextEditor] Current Content:");
        textArea.displayCurrentState();
    }

    public void showHistory() {
        System.out.println("\n[TextEditor] Editor History:");
        undoCareTaker.showHistory();
        redoCareTaker.showHistory();
    }

    public String getCurrentText() {
        return textArea.getText();
    }

    public boolean canUndo() {
        return undoCareTaker.hasHistory();
    }

    public boolean canRedo() {
        return redoCareTaker.hasHistory();
    }
}