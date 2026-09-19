import java.util.ArrayList;
import java.util.List;

// Class representing a User in a collaborative document editor.
class User {
    private String name;
    private List<User> others;  // List of users that have access to this user

    // Constructor for creating a User with a name.
    public User(String name) {
        this.name = name;
        this.others = new ArrayList<>();
    }

    // Method to add a collaborator to this user (grants access to the user).
    public void addCollaborator(User user) {
        others.add(user);
    }

    // Method to make a change to the document and notify all collaborators.
    // Each collaborator will receive the change notification.
    public void makeChange(String change) {
        System.out.println(name + " made a change: " + change);
        for (User u : others) {
            u.receiveChange(change, this);  // Notify each collaborator about the change.
        }
    }

    // Method to receive a change notification from another user.
    public void receiveChange(String change, User from) {
        System.out.println(name + " received: \"" + change + "\" from " + from.name);
    }
}


// Client Code
class Main29 {
    public static void main(String[] args) {
        // Creating users
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // Adding collaborators (Alice gives access to Bob and Charlie)
        alice.addCollaborator(bob);
        alice.addCollaborator(charlie);

        // Alice makes a change, notifying Bob and Charlie
        alice.makeChange("Updated the document title");

        // Bob makes a change, but no collaborators are notified because Bob has no collaborators added
        bob.makeChange("Added a new section to the document");
    }
}
