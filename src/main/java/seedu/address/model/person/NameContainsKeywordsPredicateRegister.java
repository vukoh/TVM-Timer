package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code PersonRegister}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicateRegister implements Predicate<PersonRegister> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicateRegister(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(PersonRegister person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicateRegister // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicateRegister) other).keywords)); // state check
    }

}
