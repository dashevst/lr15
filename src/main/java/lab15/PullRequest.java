package lab15;

public class PullRequest {
    private final int id;
    private final String title;
    private final String sourceBranch;
    private final String targetBranch;
    private final String author;
    private boolean merged;
    private String mergeCommitHash;

    public PullRequest(int id, String title, String sourceBranch, String targetBranch, String author) {
        this.id = id;
        this.title = title;
        this.sourceBranch = sourceBranch;
        this.targetBranch = targetBranch;
        this.author = author;
        this.merged = false;
        this.mergeCommitHash = null;
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

    public String getMergeCommitHash() {
        return mergeCommitHash;
    }

    public void completeMerge(String mergeCommitHash) {
        this.merged = true;
        this.mergeCommitHash = mergeCommitHash;
    }

    @Override
    public String toString() {
        return String.format("PR #%d [%s -> %s] '%s' by %s (Status: %s)",
                id, sourceBranch, targetBranch, title, author, merged ? "MERGED (" + mergeCommitHash + ")" : "OPEN");
    }
}
