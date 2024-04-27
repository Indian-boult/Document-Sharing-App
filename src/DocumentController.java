import java.util.ArrayList;
import java.util.List;

public class DocumentController {
    private List<Document> documents;

    public DocumentController() {
        this.documents = new ArrayList<Document>();
    }

    public Document createDocument(int documentId, User owner, String initialContent) {
        Document newDocument = new Document(documentId, owner, initialContent);
        documents.add(newDocument);
        return newDocument;
    }

    public void editDocument(int documentId, String newContent, User editor) {
        for (Document doc : documents) {
            if (doc.getDocumentId() == documentId) {
                doc.editDocument(newContent, editor);
                return;
            }
        }
        throw new IllegalArgumentException("Document not found");
    }

    public String viewDocument(int documentId, User viewer) {
        for (Document doc : documents) {
            if (doc.getDocumentId() == documentId) {
                return doc.viewDocument(viewer);
            }
        }
        throw new IllegalArgumentException("Document not found");
    }

    public void shareDocument(int documentId, User userToShare, AccessLevel accessLevel, User sharer) {
        for (Document doc : documents) {
            if (doc.getDocumentId() == documentId && doc.getOwner() == sharer) {
                doc.shareDocument(userToShare, accessLevel);
                return;
            }
        }
        throw new IllegalArgumentException("Document not found or user is not the owner");
    }

    public List<String> viewVersionHistory(int documentId) {
        for (Document doc : documents) {
            if (doc.getDocumentId() == documentId) {
                return doc.getVersionHistory();
            }
        }
        throw new IllegalArgumentException("Document not found");
    }

    public List<DocumentEvent> viewDocumentEvents(int documentId) {
        for (Document doc : documents) {
            if (doc.getDocumentId() == documentId) {
                return doc.getEvents();
            }
        }
        throw new IllegalArgumentException("Document not found");
    }
}
