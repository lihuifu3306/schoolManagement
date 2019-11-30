package com.crazy.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Student implements Serializable {
    public Student() {
    }

    private Long id;
//    @NotBlank(message = "学号不能为空")
//    @Length(min = 0, max = 10, message = "长度不正确")
    private Integer studentNumber;
    @NotBlank(message = "学生姓名不能为空")
    @Length(min = 0, max = 10, message = "姓名长度不正确")
    private String studentName;
    private Integer studentSex;
    private Integer studentAge;
    @NotBlank(message = "班级不能为空")
    @Length(min = 0, max = 10, message = "班级长度不正确")
    private String studentClass;
    @NotBlank(message = "系别不能为空")
    @Length(min = 0, max = 10, message = "系别长度不正确")
    private String studentDepartment;
    private Integer studentGrade;
    private Integer studentPhone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer studentDelete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deleteTime;

    private List<Report> reports;

    private Integer scores;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber=" + studentNumber +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentAge=" + studentAge +
                ", studentClass='" + studentClass + '\'' +
                ", studentDepartment='" + studentDepartment + '\'' +
                ", studentGrade=" + studentGrade +
                ", studentPhone=" + studentPhone +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", studentDelete=" + studentDelete +
                ", deleteTime=" + deleteTime +
                ", reports=" + reports +
                ", scores=" + scores +
                '}';
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public Integer getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(Integer studentPhone) {
        this.studentPhone = studentPhone;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStudentDelete() {
        return studentDelete;
    }

    public void setStudentDelete(Integer studentDelete) {
        this.studentDelete = studentDelete;
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }
}
