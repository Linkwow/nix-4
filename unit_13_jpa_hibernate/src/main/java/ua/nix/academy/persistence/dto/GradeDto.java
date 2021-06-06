package ua.nix.academy.persistence.dto;

public class GradeDto extends AbstractDto{
    private final String value;

    public GradeDto(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
