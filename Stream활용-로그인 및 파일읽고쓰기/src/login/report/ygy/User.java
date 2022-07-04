package login.report.ygy;

import java.io.Serializable;

public abstract class User implements Serializable{
	protected String id, pw, name, adress, grade;
	
	public User(String id, String pw, String name, String adress, String grade) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.adress = adress;
		this.grade = grade;
	}
	
	public User() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}


}
