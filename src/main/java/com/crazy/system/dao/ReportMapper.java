package com.crazy.system.dao;

import com.crazy.system.entity.Report;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportMapper {

    boolean insertReport(@Param("report") Report report);

    Report queryByReportType(@Param("studentNumber") Integer studentNumber,
                             @Param("courseNumber") Integer courseNumber,
                             @Param("reportType") String reportType);

    boolean updateReport(@Param("id") Long id,
                         @Param("reportScore") String reportScore,
                         @Param("updateTime") LocalDateTime updateTime);

    List<Report> queryByStudentNumber(@Param("studentNumber") Integer studentNumber);

    List<Report> queryByNumberAndType(@Param("studentNumber") Integer studentNumber, @Param("keyword") String keyword);

    List<Report> queryReport(@Param("ids") List<Integer> ids);

    boolean delete(@Param("id") Long id);
}
