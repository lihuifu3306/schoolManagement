package com.crazy.system.controller;

import com.crazy.system.entity.Course;
import com.crazy.system.service.CourseService;
import com.crazy.system.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/insert")
    public JsonResult insert(@Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.BindingError(bindingResult);
        }
        if (course.getCourseNumber() == null) {
            return JsonResult.failure("课程编不能为空");
        }
        int i = courseService.insertCourse(course);
        if (i == 0) {
            return JsonResult.SUCCESS;
        } else if (i == -1) {
            return JsonResult.FAILURE;
        }
        return JsonResult.failure("课程编码已存在");
    }

    @PutMapping("/update")
    public JsonResult update(@Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            JsonResult.BindingError(bindingResult);
        }
        if (course.getId() == null || course.getCourseNumber() == null) {
            return JsonResult.failure("主键或者课程编码不能为空");
        }
        int i = courseService.updateCourse(course);
        if (i == 0) {
            return JsonResult.SUCCESS;
        } else if (i == -1) {
            return JsonResult.FAILURE;
        }
        return JsonResult.failure("课程编码已存在.");
    }

    @DeleteMapping("/delete")
    public JsonResult delete(@RequestParam(value = "id", required = true) Long id) {
        return courseService.deleteCourse(id) ? JsonResult.SUCCESS : JsonResult.FAILURE;
    }

    @GetMapping("list")
    public JsonResult list() {
        List<Course> list = courseService.list();
        return JsonResult.success(list);
    }
}
