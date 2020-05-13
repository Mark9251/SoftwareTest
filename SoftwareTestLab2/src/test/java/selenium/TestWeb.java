package selenium;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.*;

@RunWith(Parameterized.class)
public class TestWeb {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    private static final String url = System.getProperty("user.dir") + "/src/main/resources/file/Selenium+Lab2020.xlsx";
    private String username;
    private String password;

    public TestWeb(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Before
    public void setUp() {
        String driverPath = System.getProperty("user.dir") + "/src/main/resources/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(readFile(url));
    }

    @Test
    public void verify() {
        driver.get("http://103.120.226.190/selenium-demo/git-repo");
        driver.findElement(By.name("user_number")).sendKeys(this.username);
        driver.findElement(By.name("password")).sendKeys(this.password);
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-user.btn-block")).click();
        String text = driver.findElement(By.cssSelector(".mb-2+.mb-2")).getText();
        assertEquals(this.password, text);
    }

    public static Object[][] readFile(String filePath) {
        List<List<String>> fileList = new ArrayList<>();
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int firstRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();
        for (int i = firstRow; i < lastRow + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                int firstCell = row.getFirstCellNum();
                int lastCell = row.getLastCellNum();
                List<String> rowList = new ArrayList<>();
                for (int j = firstCell; j < lastCell; j++) {
                    XSSFCell cell = row.getCell(j);
                    if (cell != null && !"".equals(cell.toString())) {
                        rowList.add(cell.toString());
                    }
                }
                if (rowList.size() > 1) {
                    fileList.add(rowList);
                }
            }
        }
        String[][] array = new String[fileList.size()][fileList.get(0).size()];
        for (int i = 0; i < fileList.size(); i++) {
            for (int j = 0; j < fileList.get(0).size(); j++) {
                array[i][j] = fileList.get(i).get(j);
            }
        }
        return array;
    }
}