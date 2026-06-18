package com.example.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branch_Ifsc {
	@Id
	private String BranchName;
	private String Ifsc;
	public Branch_Ifsc() {
		
	}
	public Branch_Ifsc(String branchName, String ifsc) {
		super();
		BranchName = branchName;
		Ifsc = ifsc;
	}
	public String getBranchName() {
		return BranchName;
	}
	public void setBranchName(String branchName) {
		BranchName = branchName;
	}
	public String getIfsc() {
		return Ifsc;
	}
	public void setIfsc(String ifsc) {
		Ifsc = ifsc;
	}
	@Override
	public String toString() {
		return "Branch_Ifsc [BranchName=" + BranchName + ", Ifsc=" + Ifsc + "]";
	}
}
