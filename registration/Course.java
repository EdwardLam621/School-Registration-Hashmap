/*
 * Edward Lam 
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package registration;
import java.util.ArrayList;
import java.util.List;

import enums.SubjectCode;

/**
 * The Course class holds information about a course.
 * 
 * For example, CPSC 5011: Object-Oriented Concepts
 * - subject code: CPSC
 * - course number: 5011
 * - course name: Object-Oriented Concepts
 * - credit number: 3
 * - prerequisite(s): CPSC 5003 (can have multiple prerequisites or none)
 * 
 * @author 
 */
public class Course {
	
	SubjectCode code;
	String name;
	int courseNum, creditNum;
	List <Course> prereqList;
	
	/**
	 * Constructor of Course class
	 * 
	 * @param code		The subject code of the course
	 * @param courseNum	The course number of the course
	 * @param name		The course name
	 * @param creditNum The number of the credits of the course
	 */
	public Course(SubjectCode code, int courseNum, String name, 
					int creditNum) {
		
		this.code = code;
		this.courseNum = courseNum;
		this.name = name;
		this.creditNum = creditNum;
		prereqList = new ArrayList<Course>();
	
	}

	/**
	 * set course code 
	 */
	public void setCode(SubjectCode code) {
		this.code = code;
	}
	
	/**
	 * set course number 
	 */
	public void setCourseNum(int n) {
		this.courseNum = n;
	}
	
	/**
	 * set course name 
	 */
	public void setName(String n) {
		this.name = n;
	}
	
	/**
	 * set course credit 
	 */
	public void setCredit(int c) {
		this.creditNum = c;
	}
	
	/**
	 * set course prerequisite 
	 */
	public void setPrerequisite(Course c) {
		prereqList.add(c);
	}
	
	/**
	 * get course code 
	 * @return course code
	 */
	public SubjectCode getCode() {
		return this.code;
	}
	
	/**
	 * get course number 
	 * @return course number
	 */
	public int getCourseNum() {
		return this.courseNum;
	}
	
	/**
	 * get course name 
	 * @return course name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * get course credit 
	 * @return course credit
	 */
	public int getCredit() {
		return this.creditNum;
	}
	
	/**
	 * get course prerequisite 
	 * @return course prerequisite
	 */
	public String getPrerequisities(){
		String str = "";
		for(int i = 0; i < prereqList.size(); i++) {
			String a = prereqList.get(i).getCode().toString();
			int b = prereqList.get(i).getCourseNum();
			str += a + " " + b;
			if(i < prereqList.size() - 1) str += " & ";
		}
		if (str.length() <= 1) str = "----";
		return str;
			
	}

	@Override
	public String toString() {
		return String.format("%4s %-4d %-60s %1s", code, courseNum, name, getPrerequisities()+ "\n");
	}
	
}

