package ua.nix.academy.persistence.dto;

public class GradeDto extends AbstractDto{
    private final String value;
    private final String theme;
    private final String student;

    public GradeDto(String value, String theme, String student){
        this.value = value;
        this.theme = theme;
        this.student = student;
    }

    public String getValue() {
        return value;
    }

    public String getTheme() {
        return theme;
    }

    public String getStudent() {
        return student;
    }
}
