package com.example.demo.service;

import com.example.demo.dto.ProjectNameValidationTagDto;
import com.example.demo.dto.TagNameDto;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service(value = "ProjectReport_xls")
public class ReportXlsService implements ProjectReport {

    @Override
    public byte[] generateReport(ProjectNameValidationTagDto projectDto) throws IOException {
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
        for (TagNameDto tag : projectDto.getTags()) {
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
        return result;
    }
}
