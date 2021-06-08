package ua.nix.academy.controller;

import org.hibernate.Session;
import ua.nix.academy.persistence.entity.Group;
import ua.nix.academy.persistence.entity.Lesson;
import ua.nix.academy.repository.impl.StudentRepositoryImpl;

public class Controller {

    public static Lesson studentInfo(Session session, long id) {
        return StudentRepositoryImpl.getInstance(session).takeInfoAboutLesson(id);
    }

  /*  public static Group groupInfo(Session session, long id){
        //return
    }*/
}