package com.me.util;

import org.springframework.stereotype.Component;

@Component
public class CommonMethod {
	public String returnValue(String columnValue, String returnType) {

		if (returnType.equalsIgnoreCase("String")) {
			return "'" + columnValue + "'";
		} else if (returnType.equalsIgnoreCase("int")) {
			return columnValue;
		} else {
			return columnValue;
		}
	}

	public String inReturnValue(String columnValue, String returnType) {
		System.out.println("returnType--->" + returnType);
		String splitedValue = " ( ";
		String[] splitted = columnValue.split(",");
		if (returnType.equalsIgnoreCase("String")) {

			for (String st : splitted) {
				splitedValue += " '" + st + "'" + " , ";
			}
			splitedValue = splitedValue.replaceAll(", $", "") + " ) ";
			System.out.println("splitedValue--->" + splitedValue);
			return splitedValue;
		} else if (returnType.equalsIgnoreCase("int")) {
			for (String st : splitted) {
				splitedValue += st + " , ";
			}
			splitedValue = splitedValue.replaceAll(", $", "") + " ) ";
			System.out.println("splitedValue--->" + splitedValue);
			return splitedValue;

		} else {
			System.out.println("splitedValue--->" + splitedValue);
			return columnValue;
		}

	}
	
	public String returnValueForLike(String columnValue, String returnType) {
		System.out.println("returnType--->" + returnType);
		String splitedValue = " ( ";
		String[] splitted = columnValue.split(",");
		if (returnType.equalsIgnoreCase("String")) {

			for (String st : splitted) {
				splitedValue += " '%" + st + "%'" + " , ";
			}
			splitedValue = splitedValue.replaceAll(", $", "") + " ) ";
			System.out.println("splitedValue--->" + splitedValue);
			return splitedValue;
		} else if (returnType.equalsIgnoreCase("int")) {
			for (String st : splitted) {
				splitedValue += st + " , ";
			}
			splitedValue = splitedValue.replaceAll(", $", "") + " ) ";
			System.out.println("splitedValue--->" + splitedValue);
			return splitedValue;

		} else {
			System.out.println("splitedValue--->" + splitedValue);
			return columnValue;
		}

	}
	
	

	public String returnAggrigateColumn(String aggriName, String columnName, String tableName,String columnType) {

		System.out.println("aggriName---->" + aggriName);
		System.out.println("columnName--->" + columnName);
		System.out.println("tableName---->" + tableName);
		
		if (aggriName.equalsIgnoreCase("Number of Records")) {
			if(columnType.equalsIgnoreCase("F")) {
				columnName = "count("+ columnName +")";
			}else {
			columnName = "count(" + tableName + "." + columnName + ")";
			}
		} else if (aggriName.equalsIgnoreCase("Sum of")) {
			if(columnType.equalsIgnoreCase("F")) {
				columnName = "sum(" + columnName + ")";
			}else {
				columnName = "sum(" + tableName + "." + columnName + ")";	
			}
			
			
		} else if (aggriName.equalsIgnoreCase("Average of")) {
			
			if(columnType.equalsIgnoreCase("F")) {
				columnName = "avg("+ columnName + ")";
			}else {
				columnName = "avg(" + tableName + "." + columnName + ")";
			}
			
			
		} else if (aggriName.equalsIgnoreCase("Number of distinct values of")) {
			if(columnType.equalsIgnoreCase("F")) {
				columnName = "distinct(" + columnName + ")";
			}else {
				columnName = "distinct(" + tableName + "." + columnName + ")";
			}
			
			
			
		} else if (aggriName.equalsIgnoreCase("Cumulative sum of")) {

		} else if (aggriName.equalsIgnoreCase("Cumulative count of rows")) {

		} else if (aggriName.equalsIgnoreCase("Standard deviation of")) {

		} else if (aggriName.equalsIgnoreCase("Minimum of")) {
			
			if(columnType.equalsIgnoreCase("F")) {
				columnName = "min(" + columnName + ")";
			}else {
				columnName = "min(" + tableName + "." + columnName + ")";
			}
			
			
		} else if (aggriName.equalsIgnoreCase("Maximum of")) {
			columnName = "max(" + tableName + "." + columnName + ")";
		}

		// else if(aggriName.equalsIgnoreCase("In")) {
		// String splitedValue="";
		// columnName=tableName+"."+columnName +" in ("+splitedValue+")";
		// // columnName="in("+tableName+"."+columnName+")";
		// }

		return columnName;

	}
}
