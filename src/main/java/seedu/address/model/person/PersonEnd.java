package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Instant;
import java.util.Objects;

import seedu.address.model.time.StartTime;

/**
 * Represents a PersonEnd object.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonEnd {

    private final BibNumber bibNumber;
    private final EndTimeListIndex index;

    /**
     * Every field must be present and not null.
     */
    public PersonEnd(BibNumber bibNumber, EndTimeListIndex index) {
        requireAllNonNull(bibNumber);
        this.bibNumber = bibNumber;
        this.index = index;
    }

    public BibNumber getBibNumber() {
        return bibNumber;
    }

    public EndTimeListIndex getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonEnd)) {
            return false;
        }

        PersonEnd otherPersonEnd = (PersonEnd) other;
        return otherPersonEnd.getBibNumber().equals(getBibNumber())
                && otherPersonEnd.getIndex().equals(getIndex());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(bibNumber, index);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getBibNumber())
                .append(" Position: ")
                .append(getIndex());
        return builder.toString();
    }

}
