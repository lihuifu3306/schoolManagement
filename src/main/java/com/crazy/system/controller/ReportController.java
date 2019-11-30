package com.crazy.system.controller;

import com.crazy.system.entity.Report;
import com.crazy.system.service.ReportService;
import com.crazy.system.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/insert")
    public JsonResult insert(@Valid Report report, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.BindingError(bindingResult);
        }
        if (report.getStudentNumber() == null || report.getCourseNumber() == null || report.getReportScore() == null) {
            return JsonResult.FAILURE;
        }
        int i = reportService.insertReport(report);
        if (i == 0) {
            return JsonResult.SUCCESS;
        } else if (i == -1) {
            return JsonResult.FAILURE;
        }
        return JsonResult.failure(report.getReportType()+"成绩已存在,不能重复添加");
    }

    @PutMapping("/update")
    public JsonResult update(@RequestParam(value = "id", required = true) Long id,
                             @RequestParam(value = "reportScore", required = true) String reportScore) {
        return reportService.updateReport(id, reportScore) ? JsonResult.SUCCESS : JsonResult.FAILURE;
    }

    /**
     * 通过学号和类型查询学生成绩信息
     */
    @GetMapping("/queryByNumberAndType")
    public JsonResult queryByNumberAndType(@RequestParam(value = "studentNumber", required = true) Integer studentNumber,
                                           @RequestParam(value = "keyword", required = false) String keyword) {
        return JsonResult.success(reportService.queryByNumberAndType(studentNumber, keyword));
    }

    @DeleteMapping("/delete")
    public JsonResult delete(@RequestParam("id") Long id) {
        return reportService.delete(id) ? JsonResult.SUCCESS : JsonResult.FAILURE;
    }
}
