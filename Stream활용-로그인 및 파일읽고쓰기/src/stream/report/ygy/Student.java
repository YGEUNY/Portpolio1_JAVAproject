package stream.report.ygy;

import java.io.*;

public class Student implements Serializable{

	private int id;
	private String name;
	private double score;
	
	
	public Student(int id, String name, double score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	public Student() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
	public String toString() {
		return id+"   "+name+"   "+score;
	}
}
