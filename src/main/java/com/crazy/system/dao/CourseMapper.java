package com.crazy.system.dao;

import com.crazy.system.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    boolean insertCourse(@Param("course")Course course);

    Course queryCourseByNumber(@Param("courseNumber") Integer courseNumber);

    boolean updateCourse(@Param("course") Course course);

    Course queryByIdAndNumber(@Param("id") Long id, @Param("courseNumber") Integer courseNumber);

    boolean deleteCourse(@Param("id") Long id);

    List<Course> queryByCourseNumber(@Param("courseNumber") Integer courseNumber);

    List<Course> list();
}
