package ua.nix.academy.service.impl;

import ua.nix.academy.excepton.MyCustomSQLException;
import ua.nix.academy.persistence.dto.CourseDto;
import ua.nix.academy.repository.impl.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private CourseRepository course = CourseRepository.getInstance();

    public void create(Integer[] courseNumbers) throws MyCustomSQLException {
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Integer courseNumber : courseNumbers) {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseNumber(courseNumber);
            courseDtos.add(courseDto);
        }
        course.create(courseDtos);
    }
}
