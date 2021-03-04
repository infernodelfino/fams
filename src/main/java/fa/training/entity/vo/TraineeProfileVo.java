package fa.training.entity.vo;

public class TraineeProfileVo {
	private int id;
	private String name;
	private int gender;
	private String dateOfBirth;
	private String universityName;
	private String facultyName;
	private String phone;
	private String email;
	private int salaryPaid;
	private String tPBAccount;
	private String allowanceGroup;

	public TraineeProfileVo() {
		super();
	}

	public TraineeProfileVo(int id, String name, int gender, String dateOfBirth, String universityName,
			String facultyName, String phone, String email, int salaryPaid, String tPBAccount, String allowanceGroup) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.universityName = universityName;
		this.facultyName = facultyName;
		this.phone = phone;
		this.email = email;
		this.salaryPaid = salaryPaid;
		this.tPBAccount = tPBAccount;
		this.allowanceGroup = allowanceGroup;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalaryPaid() {
		return salaryPaid;
	}

	public void setSalaryPaid(int salaryPaid) {
		this.salaryPaid = salaryPaid;
	}

	public String gettPBAccount() {
		return tPBAccount;
	}

	public void settPBAccount(String tPBAccount) {
		this.tPBAccount = tPBAccount;
	}

	public String getAllowanceGroup() {
		return allowanceGroup;
	}

	public void setAllowanceGroup(String allowanceGroup) {
		this.allowanceGroup = allowanceGroup;
	}

}
