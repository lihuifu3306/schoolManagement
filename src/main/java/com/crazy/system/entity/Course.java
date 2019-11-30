package com.crazy.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Course implements Serializable {
    public Course() {
    }
    private Long id;
    private Integer courseNumber;
    @NotBlank(message = "课程名不能为空")
    @Length(min = 0, max= 10, message = "课程名长度不正确")
    private String courseName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public Course(Long id, Integer courseNumber, @NotBlank(message = "课程名不能为空") @Length(min = 0, max = 10, message = "课程名长度不正确") String courseName, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
