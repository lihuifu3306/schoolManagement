package com.crazy.system.dao;

import com.crazy.system.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentMapper {

    boolean insertStudent(@Param("student") Student student);

    Student queryStudentByNumber(@Param("studentNumber") Integer studentNumber);

    Student queryStudentByIdAndNumber(@Param("id") Long id, @Param("studentNumber") Integer studentNumber);

    boolean updateStudent(@Param("student") Student student);

    boolean deleteStudent(@Param("id") Long id, @Param("deleteTime")LocalDateTime deleteTime);

    List<Student> list();

    List<Student> queryById(@Param("id") Long id);

    List<Student> queryStudentIdByGrade(@Param("studentGrade") Integer studentGrade,
                                        @Param("studentDepartment") String studentDepartment,
                                        @Param("studentClass") String studentClass,
                                        @Param("keyword") String keyword);
}
