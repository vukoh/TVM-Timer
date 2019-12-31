package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PersonRegisters;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPersonRegisters;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.team.TeamNumber;

/**
 * Contains utility methods for populating {@code PersonRegisters} with sample data.
 */
public class SampleDataUtil {
    public static PersonRegister[] getSamplePersons() {
        return new PersonRegister[] {
            new PersonRegister(new Name("Alex Yeoh"), new BibNumber("1"), new TeamNumber("1"),
                    Category.NUS_MEN),
            new PersonRegister(new Name("Bhalaji"), new BibNumber("101"), new TeamNumber("1"),
                    Category.OPEN_WOMEN),
            new PersonRegister(new Name("Coco Ular"), new BibNumber("201"), new TeamNumber("2"),
                    Category.NUS_WOMEN),
            new PersonRegister(new Name("David Li"), new BibNumber("301"), new TeamNumber("2"),
                    Category.OPEN_MEN),
            new PersonRegister(new Name("Elliza Bee"), new BibNumber("202"), new TeamNumber("3"),
                    Category.NUS_WOMEN),
            new PersonRegister(new Name("Viral Sabrina"), new BibNumber("203"), new TeamNumber("4"),
                    Category.NUS_WOMEN)
        };
    }

    public static ReadOnlyPersonRegisters getSamplePersonRegisters() {
        PersonRegisters sampleAb = new PersonRegisters();
        for (PersonRegister samplePerson : getSamplePersons()) {
            sampleAb.addPersonRegister(samplePerson);
        }
        return sampleAb;
    }

}
