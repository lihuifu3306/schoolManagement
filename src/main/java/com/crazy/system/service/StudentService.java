package com.crazy.system.service;

import com.crazy.system.entity.Course;
import com.crazy.system.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface StudentService {

    int insertStudent(Student student);

    int updateStudent(Student student);

    boolean deleteStudent(Long id);

    List<Student> list();

    List<Student> queryById(Long id);

    Map<String, Object> queryStudentIdByGrade(Integer studentGrade, String studentDepartment, String studentClass, String keyword);
}
