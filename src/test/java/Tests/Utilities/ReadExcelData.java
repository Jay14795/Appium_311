package Tests.Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadExcelData {
    //LoginData
   @DataProvider(name = "LoginData")
    public Object[][] getData(Method m) throws IOException {
        String excelSheetName = m.getName();
        System.out.println("ExcelSheetName is" + " " + excelSheetName);
        File f = new File(System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\TestData\\LoginData.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet SheetName = wb.getSheet(excelSheetName);

        int totalRows = SheetName.getLastRowNum();
        System.out.println(totalRows);
        Row rowCells = SheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
        System.out.println(totalCols);

        DataFormatter format = new DataFormatter();

        String[][] testData = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = format.formatCellValue(SheetName.getRow(i).getCell(j));
                System.out.println(testData[i - 1][j]);
            }
        }
        return testData;

    }


    //ForgotPassword Data
    @DataProvider(name = "ForgotPw")
    public Object[][] getExcelData() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\ForgotPw.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Object[][] data = new Object[sheet.getLastRowNum() + 1][2];
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell emailCell = row.getCell(0);
            Cell expectedResponseCell = row.getCell(1);
            String email = null;
            String expectedResponse = null;
            if (emailCell != null) {
                emailCell.setCellType(CellType.STRING);
                email = emailCell.getStringCellValue();
            }
            if (expectedResponseCell != null) {
                expectedResponseCell.setCellType(CellType.STRING);
                expectedResponse = expectedResponseCell.getStringCellValue();
            }
            data[i][0] = email;
            data[i][1] = expectedResponse;
        }
        fis.close();
        workbook.close();
        return data;
    }


   //Registration Data
    @DataProvider(name = "RegisterData")
    public Object[][] testData() {
        String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\Registration.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Excel file not found at " + filePath);
        }
        return readExcelData(filePath);
    }
    private Object[][] readExcelData(String filePath) {
        Object[][] data;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Register_Data");
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            data = new Object[rowCount][colCount];
            for (int i = 1; i <= rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                        } else {
                            data[i - 1][j] = cell.getStringCellValue();
                        }
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
            fis.close();
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
        return data;
    }

    @DataProvider(name = "WetWasteData")
    public Object[][] getData() throws IOException {
        String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\WetWasteData.xlsx";
        String sheetName = "WetWaste";

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        return data;
    }

    @DataProvider(name = "CompostData")
    public Object[][] getCompostData() throws IOException {
        String filePath = "C:\\Users\\Jay Gandhi\\Downloads\\selenium4poc-master\\Appium_311\\src\\test\\java\\Resources\\TestData\\WetWasteData.xlsx";
        String sheetName = "CompostData";

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);

                if (cell != null) {
                    String cellValue = cell.toString();
                    data[i - 1][j] = cell.toString();

                    // Rest of the code
                } else {
                    // Handle the null value
                }

            }
        }

        return data;
    }


}