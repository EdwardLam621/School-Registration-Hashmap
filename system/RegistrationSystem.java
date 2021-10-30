/*
 * Edward Lam 
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package system;

import java.util.HashMap;
import enums.Building;
import enums.FacultyType;
import enums.Quarter;
import enums.StudentProgram;
import enums.StudentType;
import enums.SubjectCode;
import exception.CourseNotFoundException;
import exception.DuplicateCourseException;
import exception.DuplicatePersonException;
import exception.DuplicateSubjectException;
import exception.PersonNotFoundException;
import exception.SectionNotFoundException;
import javafx.util.Pair;
import person.Faculty;
import person.Student;
import registration.Course;
import registration.Section;
/**
 * The RegistrationSystem class stores information about the school, including
 * the ability to add students, add faculty, add courses, and add prerequisite(s).
 * 
 * @author Edward Lam
 */
public class RegistrationSystem {

	
	/**
	 * 
	 */
	public RegistrationSystem() { 
		studentList = new HashMap<>();
		facultyList = new HashMap<>();
		subjectList = new HashMap<>();
		courseList = new HashMap<>();
		sectionList = new HashMap<>();
	}
	
	/**
	 * Add a student to the student list collection.
	 * 
	 * @param firstName	The first name of the student
	 * @param lastName	The last name of the student
	 * @param type		The student type
	 * @param program	The student program	
	 * @param quarter	The start quarter of the student
	 * @param year		The start year of the student
	 * @throws DuplicatePersonException The person is already in the system
	 */
	public void addStudent(String firstName, String lastName, 
							StudentType type, StudentProgram program,
							Quarter quarter, int year) 
							throws DuplicatePersonException {
		
		int hash = firstName.hashCode()+lastName.hashCode();
		
		//throw exist exception
		if(existStudent(firstName,lastName) == true) throw 
		new DuplicatePersonException();

		Student newStudent = new Student(firstName, lastName);
		newStudent.setStatus(type);
		newStudent.setProgram(program);
		newStudent.setQuarter(quarter);
		newStudent.setYear(year);
		studentList.put(hash, newStudent);
	
	}
	
	/**
	 * Add a faculty to the faculty list collection.
	 * 
	 * @param firstName	The first name of the faculty
	 * @param lastName	The last name of the faculty
	 * @param type		The faculty type
	 * @param bldg		The building of the faculty office
	 * @param room		The (building) room of the faculty office
	 * @param email		The email of the faculty
	 * @throws DuplicatePersonException The person is already in the system
	 */
	public void addFaculty(String firstName, String lastName,
							FacultyType type, Building bldg, int room, String email) 
							throws DuplicatePersonException {
		int hash = lastName.hashCode();
		//throw exist exception
		if(existFaculty(firstName,lastName) == true) throw 
		new DuplicatePersonException();

		Faculty newFaculty = new Faculty(firstName, lastName);
		newFaculty.setType(type);
		newFaculty.setBuilding(bldg);
		newFaculty.setRoom(room);
		newFaculty.setEmail(email);
		facultyList.put(hash, newFaculty);
	}
	
	/**
	 * Adds a subject to the subject list collection.
	 * (hint: use a Pair instead of creating a class)
	 * 
	 * @param code	The subject code
	 * @param desc	The subject description
	 * 
	 * @throws DuplicateSubjectException The subject is already in the system
	 */
	public void addSubject(SubjectCode code, String desc) 
							throws DuplicateSubjectException {
		int hash = code.hashCode();
		//throw exist exception
		if(existSubject(code) == true) throw 
		new DuplicateSubjectException();
		Pair<SubjectCode, String> subject = new Pair<SubjectCode, String>(code, desc);
		subjectList.put(hash, subject);
	}
	
	/**
	 * Adds a course to the course list collection.
	 * 
	 * @param code		The subject code of the course
	 * @param num		The course number of the course
	 * @param name		The course name
	 * @param creditNum	The number of the credits of the course
	 * @throws DuplicateCourseException	The course is already in the system 
	 */
	public void addCourse(SubjectCode code, int num, String name, 
							int creditNum) throws DuplicateCourseException {
		if(existCourse(code, num) == true) throw 
		new DuplicateCourseException();
		int hash = code.hashCode() + num;
		Course course = new Course(code, num, name, creditNum);
		courseList.put(hash, course);
	}
	
