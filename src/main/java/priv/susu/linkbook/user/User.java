package priv.susu.linkbook.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 1404269800594140447L;

	public static final int USER_GENDER_MALE = 1;
	public static final int USER_GENDER_FEMALE = 2;

	public static final User NULL;
	static {
		NULL = new User();
		NULL.username = "用户不存在";
	}

	private long id = -1;
	private String username = "";
	private String firstName = "";
	private String lastName = "";
	private String password = "";
	private String avatar = "";
	private int gender = USER_GENDER_MALE;
	private Date birthDate = null;
	private String hometown = "";
	private String residence = "";
	private Date registerTime = null;
	private Date lastLoginTime = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		checkNullObject();
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		checkNullObject();
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		checkNullObject();
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		checkNullObject();
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		checkNullObject();
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		checkNullObject();
		this.avatar = avatar;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		checkNullObject();
		this.hometown = hometown;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		checkNullObject();
		this.residence = residence;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		checkNullObject();
		this.lastLoginTime = lastLoginTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		checkNullObject();
		this.registerTime = registerTime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		checkNullObject();
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		checkNullObject();
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", avatar=" + avatar + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", hometown=" + hometown + ", residence=" + residence + ", registerTime=" + registerTime + ", lastLoginTime=" + lastLoginTime + "]";
	}

	private void checkNullObject() {
		if (this == NULL) {
			throw new UnsupportedOperationException("Could not modify NULL object.");
		}
	}

}
