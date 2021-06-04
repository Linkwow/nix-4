package ua.nix.academy.persistence.dto;

public class CourseDto extends AbstractDto {
    private Integer courseNumber;

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }
}
