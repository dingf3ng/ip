package winnie.task;

public enum TaskEnum {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String icon;

    TaskEnum(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return "[" + icon + "]";
    }
}
