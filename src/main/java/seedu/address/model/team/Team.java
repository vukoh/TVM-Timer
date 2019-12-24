package seedu.address.model.team;

import seedu.address.model.person.PersonResult;

import java.time.Duration;

/**
 * Class to be used during "Calculate" mode to do team-based calculations.
 */
public class Team {

    private final TeamNumber teamNumber;
    //Size to be changed when confirmed, chosen to defensively prevent too many members somehow being added to a team
    private final PersonResult[] teamMembers = new PersonResult[5];
    private final Duration totalTeamTime = null;

    public Team(TeamNumber teamNumber) {
        this.teamNumber = teamNumber;
        //To be created after implementing UniquePersonResult object (Will iterate through to populate team-members)
        //Do efficiently or doesn't matter?
    }

    public void setTotalTeamTime() {
        
    }
}