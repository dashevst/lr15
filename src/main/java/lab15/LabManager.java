package lab15;

import java.util.List;

public class LabManager {
    private final GitRepository repository;
    private final int variant;
    private final String author;

    public LabManager(GitRepository repository, int variant, String author) {
        this.repository = repository;
        this.variant = variant;
        this.author = author;
    }

    public GitRepository getRepository() {
        return repository;
    }

    public int getVariant() {
        return variant;
    }

    public String getAuthor() {
        return author;
    }

    public void initializeRepository() {
        repository.checkout("main");
        repository.commit("Initial commit: project structure and .gitignore", author);
    }

    public void transferLab(LabAssignment lab) {
        repository.checkout("main");

        String branchName = lab.getBranchName();
        repository.createBranch(branchName);
        repository.checkout(branchName);

        String commitMessage = String.format("Выполнена ЛР %d [Вариант %d]: %s",
                lab.getNumber(), lab.getVariant(), lab.getTitle());
        repository.commit(commitMessage, author);

        repository.checkout("main");

        String prTitle = String.format("Слияние ЛР %d в main (Вариант %d)",
                lab.getNumber(), lab.getVariant());
        PullRequest pr = repository.createPullRequest(prTitle, branchName, "main", author);

        String mergeMessage = String.format("Merge pull request #%d from %s into main",
                pr.getId(), branchName);
        repository.mergePullRequest(pr.getId(), mergeMessage);
    }

    public void transferAllLabs(List<LabAssignment> labs) {
        initializeRepository();
        for (LabAssignment lab : labs) {
            transferLab(lab);
        }
    }
}
