package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.time.StartTime;

/**
 * Represents a PersonStart object.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonStart {

    private final BibNumber bibNumber;
    private final StartTime startTime = new StartTime();
    private boolean verified = false;

    /**
     * Every field must be present and not null.
     */
    public PersonStart(BibNumber bibNumber) {
        requireAllNonNull(bibNumber);
        this.bibNumber = bibNumber;
    }

    public BibNumber getBibNumber() {
        return bibNumber;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified() {
        this.verified = true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonStart)) {
            return false;
        }

        PersonStart otherPersonStart = (PersonStart) other;
        return otherPersonStart.getBibNumber().equals(getBibNumber())
                && otherPersonStart.getStartTime().equals(getStartTime())
                && otherPersonStart.isVerified() == (isVerified());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(bibNumber, startTime, verified);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getBibNumber())
                .append(" StartTime: ")
                .append(getStartTime())
                .append(" Verified: ")
                .append(isVerified());
        return builder.toString();
    }

}
