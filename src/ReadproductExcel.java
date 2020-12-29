import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadproductExcel {
    public product[] readExcel(InputStream in) {
        product products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                product  pro = new product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);


                    if (cell == null)

                        continue;
                    if (k == 0) {
                        pro.setProductID(this.getValue(cell));//给username属性赋值

                    } else if (k == 1) {
                        pro.setProductName(this.getValue(cell));//给password属性赋值

                    } else if (k == 2) {
                        pro.getProductPrice(this.getValue(cell));//给address属性赋值

                    } else if (k == 3) {
                        pro.setProductDescribe(this.getValue(cell));//给phone属性赋值

                    }
                }
                products[j-1] = pro;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();
        DecimalFormat df=new DecimalFormat("#");

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = df.format(cell.getNumericCellValue() );
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}