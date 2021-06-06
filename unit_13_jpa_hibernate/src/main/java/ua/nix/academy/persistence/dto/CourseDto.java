package ua.nix.academy.persistence.dto;

public class CourseDto extends AbstractDto {
    private final String courseNumber;

    public CourseDto(String courseNumber){
        this.courseNumber = courseNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }
}
