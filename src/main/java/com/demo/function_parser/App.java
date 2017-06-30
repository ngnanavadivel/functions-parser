package com.demo.function_parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		List<Function> functions = new ArrayList<Function>();
		InputStream istream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("Functions_6Dec2016.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(istream);
		if (workbook != null) {
			Sheet firstWorkSheet = workbook.getSheetAt(0);
			if (firstWorkSheet != null) {
				Iterator<Row> rowIterator = firstWorkSheet.rowIterator();
				int rowIndex = -1;
				while (rowIterator.hasNext()) {
					System.out.println("ROW -> " + rowIndex);
					Row row = rowIterator.next();

					if (rowIndex == -1 || rowIndex == 0) {
						rowIndex++;
						continue;
					}

					Function function = new Function();
					functions.add(function);
					function.setId(rowIndex);

					Iterator<Cell> cellIterator = row.cellIterator();
					int cellIndex = 1;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String massagedValue = transformParams(cell.getStringCellValue());
						if (cellIndex == 1) {
							function.setName(cell.getStringCellValue());
						} else if (cellIndex == 2) {
							function.setCode(cell.getStringCellValue());
						} else if (cellIndex == 3) {
							function.setDescription(cell.getStringCellValue());
						} else if (cellIndex == 4) {
							function.getTemplates().add(new FunctionTemplate("ORACLE", massagedValue));
						} else if (cellIndex == 5) {
							function.getTemplates().add(new FunctionTemplate("MYSQL", massagedValue));
						} else if (cellIndex == 6) {
							function.getTemplates().add(new FunctionTemplate("SQLSERVER", massagedValue));
						} else if (cellIndex == 7) {
							function.getTemplates().add(new FunctionTemplate("POSTGRESQL", massagedValue));
						} else if (cellIndex == 8) {
							function.getTemplates().add(new FunctionTemplate("SYBASE", massagedValue));
						} else if (cellIndex == 9) {
							function.getTemplates().add(new FunctionTemplate("DB2", massagedValue));
						} else if (cellIndex == 10) {
							function.getTemplates().add(new FunctionTemplate("MONGO", massagedValue));
						} else if (cellIndex == 11) {
							function.getTemplates().add(new FunctionTemplate("JAVA", massagedValue));
						} else if (cellIndex == 12) {
							function.getTemplates().add(new FunctionTemplate("SCALA", massagedValue));
						} else if (cellIndex == 13) {
							function.getTemplates().add(new FunctionTemplate("HIVE", massagedValue));
						} else if (cellIndex == 14) {
							function.getTemplates().add(new FunctionTemplate("IMPALA", massagedValue));
						} else if (cellIndex == 15) {
							function.getTemplates().add(new FunctionTemplate("DRILL", massagedValue));
						}
						cellIndex++;
					}
					rowIndex++;
				}
			}
			workbook.close();

			System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(functions));
			int index = 14;
			for(Function func: functions) {
				System.out.println("INSERT INTO trans_func_decode values(" + index++ + ", '" + func.getName() + "', '', '', 0);");
			}
		}
	}

	private static String transformParams(String value) {
		return value.replace("$a", "$0").replace("$b", "$1").replace("$c", "$2").replace("$d", "$3").replace("$e",
				"$4");
	}
}
