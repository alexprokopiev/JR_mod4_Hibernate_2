package model;

import static java.util.Objects.isNull;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String label;

    Feature(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Feature getFeatureByValue(String value) {
        if (isNull(value) || value.isEmpty()) return null;
        Feature[] features = Feature.values();
        for (Feature feature : features) {
            if (feature.label.equals(value)) return feature;
        }
        return null;
    }
}
