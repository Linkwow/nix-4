package ua.nix.academy.dao;

import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;

public class CourseDao {
    private static CourseDao instance;

    private CourseDao(){}

    public Course create(CourseDto courseDto){
       return new Course(courseDto.getCourseNumber());
    }

     public static CourseDao getInstance(){
        if(instance == null){
            instance = new CourseDao();
        }
        return instance;
    }
}
