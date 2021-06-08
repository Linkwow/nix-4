package ua.nix.academy.persistence.dto;

public class GradeDto extends AbstractDto{
    private final String value;
    private final String student;
    private final String theme;

    public GradeDto(String value, String student, String theme){
        this.value = value;
        this.student = student;
        this.theme = theme;
    }

    public String getValue() {
        return value;
    }

    public String getStudent() {
        return student;
    }

    public String getTheme() {
        return theme;
    }
}
