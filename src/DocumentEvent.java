public class DocumentEvent {
    private String eventType;
    private User user;
    private long timestamp;

    public DocumentEvent(String eventType, User user, long timestamp) {
        this.eventType = eventType;
        this.user = user;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event: " + eventType + ", User: " + user.getName() + ", Time: " + timestamp;
    }
}
