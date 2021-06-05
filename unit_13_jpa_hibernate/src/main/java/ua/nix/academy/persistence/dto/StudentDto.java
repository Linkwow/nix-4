package ua.nix.academy.persistence.dto;

public class StudentDto extends AbstractDto {
    private final String initials;
    private final String groupName;

    public StudentDto(String initials, String groupName){
        this.initials = initials;
        this.groupName = groupName;
    }

    public String getInitials() {
        return initials;
    }

    public String getGroupName() {
        return groupName;
    }

}
