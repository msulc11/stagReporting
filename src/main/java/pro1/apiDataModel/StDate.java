package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StDate {
    public String value;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    public boolean isValid() {
        if (value == null || value.isEmpty()) return false;
        try {
            LocalDate.parse(value, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(value, FORMATTER);
    }
}
