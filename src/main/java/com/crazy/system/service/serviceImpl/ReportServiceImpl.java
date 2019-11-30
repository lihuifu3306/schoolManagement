package com.crazy.system.service.serviceImpl;

import com.crazy.system.dao.ReportMapper;
import com.crazy.system.entity.Report;
import com.crazy.system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public int insertReport(Report report) {
        if (report == null) {
            return -1;
        }
        Report queryReport = reportMapper.queryByReportType(report.getStudentNumber(), report.getCourseNumber(), report.getReportType());
        if (queryReport == null) {
            report.setCreateTime(LocalDateTime.now());
            report.setUpdateTime(LocalDateTime.now());
            return reportMapper.insertReport(report) ? 0: -1;
        }
        return 1;
    }

    @Override
    public boolean updateReport(Long id, String reportScore) {
        return reportMapper.updateReport(id, reportScore, LocalDateTime.now());
    }

    @Override
    public List<Report> queryByNumberAndType(Integer studentNumber, String keyword) {
        return reportMapper.queryByNumberAndType(studentNumber, keyword);
    }

    @Override
    public List<Report> queryReportByStudentNumbers(List<Integer> studentNumbers) {
        if (studentNumbers != null && studentNumbers.size() > 0) {
            return reportMapper.queryReport(studentNumbers);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
       return reportMapper.delete(id);
    }
}
