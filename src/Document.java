import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    private int documentId;
    private String content;
    private User owner;
    private Map<User, AccessLevel> accessMap;
    private List<Version> versions;
    private List<DocumentEvent> events;

    public Document(int documentId, User owner, String content) {
        this.documentId = documentId;
        this.owner = owner;
        this.content = content;
        this.accessMap = new HashMap<User, AccessLevel>();
        this.versions = new ArrayList<Version>();
        this.events = new ArrayList<DocumentEvent>();
        this.accessMap.put(owner, AccessLevel.EDIT);
        this.versions.add(new Version(content, owner, System.currentTimeMillis()));
    }

    public void editDocument(String newContent, User user) {
        if (this.accessMap.getOrDefault(user, AccessLevel.VIEW) == AccessLevel.EDIT) {
            this.content = newContent;
            this.versions.add(new Version(newContent, user, System.currentTimeMillis()));
            this.events.add(new DocumentEvent("Edited", user, System.currentTimeMillis()));
        } else {
            throw new IllegalArgumentException("User does not have edit permissions");
        }
    }

    public void shareDocument(User user, AccessLevel accessLevel) {
        this.accessMap.put(user, accessLevel);
        this.events.add(new DocumentEvent("Shared", user, System.currentTimeMillis()));
    }

    public List<String> getVersionHistory() {
        List<String> history = new ArrayList<String>();
        for (Version version : versions) {
            history.add("Edited by: " + version.getEditor().getName() + " at " + version.getTimestamp() + " with content: " + version.getContent());
        }
        return history;
    }

    public List<DocumentEvent> getEvents() {
        return events;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String viewDocument(User user) {
        if (this.accessMap.containsKey(user)) {
            return this.content;
        } else {
            throw new IllegalArgumentException("User does not have view permissions");
        }
    }

}
