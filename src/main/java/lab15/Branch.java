package lab15;

public class Branch {
    private final String name;
    private Commit head;

    public Branch(String name, Commit head) {
        this.name = name;
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public Commit getHead() {
        return head;
    }

    public void setHead(Commit head) {
        this.head = head;
    }

    @Override
    public String toString() {
        String headHash = head != null ? head.getHash() : "empty";
        return String.format("Branch '%s' -> %s", name, headHash);
    }
}
