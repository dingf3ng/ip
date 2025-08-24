package task;

import java.time.LocalDateTime;
import util.DateTimeUtil;
import exception.InvalidDateTimeException;

public class Deadline extends Task {
    private LocalDateTime by;
    
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, String byString) throws InvalidDateTimeException {
        super(description, TaskType.DEADLINE);
        this.by = DateTimeUtil.parseDateTime(byString);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }
}