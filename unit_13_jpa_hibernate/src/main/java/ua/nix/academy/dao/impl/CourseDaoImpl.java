package ua.nix.academy.dao.impl;

import ua.nix.academy.dao.interfaces.CourseDao;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.persistence.entity.Course;

public class CourseDaoImpl implements CourseDao<Course, CourseDto> {
    private static CourseDaoImpl instance;

    private CourseDaoImpl(){}

    @Override
    public Course create(CourseDto courseDto){
       return new Course(courseDto.getCourseNumber());
    }

     public static CourseDaoImpl getInstance(){
        if(instance == null){
            instance = new CourseDaoImpl();
        }
        return instance;
    }
}
