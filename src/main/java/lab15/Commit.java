package lab15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commit {
    private final int id;
    private final String message;
    private final String author;
    private final LocalDateTime timestamp;
    private final List<Integer> parentIds;

    public Commit(int id, String message, String author, List<Integer> parentIds) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.parentIds = parentIds != null ? new ArrayList<>(parentIds) : new ArrayList<>();
    }

    public int getId() {
        return id;
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

    public List<Integer> getParentIds() {
        return Collections.unmodifiableList(parentIds);
    }

    public boolean isMergeCommit() {
        return parentIds.size() > 1;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String parents = parentIds.isEmpty() ? "нет" : parentIds.toString();
        return String.format("Коммит #%d (Родители: %s) %s - %s <%s>", 
                id, parents, message, timestamp.format(formatter), author);
    }
}
