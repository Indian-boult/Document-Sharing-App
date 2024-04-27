public class Version {
    private String content;
    private User editor;
    private long timestamp;

    public Version(String content, User editor, long timestamp) {
        this.content = content;
        this.editor = editor;
        this.timestamp = timestamp;
    }

    // Getters
    public String getContent() { return content; }
    public User getEditor() { return editor; }
    public long getTimestamp() { return timestamp; }
}
