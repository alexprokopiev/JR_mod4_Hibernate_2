package converter;

import model.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getLabel();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] values = Rating.values();
        for (Rating value : values) {
            if (value.getLabel().equals(dbData)) return value;
        }
        return null;
    }
}
