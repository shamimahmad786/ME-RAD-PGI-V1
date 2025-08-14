package com.me.model;

public class DomainScore implements java.io.Serializable {
	private int domain1;
	private int domain2;
	private int domain3;
	private int domain4;
	private int domain5;
	private int domain1Max = 180;
	private int domain2Max = 80;
	private int domain3Max = 150;
	private int domain4Max = 230;
	private int domain5Max = 360;
	private int totalScore;
	private String grade;

	public DomainScore() {
	}

	public int getDomain1() {
		return domain1;
	}

	public void setDomain1(int domain1) {
		this.domain1 = domain1;
	}

	public int getDomain2() {
		return domain2;
	}

	public void setDomain2(int domain2) {
		this.domain2 = domain2;
	}

	public int getDomain3() {
		return domain3;
	}

	public void setDomain3(int domain3) {
		this.domain3 = domain3;
	}

	public int getDomain4() {
		return domain4;
	}

	public void setDomain4(int domain4) {
		this.domain4 = domain4;
	}

	public int getDomain5() {
		return domain5;
	}

	public void setDomain5(int domain5) {
		this.domain5 = domain5;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getDomain1Max() {
		return domain1Max;
	}

	public int getDomain2Max() {
		return domain2Max;
	}

	public int getDomain3Max() {
		return domain3Max;
	}

	public int getDomain4Max() {
		return domain4Max;
	}

	public int getDomain5Max() {
		return domain5Max;
	}

}
