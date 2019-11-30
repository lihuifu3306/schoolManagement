package com.crazy.system.service;

import com.crazy.system.entity.Report;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {

    int insertReport(Report report);

    boolean updateReport(Long id, String reportScore);

    List<Report> queryByNumberAndType(Integer studentNumber, String keyword);

    List<Report> queryReportByStudentNumbers(List<Integer> studentNumbers);

    boolean delete(Long id);

}
