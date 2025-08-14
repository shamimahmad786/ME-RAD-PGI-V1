package com.me.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.me.model.GroupMapping;
@Component
public class AddGroup {
private int groupId;
private String groupName;
private List<GroupMapping> groupMapping;
public int getGroupId() {
	return groupId;
}
public void setGroupId(int groupId) {
	this.groupId = groupId;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(String groupName) {
	this.groupName = groupName;
}
public List<GroupMapping> getGroupMapping() {
	return groupMapping;
}
public void setGroupMapping(List<GroupMapping> groupMapping) {
	this.groupMapping = groupMapping;
}

}
