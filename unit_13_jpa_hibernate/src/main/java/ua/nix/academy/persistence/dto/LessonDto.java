package ua.nix.academy.persistence.dto;

public class LessonDto extends AbstractDto {
    private final String time;
    private final String theme;
    private final String group;


    public LessonDto(String time, String theme, String group){
        this.time = time;
        this.theme = theme;
        this.group = group;
    }

    public String getTime() {
        return time;
    }

    public String getTheme() {
        return theme;
    }

    public String getGroup() {
        return group;
    }
}
