package ua.nix.academy.persistence.dto;

public class GroupDto extends AbstractDto {
    private final String name;
    private final Integer course;
    private final String professor;

    public GroupDto(String name, Integer course, String professor){
        this.name = name;
        this.course = course;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public Integer getCourse() {
        return course;
    }

    public String getProfessor() {
        return professor;
    }
}
