package com.example.demo.service;

import com.example.demo.exception.FormatNotFoundException;
import com.example.demo.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SelectorReportService {

    @Autowired
    Map<String, ProjectReport> projectReports = new HashMap<>();

    public ProjectReport getProjectReport(String format) throws FormatNotFoundException {
        ProjectReport projectReport = projectReports.get(Constants.PATTERN + format);
        if (projectReport == null)
            throw new FormatNotFoundException("Format " + format + " not supported");
        return projectReport;

    }

}

