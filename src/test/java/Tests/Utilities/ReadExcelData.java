package Tests.Utilities;

import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadExcelData {
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

        String testData[][] = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = format.formatCellValue(SheetName.getRow(i).getCell(j));
                System.out.println(testData[i - 1][j]);
            }
        }
        return testData;

        }

    }
