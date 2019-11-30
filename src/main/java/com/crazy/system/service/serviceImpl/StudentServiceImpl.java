package com.crazy.system.service.serviceImpl;

import com.crazy.system.dao.StudentMapper;
import com.crazy.system.entity.Report;
import com.crazy.system.entity.Student;
import com.crazy.system.service.CourseService;
import com.crazy.system.service.ReportService;
import com.crazy.system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ReportService reportService;

    @Autowired
    private CourseService courseService;

    /**
     * 0新增成功，1学号已存在，-1新增失败
     *
     * @param student
     */
    @Override
    public int insertStudent(Student student) {
        if (student == null) {
            return -1;
        }
        Student queryStudent = studentMapper.queryStudentByNumber(student.getStudentNumber());
        if (queryStudent == null) {
            student.setCreateTime(LocalDateTime.now());
            student.setUpdateTime(LocalDateTime.now());
            if (student.getStudentDelete() == null) {
                student.setStudentGrade(0);
            }
            return studentMapper.insertStudent(student) ? 0 : -1;
        }
        return 1;
    }

    /**
     * 0新增成功，1学号已存在，-1新增失败
     *
     * @param student
     */
    @Override
    public int updateStudent(Student student) {
        if (student == null) {
            return -1;
        }
        Student queryStudent = studentMapper.queryStudentByIdAndNumber(student.getId(), student.getStudentNumber());
        if (queryStudent == null) {
            student.setUpdateTime(LocalDateTime.now());
            return studentMapper.updateStudent(student) ? 0 : -1;
        }
        return 1;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return studentMapper.deleteStudent(id, LocalDateTime.now());
    }

    @Override
    public List<Student> list() {
        return studentMapper.list();
    }

    @Override
    public List<Student> queryById(Long id) {
        List<Student> students = studentMapper.queryById(id);
        if (students != null && students.size() > 0) {
            for (Student student : students) {
                int scores = 0;
                List<Report> reports = student.getReports();
                if (reports != null && reports.size() > 0) {
                    for (Report report : reports) {
                        Integer reportScore = report.getReportScore();
                        scores += reportScore;
                    }
                }
                student.setScores(scores);
            }
        }
        return students;
    }

    @Override
    public Map<String, Object> queryStudentIdByGrade(Integer studentGrade, String studentDepartment, String studentClass, String keyword) {
        HashMap<String, Object> resultMap = null;
        List<Student> students = studentMapper.queryStudentIdByGrade(studentGrade, studentDepartment, studentClass, keyword);
        if (students != null && students.size() > 0) {
            resultMap = new HashMap<>();
            Map<String, Integer> scoresMap = new HashMap<>();
            Map<Integer, String> courseNameMap = null;
            for (Student student : students) {
                Integer scores = 0;
                List<Report> reports = student.getReports();
                if (reports != null && reports.size() > 0) {
                    for (Report report : reports) {
                        courseNameMap = courseService.listToMap();
                        // 课程总分
                        Integer courseNumber = report.getCourseNumber();
                        String courseName = courseNameMap.get(courseNumber);
                        scores = scoresMap.get(courseName);
                        Integer reportScore = report.getReportScore();
                        if (scores != null) {
                            scores += reportScore;
                        } else {
                            scores = reportScore;
                        }
                        scoresMap.put(courseName, scores);
                    }
                }
                // 学生成绩总分
                student.setScores(scores);
            }
            if (scoresMap.size() > 0) {
                resultMap.put("scores", scoresMap);
                resultMap.put("students", students);
            }
        }
        return resultMap;

    }
}