package person;

public class Person {

	String firstName, lastName, email;
	int suid;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * set a student's id
	 */
	public void setSUID(int id) {
		this.suid = id;
	}
	
	public int getID() {
		return suid;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	/**
	 * get a student's first name
	 * @return student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * get a student's last name
	 * @return student's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * set a student's first name
	 * @return student's first name
	 */
	public void setFirstName(String n) {
		this.firstName = n;
	}
	
	/**
	 * set a student's last name
	 * @return student's last name
	 */
	public void setLastName(String n) {
		this.lastName = n;
	}
}
