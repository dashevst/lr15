package lab15;

public class LabAssignment {
    private final int number;
    private final String title;
    private final int variant;
    private final String topic;

    public LabAssignment(int number, String title, int variant, String topic) {
        this.number = number;
        this.title = title;
        this.variant = variant;
        this.topic = topic;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public int getVariant() {
        return variant;
    }

    public String getTopic() {
        return topic;
    }

    public String getBranchName() {
        return "lr" + number;
    }

    @Override
    public String toString() {
        return String.format("ЛР №%d [Вариант %d]: %s (%s)", number, variant, title, topic);
    }
}
