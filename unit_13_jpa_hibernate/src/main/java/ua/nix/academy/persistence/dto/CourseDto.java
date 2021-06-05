package ua.nix.academy.persistence.dto;

public class CourseDto extends AbstractDto {
    private final Integer courseNumber;

    public CourseDto(Integer courseNumber){
        this.courseNumber = courseNumber;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }
}
