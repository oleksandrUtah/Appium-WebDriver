package interviewExercises;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

//How to read Excel files using Apache POI
public class ReadExcel {
    public static void main (String [] args) throws IOException{
        // I have placed the Excel file 'ExcelSheet.xlsx' in directory: "D:\\2019\\..."
        FileInputStream fis = new FileInputStream("D:\\2019\\MyInterview_questions_31\\ExcelSheet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        //I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
        // Detail output: row - cell - print:
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell);
        // Short output: print(sheet.getRow(rownum).getCell(cellnum)):
        System.out.println(sheet.getRow(0).getCell(0));
        // String output:
        String cellval = cell.getStringCellValue();
        System.out.println(cellval);
    }
}
