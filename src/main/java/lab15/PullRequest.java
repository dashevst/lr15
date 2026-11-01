package lab15;

public class PullRequest {
    private final int id;
    private final String title;
    private final String sourceBranch;
    private final String targetBranch;
    private final String author;
    private boolean merged;
    private Integer mergeCommitId;

    public PullRequest(int id, String title, String sourceBranch, String targetBranch, String author) {
        this.id = id;
        this.title = title;
        this.sourceBranch = sourceBranch;
        this.targetBranch = targetBranch;
        this.author = author;
        this.merged = false;
        this.mergeCommitId = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceBranch() {
        return sourceBranch;
    }

    public String getTargetBranch() {
        return targetBranch;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isMerged() {
        return merged;
    }

    public Integer getMergeCommitId() {
        return mergeCommitId;
    }

    public void completeMerge(int mergeCommitId) {
        this.merged = true;
        this.mergeCommitId = mergeCommitId;
    }

    @Override
    public String toString() {
        return String.format("PR #%d [%s -> %s] '%s' by %s (Статус: %s)",
                id, sourceBranch, targetBranch, title, author, merged ? "СЛИТ (Коммит #" + mergeCommitId + ")" : "ОТКРЫТ");
    }
}
