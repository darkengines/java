package darkengines.importer;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import darkengines.database.DBSessionFactory;

public class Importer {
	private Session session;

	public Importer() {
		session = DBSessionFactory.GetSession();
	}

	public ImportResult Import(String dataFilePath) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, NoSuchFieldException {
		InputStream stream = new FileInputStream(dataFilePath);
		Workbook workbook = new XSSFWorkbook(stream);
		int sheetCount = workbook.getNumberOfSheets();
		Map<String, ClassInfo> infos = new HashMap<String, ClassInfo>();
		int i = 0;
		while (i < sheetCount) {
			Sheet sheet = workbook.getSheetAt(i);
			String className = sheet.getRow(0).getCell(0).getStringCellValue();
			ClassInfo classInfo = new ClassInfo();
			Row fieldsRow = sheet.getRow(3);
			for (Cell cell : fieldsRow) {
				int columnIndex = cell.getColumnIndex();
				String fieldName = cell.getStringCellValue();
				FieldInfo fieldInfo = new FieldInfo();
				Row keysFieldsRow = sheet.getRow(2);
				if (keysFieldsRow != null) {
					String keysCellValue = keysFieldsRow.getCell(columnIndex)
							.getStringCellValue();
					if (keysCellValue != null && keysCellValue != "") {
						fieldInfo.getKeys().addAll(
								Arrays.asList(keysCellValue.split(",")));
					}
				}
				boolean isKey = sheet.getRow(1).getCell(columnIndex)
						.getBooleanCellValue();
				fieldInfo.setKey(isKey);
				classInfo.getFieldInfos().put(fieldName, fieldInfo);
			}
			infos.put(className, classInfo);
			i++;
		}
		i = 0;
		while (i < sheetCount) {
			Sheet sheet = workbook.getSheetAt(i);
			String className = sheet.getRow(0).getCell(0).getStringCellValue();
			Class<?> c = Class.forName(className);
			Row fieldsRow = sheet.getRow(3);
			ClassInfo classInfo = infos.get(className);
			Transaction transaction = session.beginTransaction();
			for (Row row : sheet) {
				int rowIndex = row.getRowNum();
				if (rowIndex > 3) {
					Map<String, String> xKeyFieldValues = new HashMap<String, String>();
					Map<String, Object> xFieldValues = new HashMap<String, Object>();
					for (Cell cell : fieldsRow) {
						int columnIndex = cell.getColumnIndex();
						String fieldName = sheet.getRow(3).getCell(columnIndex)
								.getStringCellValue();
						FieldInfo fieldInfo = classInfo.getFieldInfos().get(
								fieldName);
						Field field = c.getDeclaredField(fieldName);
						boolean isComplex = field.getType()
								.isAnnotationPresent(Entity.class);
						Object value = null;
						if (isComplex) {
							ArrayList<String> keys = new ArrayList<String>();
							keys.addAll(Arrays.asList(row.getCell(columnIndex)
									.getStringCellValue().split(",")));
							Map<String, String> keyFieldValues = toKeyFieldValuesMap(fieldInfo.getKeys(), keys);
							value = getEntityByKey(field.getType(), keyFieldValues);
						} else {
							value = CellToObject(row.getCell(columnIndex));
						}
						if (fieldInfo.isKey()) {
							xKeyFieldValues.put(fieldName, value.toString());
						}
						xFieldValues.put(fieldName, value);
					}
					Object x = getEntityByKey(c, xKeyFieldValues);
					if (x == null) {
						x = c.getConstructor().newInstance();
					}
					for (String fieldName: xFieldValues.keySet()) {
						Field field = c.getDeclaredField(fieldName);
						field.setAccessible(true);
						field.set(x, xFieldValues.get(fieldName));
					}
					session.saveOrUpdate(x);
					session.flush();
				}
			}
			transaction.commit();
			i++;
		}
		session.close();
		return null;
	}
	
	private Map<String, String> toKeyFieldValuesMap(ArrayList<String> keyFields, ArrayList<String> keyValues) {
		Map<String, String> keyFieldValues = new HashMap<String, String>();
		int keyIndex = 0;
		for (String keyField : keyFields) {
			keyFieldValues.put(keyField, keyValues.get(keyIndex));
			keyIndex++;
		}
		return keyFieldValues;
	}
	
	private Object getEntityByKey(Class<?> type, Map<String, String> keyFieldValues) {
		Criteria criteria = session.createCriteria(type);
		for (String key: keyFieldValues.keySet()) {
			criteria.add(Restrictions.eq(key,
					convert(String.class, keyFieldValues.get(key))));
		}
		return criteria.uniqueResult();
	}

	private Object convert(Class<?> targetType, String text) {
		PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
		editor.setAsText(text);
		return editor.getValue();
	}

	private Object CellToObject(Cell cell) {
		switch (cell.getCellType()) {
		case (HSSFCell.CELL_TYPE_NUMERIC): {
			return cell.getNumericCellValue();
		}

		case (HSSFCell.CELL_TYPE_STRING): {
			return cell.getStringCellValue();
		}

		default:
			return null;
		}
	}
}
