package com.crazy.system.service.serviceImpl;

import com.crazy.system.dao.CourseMapper;
import com.crazy.system.entity.Course;
import com.crazy.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.CSS;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int insertCourse(Course course) {
        if (course == null) {
            return -1;
        }
        if (courseMapper.queryCourseByNumber(course.getCourseNumber()) == null) {
            course.setCreateTime(LocalDateTime.now());
            course.setUpdateTime(LocalDateTime.now());
            return courseMapper.insertCourse(course) ? 0 : -1;
        }
        return 1;
    }
    /**
     * return 0新增成功，1课程编号已存在，-1新增失败
     * @param course
     */
    @Override
    public int updateCourse(Course course) {
        if (course == null) {
            return -1;
        }
        Course queryCourse = courseMapper.queryByIdAndNumber(course.getId(), course.getCourseNumber());
        if (queryCourse == null) {
            course.setUpdateTime(LocalDateTime.now());
            courseMapper.updateCourse(course);
            return courseMapper.updateCourse(course) ? 0 : -1;
        }
        return 1;
    }

    @Override
    public boolean deleteCourse(Long id) {
        return courseMapper.deleteCourse(id);
    }

    @Override
    public List<Course> list() {
        return courseMapper.list();
    }

    @Override
    public Map<Integer, String> listToMap() {
        List<Course> list = courseMapper.list();
        Map<Integer, String> map = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (Course course : list) {
                map.put(course.getCourseNumber(), course.getCourseName());
            }
        }
        return map;
    }
}
