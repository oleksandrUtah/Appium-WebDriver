package interviewExercises;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel{
    public static void main(String...strings) throws IOException {
        // Create an array with the data to be filled in Excel file:
        String[] valueToWrite = {"new_A-sell","new_B-sell", "new_C-sell"};
        // Create an object of current class:
        WriteExcel objExcelFile = new WriteExcel();
        // Write the file using file name, sheet name and the data to be filled
        objExcelFile.writeExcel("D:\\2019\\MyInterview_questions_31\\ExcelSheet.xlsx",
                "ExcelSheet.xlsx","ExcelMyDemo", valueToWrite);
    }
    public void writeExcel(String filePath, String fileName, String sheetName,
                           String[] dataToWrite) throws IOException{
        // Create an object of File class to open xlsx file:
        File file = new File(filePath);
        // Create an object of FileInputStream class to read Excel file
        FileInputStream inputStream = new FileInputStream(file);
        // Create object myWorkbook of XSSFWorkbook class:
        Workbook myWorkbook = new XSSFWorkbook(inputStream);
        // Read excel sheet by sheet name:
        Sheet sheet = myWorkbook.getSheet(sheetName);
        // Get the current count of rows in excel file:
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        // Get the first row from the sheet:
        Row row = sheet.getRow(0);
        // Create a new row and append it at last of sheet:
        Row newRow = sheet.createRow(rowCount+1);
        // Create a loop over the cell of newly created Row:
        for(int j = 0; j < row.getLastCellNum(); j++){
            // Fill data in row:
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        // Close input stream:
        inputStream.close();
        // Create an object of FileOutputStream class to create write data in excel file:
        FileOutputStream outputStream = new FileOutputStream(file);
        // Write data in the excel file:
        myWorkbook.write(outputStream);
        // Close output stream:
        outputStream.close();
    }
}
