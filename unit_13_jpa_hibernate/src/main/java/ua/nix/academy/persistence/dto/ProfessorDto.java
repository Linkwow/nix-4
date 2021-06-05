package ua.nix.academy.persistence.dto;

public class ProfessorDto extends AbstractDto{
    private final String initials;

    public ProfessorDto(String initials){
        this.initials = initials;
    }

    public String getInitials() {
        return initials;
    }
}
