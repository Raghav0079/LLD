import java.util.*;

// Originator class: stores the current state of the resume
class ResumeEditor {
    String name;
    String education;
    String experience;
    List<String> skills;
}

// ResumeSnapshot acts like a memento, but isn't encapsulated properly
class ResumeSnapshot {
    public String name;
    public String education;
    public String experience;
    public List<String> skills;

    // Constructor: captures the current state from ResumeEditor
    public ResumeSnapshot(ResumeEditor editor) {
        this.name = editor.name;
        this.education = editor.education;
        this.experience = editor.experience;
        this.skills = new ArrayList<>(editor.skills); // Deep copy
    }

    // Restore function: applies the stored state back to ResumeEditor
    public void restore(ResumeEditor editor) {
        editor.name = this.name;
        editor.education = this.education;
        editor.experience = this.experience;
        editor.skills = new ArrayList<>(this.skills); // Deep copy
    }
}

// Main driver to demonstrate snapshot creation and restoration
class Main30 {
    public static void main(String[] args) {
        ResumeEditor editor = new ResumeEditor();
        editor.name = "Alice";
        editor.education = "B.Tech in CS";
        editor.experience = "2 years at ABC Corp";
        editor.skills = new ArrayList<>(Arrays.asList("Java", "SQL"));

        // Step 1: Create a snapshot before making changes
        ResumeSnapshot snapshot = new ResumeSnapshot(editor);

        // Step 2: Modify the resume
        editor.name = "Alice Johnson";
        editor.skills.add("Spring Boot");

        System.out.println("After changes:");
        System.out.println("Name: " + editor.name);
        System.out.println("Skills: " + editor.skills);

        // Step 3: Restore previous state using snapshot
        snapshot.restore(editor);

        System.out.println("\nAfter undo:");
        System.out.println("Name: " + editor.name);
        System.out.println("Skills: " + editor.skills);
    }
}