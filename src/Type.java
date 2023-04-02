public enum Type {
    WORK("Рабочая"),
    PERSONAL("Личная");
    private final String typeTask;
    Type(String typeTask) {
        this.typeTask = typeTask;
    }

    public String getTypeTask() {
        return typeTask;
    }
}