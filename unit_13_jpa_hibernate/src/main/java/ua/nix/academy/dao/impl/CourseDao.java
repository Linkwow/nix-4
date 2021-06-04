package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.CommonDao;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;

public class CourseDao implements CommonDao<Course, CourseDto> {
    private static CourseDao instance;

    private CourseDao(){}

    @Override
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
