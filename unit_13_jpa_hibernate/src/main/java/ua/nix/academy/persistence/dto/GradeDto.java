package ua.nix.academy.persistence.dto;

public class GradeDto extends AbstractDto{
    private final String value;
    private final String theme;

    public GradeDto(String value, String theme){
        this.value = value;
        this.theme = theme;
    }

    public String getValue() {
        return value;
    }

    public String getTheme() {
        return theme;
    }
}
