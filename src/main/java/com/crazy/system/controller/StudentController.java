package com.crazy.system.controller;

import com.crazy.system.entity.Student;
import com.crazy.system.service.StudentService;
import com.crazy.system.util.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insert")
    public JsonResult insert(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.BindingError(bindingResult);
        }
        if (student.getStudentNumber() == null || student.getStudentGrade() == null) {
            return JsonResult.failure("学号或者年级不能为空");
        }
        int i = studentService.insertStudent(student);
        if (i == 0) {
            return JsonResult.SUCCESS;
        } else if (i == -1) {
            return JsonResult.FAILURE;
        }
        return JsonResult.failure("学号已存在");
    }

    @PutMapping("/update")
    public JsonResult update(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.BindingError(bindingResult);
        }
        if (student.getId() == null || student.getStudentNumber() == null || student.getStudentGrade() == null) {
            return JsonResult.failure("主键id、学号、年级不能为空");
        }
        int i = studentService.updateStudent(student);
        if (i == 0) {
            return JsonResult.SUCCESS;
        } else if (i == -1) {
            return JsonResult.FAILURE;
        }
        return JsonResult.failure("学号已存在，请重新添加");
    }

    @PutMapping("/delete")
    public JsonResult delete(@RequestParam(value = "id", required = true) Long id) {
        return studentService.deleteStudent(id) ? JsonResult.SUCCESS : JsonResult.FAILURE;
    }

    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", required = true, defaultValue = "0") int pageNum,
                           @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentService.list();
        PageInfo info = new PageInfo(list);
        return JsonResult.success(info);
    }

    /**
     * 通过学生主键id查询学各课程成绩
     *
     * @param id 学生主键
     */
    @GetMapping("/queryById")
    public JsonResult queryById(@RequestParam(value = "id", required = true) Long id) {
        List<Student> list = studentService.queryById(id);
        return JsonResult.success(list);
    }

    @GetMapping("/queryStudentByGrade")
    public JsonResult queryStudentByGrade(@RequestParam(value = "studentGrade", required = false) Integer studentGrade,
                                          @RequestParam(value = "studentDepartment", required = false) String studentDepartment,
                                          @RequestParam(value = "studentClass", required = false) String studentClass,
                                          @RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "pageNum", required = true, defaultValue = "0") int pageNum,
                                          @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = studentService.queryStudentIdByGrade(studentGrade, studentDepartment, studentClass, keyword);
        List<Student> students = (List<Student>) map.get("students");
        PageInfo info = null;
        if (students != null && students.size() > 0) {
            info = new PageInfo(students);
        }
        map.put("students", info);
        return JsonResult.success(map);
    }
}
