package ua.nix.academy.persistence.dto;

public class LessonDto extends AbstractDto {
    private final String time;
    private final String theme;

    public LessonDto(String time, String theme){
        this.time = time;
        this.theme = theme;
    }

    public String getTime() {
        return time;
    }

    public String getTheme() {
        return theme;
    }
}
