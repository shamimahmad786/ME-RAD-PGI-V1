package com.me.bean;

import com.me.model.DomainScore;

public class StateIndicatorScores {
private String state_name;
private String path;
private String banner;
private int state_id;
private DomainScore domainScore;
public String getState_name() {
	return state_name;
}
public void setState_name(String state_name) {
	this.state_name = state_name;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getBanner() {
	return banner;
}
public void setBanner(String banner) {
	this.banner = banner;
}
public int getState_id() {
	return state_id;
}
public void setState_id(int state_id) {
	this.state_id = state_id;
}
public DomainScore getDomainScore() {
	return domainScore;
}
public void setDomainScore(DomainScore domainScore) {
	this.domainScore = domainScore;
}


}
