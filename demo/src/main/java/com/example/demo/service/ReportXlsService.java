package com.example.demo.service;

import com.example.demo.dto.AllProjectDto;
import com.example.demo.dto.TagDto;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service(value = "ProjectReport_xls")
public class ReportXlsService implements ProjectReport {

    private static final Logger logger = LoggerFactory.getLogger(ReportTxtService.class);

    @Override
    public byte[] generateReport(AllProjectDto projectDto) throws IOException {
        String format = ".xls";
        File tmpFile = File.createTempFile(projectDto.getName() + format, null);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Project");
        Row row;
        Cell cell;
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("name project :" + projectDto.getName());
        row.createCell(1).setCellValue("Tags");
        int i = 1;
        for (TagDto tag : projectDto.getTags()) {
            row = sheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(tag.getName());
            i++;
        }
        FileOutputStream outFile = new FileOutputStream(tmpFile);
        workbook.write(outFile);
        workbook.close();
        outFile.close();
        byte[] result = Files.readAllBytes(tmpFile.toPath());
        tmpFile.delete();
        logger.info("Report in xls format has been successfully created from the project - " + projectDto.toString());
        return result;
    }
}
