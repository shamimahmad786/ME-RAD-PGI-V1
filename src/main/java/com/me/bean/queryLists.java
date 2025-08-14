package com.me.bean;

import java.util.List;

public class queryLists {
List<tablesList>  tablesList;
List<List<columnsList>> columnsList;
List<filterData> filterDatas;
List<aggrigationData> aggrigationDatas;
List<String> groupDatas;
public List<tablesList> getTablesList() {
	return tablesList;
}
public void setTablesList(List<tablesList> tablesList) {
	this.tablesList = tablesList;
}
public List<List<columnsList>> getColumnsList() {
	return columnsList;
}
public void setColumnsList(List<List<columnsList>> columnsList) {
	this.columnsList = columnsList;
}
public List<filterData> getFilterData() {
	return filterDatas;
}
public void setFilterData(List<filterData> filterData) {
	this.filterDatas = filterData;
}
public List<filterData> getFilterDatas() {
	return filterDatas;
}
public void setFilterDatas(List<filterData> filterDatas) {
	this.filterDatas = filterDatas;
}
public List<aggrigationData> getAggrigationDatas() {
	return aggrigationDatas;
}
public void setAggrigationDatas(List<aggrigationData> aggrigationDatas) {
	this.aggrigationDatas = aggrigationDatas;
}
public List<String> getGroupDatas() {
	return groupDatas;
}
public void setGroupDatas(List<String> groupDatas) {
	this.groupDatas = groupDatas;
}




}
