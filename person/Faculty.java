/*
 * Edward Lam 
 * CPSC 5011, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package person;

import enums.Building;
import enums.FacultyType;
import enums.PersonStatus;

/**
 * The Faculty class holds information about a faculty member.
 * 
 * - first name: first name of the student
 * - last name: last name of the student
 * - suid: Seattle U identification number
 * - status: the status of the faculty (see PersonStatus enum)
 * - faculty type: the type of faculty (see FacultyType enum)
 * - office: includes building (i.e. ENGR) and room number (i.e 504)
 * - email: the school (i.e. SU) email address
 * 
 * @author 
 */
public class Faculty extends Person{
	
	/**
	 * 
	 * @param firstName	The first name of the faculty
	 * @param lastName	The last name of the faculty
	 */
	public Faculty(String firstName, String lastName) {
		super(firstName,lastName);
		this.firstName = firstName;
		this.lastName = lastName;
	
	}

	/**
	 * set a faculty's status
	 * @param id faculty's status
	 */
	public void setStatus(PersonStatus s) {
		this.status = s;
	}
	
	/**
	 * set a faculty's type
	 * @param id faculty's type
	 */
	public void setType(FacultyType t) {
		this.type = t;
	}
	
	/**
	 * set a faculty's building
	 * @param id faculty's building
	 */
	public void setBuilding(Building b) {
		this.building = b;
	}
	
	/**
	 * set a faculty's room number
	 * @param id faculty's room number
	 */
	public void setRoom(int room) {
		this.room = room;
	}

	
	
	@Override
	public String toString() {
		return String.format("%-12s %-12s %-15d %-15s %-4s %-10d %-1s",
				lastName, firstName, suid, type, building, room, email + "\n");
	}


	
	// first name, last name, SUID, status, faculty type, office (see building), email
	int room;
	PersonStatus status;
	FacultyType type;
	Building building;
}