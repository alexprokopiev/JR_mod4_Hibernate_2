package model;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String label;

    Rating(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
