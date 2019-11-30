package com.crazy.system.service;

import com.crazy.system.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseService {

    int insertCourse(Course course);

    int updateCourse(Course course);

    boolean deleteCourse(Long id);

    List<Course> list();

    Map<Integer, String> listToMap();


}
