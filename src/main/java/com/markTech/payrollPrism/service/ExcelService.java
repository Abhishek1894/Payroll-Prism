package com.markTech.payrollPrism.service;

import com.markTech.payrollPrism.DTO.SalaryDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService
{
    public List<SalaryDTO> getJsonFromExcelFile(MultipartFile file) throws IOException
    {
        List<SalaryDTO> salaries = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        Sheet sheet = workbook.getSheetAt(0);

        int validRowCount = 0;

        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                boolean isEmpty = true;
                for (Cell cell : row) {
                    if (cell != null && cell.getCellType() != CellType.BLANK &&
                            !(cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty())) {
                        isEmpty = false;
                        break;
                    }
                }
                if (!isEmpty) {
                    validRowCount++;
                }
            }
        }

        for (int i = 1; i < validRowCount; i++)
        { // start from 1 to skip header
            Row row = sheet.getRow(i);

            if (row != null)
            {

                SalaryDTO salary = new SalaryDTO();

                salary.setEmployee_id(getCellValue(row.getCell(1)));
                salary.setSalaryDate(getCellValue(row.getCell(2)));
                salary.setSalaryMonth(getCellValue(row.getCell(3)));
                salary.setSalaryYear(getCellValue(row.getCell(4)));
                salary.setCalenderDays(getCellValue(row.getCell(5)));
                salary.setPaidDays(getCellValue(row.getCell(6)));
                salary.setArearDays(getCellValue(row.getCell(7)));
                salary.setOvertimeDays(getCellValue(row.getCell(8)));
                salary.setOvertimeRate(getCellValue(row.getCell(9)));
                salary.setLopDays(getCellValue(row.getCell(10)));
                salary.setBasic(getCellValue(row.getCell(11)));
                salary.setHra(getCellValue(row.getCell(12)));
                salary.setConveyanceAllowance(getCellValue(row.getCell(13)));
                salary.setPaymentsOvertime(getCellValue(row.getCell(14)));
                salary.setNightShiftAllowance(getCellValue(row.getCell(15)));
                salary.setSpecialAllowance(getCellValue(row.getCell(16)));
                salary.setDeputationAllowance(getCellValue(row.getCell(17)));
                salary.setBonus(getCellValue(row.getCell(18)));
                salary.setOtherAllowance(getCellValue(row.getCell(19)));
                salary.setArear(getCellValue(row.getCell(20)));
                salary.setGrossNetTotal(getCellValue(row.getCell(21)));
                salary.setPf(getCellValue(row.getCell(22)));
                salary.setPt(getCellValue(row.getCell(23)));
                salary.setTds(getCellValue(row.getCell(24)));
                salary.setOtherDeduction(getCellValue(row.getCell(25)));
                salary.setGrossDeduction(getCellValue(row.getCell(26)));
                salary.setNetTakeHome(getCellValue(row.getCell(27)));

                salaries.add(salary);
            }
        }

        return salaries;
    }


    private String getCellValue(Cell cell)
    {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // or format it
                }
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return String.valueOf(cell.getNumericCellValue());// or evaluate it using FormulaEvaluator

            case BLANK:
            default:
                return "";
        }
    }
}
