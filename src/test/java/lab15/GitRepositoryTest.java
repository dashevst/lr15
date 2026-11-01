package lab15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GitRepositoryTest {
    private GitRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new GitRepository("test-repo");
    }

    @Test
    public void testInitialState() {
        assertNotNull(repository.getCurrentBranch());
        assertEquals("main", repository.getCurrentBranch().getName());
        assertEquals(1, repository.getBranches().size());
        assertEquals(0, repository.getCommits().size());
    }

    @Test
    public void testCommit() {
        Commit c = repository.commit("First commit", "author");
        assertNotNull(c);
        assertEquals("First commit", c.getMessage());
        assertEquals(1, repository.getCommits().size());
        assertEquals(c, repository.getCurrentBranch().getHead());
        assertFalse(c.isMergeCommit());
    }

    @Test
    public void testBranchingAndMergeCommit() {
        repository.commit("Initial commit", "author");
        
        repository.createBranch("lr1");
        repository.checkout("lr1");
        assertEquals("lr1", repository.getCurrentBranch().getName());

        Commit branchCommit = repository.commit("Add lr1 code", "author");
        assertEquals(branchCommit, repository.getCurrentBranch().getHead());

        repository.checkout("main");
        assertEquals("main", repository.getCurrentBranch().getName());

        PullRequest pr = repository.createPullRequest("Merge lr1 into main", "lr1", "main", "author");
        assertEquals(1, pr.getId());
        assertFalse(pr.isMerged());

        Commit mergeCommit = repository.mergePullRequest(pr.getId(), "Merge pull request #1 from lr1");
        assertTrue(pr.isMerged());
        assertTrue(mergeCommit.isMergeCommit());
        assertEquals(2, mergeCommit.getParentIds().size());
        assertEquals(mergeCommit, repository.getCurrentBranch().getHead());
    }

    @Test
    public void testDuplicateBranchThrowsException() {
        repository.createBranch("feature");
        assertThrows(IllegalArgumentException.class, () -> repository.createBranch("feature"));
    }

    @Test
    public void testCheckoutNonExistentBranchThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> repository.checkout("unknown"));
    }
}
