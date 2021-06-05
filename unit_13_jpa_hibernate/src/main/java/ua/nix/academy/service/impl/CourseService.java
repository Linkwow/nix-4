package ua.nix.academy.service.impl;

import org.hibernate.SessionFactory;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.repository.impl.CourseRepositoryImpl;
import ua.nix.academy.service.Service;

import java.util.List;

public class CourseService implements Service<CourseDto> {
    private static CourseService instance;
    private final CourseRepositoryImpl course;

    private CourseService(SessionFactory sessionFactory){
        course = CourseRepositoryImpl.getInstance(sessionFactory);
    }

    @Override
    public void create(List<CourseDto> dtoList) throws Exception {
        try {
            course.create(dtoList);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static CourseService getInstance(SessionFactory sessionFactory) {
        if(instance == null){
            instance = new CourseService(sessionFactory);
        }
        return instance;
    }
}
