package ua.nix.academy.persistence.dto;

public class LessonDto extends AbstractDto {
    private final String time;
    private final String theme;
    private final String professor;

    public LessonDto(String time, String theme, String professor){
        this.time = time;
        this.theme = theme;
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public String getTheme() {
        return theme;
    }

    public String getProfessor() {
        return professor;
    }
}
