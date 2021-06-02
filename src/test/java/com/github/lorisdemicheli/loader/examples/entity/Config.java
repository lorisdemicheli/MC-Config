package com.github.lorisdemicheli.loader.examples.entity;

import com.github.lorisdemicheli.loader.yaml.annotations.Column;
import com.github.lorisdemicheli.loader.yaml.annotations.Entity;
import com.github.lorisdemicheli.loader.yaml.annotations.YmlFile;

@Entity
@YmlFile(fileName = "ConfigSQL")
public class Config {

	@Column
	private boolean useSql;
	@Column
	private String user;
	@Column
	private String password;

	public Config() {}

	public Config(boolean useSql, String user, String password) {
		this.useSql = useSql;
		this.user = user;
		this.password = password;
	}



	public boolean isUseSql() {
		return useSql;
	}

	public void setUseSql(boolean useSql) {
		this.useSql = useSql;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Config [useSql=" + useSql + ", user=" + user + ", password=" + password + "]";
	}

}
