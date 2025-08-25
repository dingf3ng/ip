package winnie.task;

import java.time.LocalDateTime;
import winnie.util.DateTimeUtil;
import winnie.exception.InvalidDateTimeException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskEnum.EVENT);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String fromString, String toString) throws InvalidDateTimeException {
        super(description, TaskEnum.EVENT);
        this.from = DateTimeUtil.parseDateTime(fromString);
        this.to = DateTimeUtil.parseDateTime(toString);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (from: " +
                DateTimeUtil.formatForDisplay(from) + " to: " + DateTimeUtil.formatForDisplay(to) + ")";
    }
}
