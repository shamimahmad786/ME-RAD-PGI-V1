package com.me.bean;

import org.springframework.stereotype.Component;

@Component
public class StaticFilterBean {
private String filterName;
private String filterType;
private String filterColoumn;
private String filterDependencyFlag;
private String filterDependencyType;
private String filterDependencyColumn;
public String getFilterName() {
	return filterName;
}
public void setFilterName(String filterName) {
	this.filterName = filterName;
}
public String getFilterType() {
	return filterType;
}
public void setFilterType(String filterType) {
	this.filterType = filterType;
}
public String getFilterColoumn() {
	return filterColoumn;
}
public void setFilterColoumn(String filterColoumn) {
	this.filterColoumn = filterColoumn;
}
public String getFilterDependencyFlag() {
	return filterDependencyFlag;
}
public void setFilterDependencyFlag(String filterDependencyFlag) {
	this.filterDependencyFlag = filterDependencyFlag;
}
public String getFilterDependencyType() {
	return filterDependencyType;
}
public void setFilterDependencyType(String filterDependencyType) {
	this.filterDependencyType = filterDependencyType;
}
public String getFilterDependencyColumn() {
	return filterDependencyColumn;
}
public void setFilterDependencyColumn(String filterDependencyColumn) {
	this.filterDependencyColumn = filterDependencyColumn;
}


}
