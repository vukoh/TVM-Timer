package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.StartTime;

/**
 * Represents a PersonRegister object.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonRegister {

    private final Name name;
    private final BibNumber bibNumber;
    private final TeamNumber teamNumber;
    private final Category category;

    /**
     * Every field must be present and not null.
     */
    public PersonRegister(Name name, BibNumber bibNumber, TeamNumber teamNumber, Category category) {
        requireAllNonNull(name, bibNumber, teamNumber, category);
        this.name = name;
        this.bibNumber = bibNumber;
        this.teamNumber = teamNumber;
        this.category = category;
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonRegister)) {
            return false;
        }

        PersonRegister otherPersonRegister = (PersonRegister) other;
        return otherPersonRegister.getName().equals(getName())
                && otherPersonRegister.getBibNumber().equals(getBibNumber())
                && otherPersonRegister.getTeamNumber().equals(getTeamNumber())
                && otherPersonRegister.getCategory().equals(getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bibNumber, teamNumber, category);
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
                .append(getCategory());
        return builder.toString();
    }

}
