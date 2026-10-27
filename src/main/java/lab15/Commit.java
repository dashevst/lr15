package lab15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commit {
    private final String hash;
    private final String message;
    private final String author;
    private final LocalDateTime timestamp;
    private final List<String> parentHashes;

    public Commit(String hash, String message, String author, List<String> parentHashes) {
        this.hash = hash;
        this.message = message;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.parentHashes = parentHashes != null ? new ArrayList<>(parentHashes) : new ArrayList<>();
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getParentHashes() {
        return Collections.unmodifiableList(parentHashes);
    }

    public boolean isMergeCommit() {
        return parentHashes.size() > 1;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String parents = parentHashes.isEmpty() ? "none" : String.join(", ", parentHashes);
        return String.format("[%s] (Parents: %s) %s - %s <%s>", 
                hash, parents, message, timestamp.format(formatter), author);
    }
}
