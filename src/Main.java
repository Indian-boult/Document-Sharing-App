public class Main {
    public static void main(String[] args) {
        // Create users
        User owner = new User(1, "Alice", "alice@example.com");
        User collaborator = new User(2, "Bob", "bob@example.com");

        // Initialize the document controller
        DocumentController controller = new DocumentController();

        // Create a new document
        Document doc = controller.createDocument(101, owner, "Initial Content of the Document");

        // Edit the document by the owner
        controller.editDocument(101, "First Modification by Owner", owner);

        // Share the document with edit permissions to another user
        controller.shareDocument(101, collaborator, AccessLevel.EDIT, owner);

        // Edit by the collaborator
        try {
            controller.editDocument(101, "Second Modification by Collaborator", collaborator);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Print version history
        System.out.println("Version History:");
        controller.viewVersionHistory(101).forEach(System.out::println);

        // Print document events
        System.out.println("Document Events:");
        controller.viewDocumentEvents(101).forEach(System.out::println);
    }
}