	/**
	 * Adds a prerequisite to an existing course in the course
	 * list collection.
	 * 
	 * @param code			The subject code of the course
	 * @param num			The course number of the course
	 * @param prereqCode	The subject code of the prerequisite
	 * 						to add to the course
	 * @param prereqNum		The course number of the prerequisite
	 * 						to add to the course
	 * @throws CourseNotFoundException The course was not found in the system
	 */
	public void addPrerequisite(SubjectCode code, int num, 
							SubjectCode prereqCode, int prereqNum) 
							throws CourseNotFoundException {
		if(findCourse(code, num) == null) throw new CourseNotFoundException();
		if(findCourse(prereqCode, prereqNum) == null) throw new CourseNotFoundException();
		Course course = findCourse(code, num);
		Course prereq = findCourse(prereqCode, prereqNum);
		course.setPrerequisite(prereq);
	}
	
	
	
	/**
	 * Adds a section to the section list collection.
	 * 
	 * @param code		 The subject code of the course
	 * @param courseNum	 The course number of the course
	 * @param sectionNum The section number for the course
	 * @param firstName	 The first name for the faculty teaching the course
	 * @param lastName	 The last name for the faculty teaching the course
	 * @param quarter	 The quarter that the course section is held 
	 * @param year		 The year that the course section is held
	 * @param cap		 The capacity of the course section
	 * @param bldg		 The building that the course section is held
	 * @param room		 The room that the course section is held
	 * @throws CourseNotFoundException The course was not found in the system
	 * @throws PersonNotFoundException The person was not found in the system
	 * @throws DuplicateSectionException The section is already in the system
	 */
	public void addSection(SubjectCode code, int courseNum, int sectionNum,
							String firstName, String lastName, Quarter quarter, int year, 
							int cap, Building bldg, int room) 
							throws CourseNotFoundException, PersonNotFoundException, DuplicateCourseException, SectionNotFoundException {
		
		int hash = code.hashCode()+courseNum;
		if(findCourse(code, courseNum) == null) throw new CourseNotFoundException();
		if(findFaculty(lastName) == null) throw new PersonNotFoundException();
		if(existCourse(code, courseNum)) throw new DuplicateCourseException();
		if(existSection(code, courseNum)) throw new SectionNotFoundException();
		Course course = findCourse(code, courseNum);
		Faculty instructor = findFaculty(lastName);
		Section newSection = new Section(course, sectionNum, instructor, quarter, 
				year, cap, bldg, room);
		sectionList.put(hash, newSection);
	
		//added message
		//System.out.println(code + " " + courseNum + " has section " + sectionNum);
	}
	
	private boolean existSection(SubjectCode code, int courseNum) {
		return (sectionList.containsKey(code.hashCode()));
	}

	// student list, faculty list, subject list, course list, section list
	// note that there is not list for prerequisites - these should be included 
	// as part of the course list
	private HashMap<Integer, Student> studentList;
	private HashMap<Integer, Faculty> facultyList;
	private HashMap<Integer, Pair<SubjectCode, String>> subjectList;
	private HashMap <Integer, Course> courseList;
	private HashMap <Integer, Section> sectionList;
	
	private boolean existStudent(String firstName, String lastName) {
		int hash = firstName.hashCode() + lastName.hashCode();
		return studentList.containsKey(hash) && 
				studentList.get(hash).getFirstName() == firstName &&
				studentList.get(hash).getLastName() == lastName;
		
	}
	
	
	/**
	 * check duplicate faculty in hashMap
	 * @param firstName	Faculty's first name
	 * @param lastName	Faculty's last name
	 * @return true/false
	 */
	private boolean existFaculty(String firstName, String lastName) {
		return facultyList.containsKey(firstName.hashCode() + lastName.hashCode());
	}
	
	/**
	 * check duplicate subject in hashMap
	 * @param s Subject Code
	 * @return true/false
	 */
	private boolean existSubject(SubjectCode s) {
		return subjectList.containsKey(s.hashCode());
	}

	/**
	 * check duplicate course in hashMap
	 * @param c SubjectCode
	 * @param n SubjectNumber
	 * @return true/false
	 */
	private boolean existCourse(SubjectCode c, int n) {
		return courseList.containsKey(c.hashCode()) && courseList.get(c.hashCode()).getCode() == c;
	}
	
	/**
	 * find a certain course
	 * @param c target subject code
	 * @param n target subject number
	 * @return target
	 */
	private Course findCourse(SubjectCode c, int n) {
		return courseList.get(c.hashCode()+n);
	}
	
	/**
	 * find a certain course
	 * @param lastName target last name
	 * @return target
	 */
	private Faculty findFaculty(String lastName) {
		return facultyList.get(lastName.hashCode());
	}
	
	/**
	 * helper method to return specific list
	 * @param s target code
	 * @return
	 */
	public HashMap<Integer, ?> returnList(String s){
		if(s.equals("faculty")) return facultyList;
		if(s.equals("student")) return studentList;
		if(s.equals("subject")) return subjectList;
		if(s.equals("course")) return courseList;
		if(s.equals("section")) return sectionList;
		else return null;
	}

}
	