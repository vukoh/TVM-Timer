package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.EndTime;
import seedu.address.model.time.StartTime;

/**
 * Represents a PersonResult object.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonResult {

    private final Name name;
    private final BibNumber bibNumber;
    private final TeamNumber teamNumber;
    private final Category category;
    private final StartTime startTime;
    private final EndTime endTime;

    /**
     * Every field must be present and not null.
     */
    public PersonResult(Name name, BibNumber bibNumber, TeamNumber teamNumber, Category category, StartTime startTime
            , EndTime endTime) {
        requireAllNonNull(name, bibNumber, teamNumber, category);
        this.name = name;
        this.bibNumber = bibNumber;
        this.teamNumber = teamNumber;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Name getName() {
        return name;
    }

    public BibNumber getBibNumber() {
        return bibNumber;
    }

    public TeamNumber getTeamNumber() {
        return teamNumber;
    }

    public Category getCategory() {
        return category;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonResult)) {
            return false;
        }

        PersonResult otherPersonResult = (PersonResult) other;
        return otherPersonResult.getName().equals(getName())
                && otherPersonResult.getBibNumber().equals(getBibNumber())
                && otherPersonResult.getTeamNumber().equals(getTeamNumber())
                && otherPersonResult.getStartTime().equals(getStartTime())
                && otherPersonResult.getEndTime().equals(getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bibNumber, teamNumber, category, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" BibNumber: ")
                .append(getBibNumber())
                .append(" TeamNumber: ")
                .append(getTeamNumber())
                .append(" Category: ")
                .append(getCategory())
                .append(" StartTime: ")
                .append(getStartTime())
                .append(" EndTime: ")
                .append(getEndTime());
        return builder.toString();
    }

}
