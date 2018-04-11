package cn.hxy.bbs.dto;

import cn.hxy.bbs.model.User;

public class UserDetail extends User{
private String url;
private String role;
private String permission;

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getPermission() {
	return permission;
}

public void setPermission(String permission) {
	this.permission = permission;
}
}
