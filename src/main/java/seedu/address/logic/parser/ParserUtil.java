package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.TeamNumber;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String bibNumber} into a {@code BibNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bibNumber} is invalid.
     */
    public static BibNumber parseBibNumber(String bibNumber) throws ParseException {
        requireNonNull(bibNumber);
        String trimmedBibNumber = bibNumber.trim();
        if (!BibNumber.isValidBibNumber(trimmedBibNumber)) {
            throw new ParseException(BibNumber.MESSAGE_CONSTRAINTS);
        }
        return new BibNumber(trimmedBibNumber);
    }

    /**
     * Parses a {@code String index} into a {@code EndTimeListIndex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code index} is invalid.
     */
    public static EndTimeListIndex parseEndTimeListIndex(String index) throws ParseException {
        requireNonNull(index);
        String trimmedIndex = index.trim();
        if (!EndTimeListIndex.isValidIndex(trimmedIndex)) {
            throw new ParseException(EndTimeListIndex.MESSAGE_CONSTRAINTS);
        }
        return new EndTimeListIndex(trimmedIndex);
    }

    /**
     * Parses a {@code String teamNumber} into a {@code TeamNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teamNumber} is invalid.
     */
    public static TeamNumber parseTeamNumber(String teamNumber) throws ParseException {
        requireNonNull(teamNumber);
        String trimmedTeamNumber = teamNumber.trim();
        if (!TeamNumber.isValidTeamNumber(trimmedTeamNumber)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new TeamNumber(trimmedTeamNumber);
    }

    /**
     * Parses a {@code String category} into a {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        switch(category) {

            case "1":
            case "nus men":
                return Category.NUS_MEN;

            case "2":
            case "nus women":
                return Category.NUS_WOMEN;

            case "3":
            case "open men":
                return Category.OPEN_MEN;

            case "4":
            case "open women":
                return Category.OPEN_WOMEN;

            case "5":
            case "team":
                return Category.TEAM;

            default:
                throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code Collection<String> bibNumbers} into an {@code ArrayList<BibNumber>}.
     */
    public static ArrayList<BibNumber> parseBibNumbers(Collection<String> bibNumbers) throws ParseException {
        requireNonNull(bibNumbers);
        final ArrayList<BibNumber> bibNumberList = new ArrayList<>();
        for (String bibNumber : bibNumbers) {
            bibNumberList.add(parseBibNumber(bibNumber));
        }
        return bibNumberList;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
