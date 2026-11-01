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
        String headInfo = head != null ? "Коммит #" + head.getId() : "пусто";
        return String.format("Ветка '%s' -> %s", name, headInfo);
    }
}
