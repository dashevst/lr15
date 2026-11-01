package lab15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GitRepository {
    private final String name;
    private final Map<String, Branch> branches;
    private Branch currentBranch;
    private final List<Commit> commits;
    private final List<PullRequest> pullRequests;
    private int commitSequence;
    private int prSequence;

    public GitRepository(String name) {
        this.name = name;
        this.branches = new LinkedHashMap<>();
        this.commits = new ArrayList<>();
        this.pullRequests = new ArrayList<>();
        this.commitSequence = 1;
        this.prSequence = 1;

        Branch mainBranch = new Branch("main", null);
        this.branches.put("main", mainBranch);
        this.currentBranch = mainBranch;
    }

    public String getName() {
        return name;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public Map<String, Branch> getBranches() {
        return Collections.unmodifiableMap(branches);
    }

    public List<Commit> getCommits() {
        return Collections.unmodifiableList(commits);
    }

    public List<PullRequest> getPullRequests() {
        return Collections.unmodifiableList(pullRequests);
    }

    public Branch createBranch(String branchName) {
        if (branches.containsKey(branchName)) {
            throw new IllegalArgumentException("Branch already exists: " + branchName);
        }
        Commit currentHead = currentBranch != null ? currentBranch.getHead() : null;
        Branch newBranch = new Branch(branchName, currentHead);
        branches.put(branchName, newBranch);
        return newBranch;
    }

    public void checkout(String branchName) {
        Branch branch = branches.get(branchName);
        if (branch == null) {
            throw new IllegalArgumentException("Branch not found: " + branchName);
        }
        this.currentBranch = branch;
    }

    public Commit commit(String message, String author) {
        List<Integer> parents = new ArrayList<>();
        if (currentBranch.getHead() != null) {
            parents.add(currentBranch.getHead().getId());
        }

        Commit commit = new Commit(commitSequence++, message, author, parents);
        commits.add(commit);
        currentBranch.setHead(commit);
        return commit;
    }

    public PullRequest createPullRequest(String title, String sourceBranch, String targetBranch, String author) {
        if (!branches.containsKey(sourceBranch)) {
            throw new IllegalArgumentException("Source branch does not exist: " + sourceBranch);
        }
        if (!branches.containsKey(targetBranch)) {
            throw new IllegalArgumentException("Target branch does not exist: " + targetBranch);
        }

        PullRequest pr = new PullRequest(prSequence++, title, sourceBranch, targetBranch, author);
        pullRequests.add(pr);
        return pr;
    }

    public Commit mergePullRequest(int prId, String mergeMessage) {
        PullRequest pr = pullRequests.stream()
                .filter(p -> p.getId() == prId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("PR not found: " + prId));

        if (pr.isMerged()) {
            throw new IllegalStateException("PR already merged: " + prId);
        }

        Branch source = branches.get(pr.getSourceBranch());
        Branch target = branches.get(pr.getTargetBranch());

        if (source.getHead() == null) {
            throw new IllegalStateException("Source branch has no commits");
        }

        List<Integer> parents = new ArrayList<>();
        if (target.getHead() != null) {
            parents.add(target.getHead().getId());
        }
        parents.add(source.getHead().getId());

        int mergeCommitId = commitSequence++;
        Commit mergeCommit = new Commit(mergeCommitId, mergeMessage, pr.getAuthor(), parents);
        commits.add(mergeCommit);

        target.setHead(mergeCommit);
        pr.completeMerge(mergeCommitId);

        return mergeCommit;
    }
}
