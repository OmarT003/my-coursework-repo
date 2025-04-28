
/**
 * @author Omar Tamimi
 * @version 29.4.2025
 */
public class StaffHire
{
    // attributes for StaffHire superclass
    private int vacancyNumber;
    private String designation;
    private String jobType;
    private String staffName;
    private String joiningDate;
    private String qualification;
    private String appointedBy;
    private boolean joined;

    /**
     * Constructor for objects of class StaffHire
     */
    //Initialising the vacancy numver, designation, job type, staff name, joining date,
    //qualification,appointed by and joined. 
    public StaffHire(int vacancyNum, String Thedesignation,String Thejobtype,String Thestaffname,
    String Thejoindate, String Thequalification, String appointedbyName, boolean joined)
    {
        // initialise instance variables
        this.vacancyNumber = vacancyNum;
        this.designation = Thedesignation;
        this.jobType = Thejobtype;
        this.staffName = Thestaffname;
        this.joiningDate = Thejoindate;
        this.qualification = Thequalification;
        this.appointedBy = appointedbyName;
        this.joined = joined;
    }

    /**
     * Getters and setter methods for all attributes.
     * 
     */   
    //Attribute 1- Returns the vacancy number of the job.
    //Read only access setter method 1
    public int getVacancyNumber(){
        return vacancyNumber;
    }
    //Write only access getter method 1
    public void setVacancyNumber(int vacancyNumber)
    {
        vacancyNumber = vacancyNumber;
    }
    //Attribute 2- Returns job designation.
    //Read only access setter method 2
    public String getDesignation(){
        return designation;
    }
    //Write only access getter method 2
    public void setDesignation(String designation)
    {
        designation = designation;
    }
    //Attribute 3- Returns job type (full or part time).
    //Read only access setter method 3
    public String getJobType(){
        return jobType;
    }
    //Write only access getter method 3
    public void setJobType(String jobType)
    {
        jobType = jobType;
    }
    //Attribute 4- Returns name of staff member.
    //Read only access setter method 4
    public String getStaffName(){
        return staffName;
    }
    //Write only access getter method 4
    public void setStaffName(String staffName)
    {
        staffName = staffName;
    }
    //Attribute 5- Returns the date when staff has joined.
    //Read only access setter method 5
    public String getJoiningDate(){
        return joiningDate;
    }
    //Write only access getter method 5
    public void setJoiningDate(String joiningDate)
    {
        joiningDate = joiningDate;
    }
    //Attribute 6- Returns staff qualification
    //Read only access setter method 6
    public String getQualification(){
        return qualification;
    }
    //Write only access getter method 6
    public void setQualification(String qualification)
    {
        qualification = qualification;
    }
    //Attribute 7- Returns name of person that appointed the staff member.
    //Read only access setter method 7
    public String getAppointedBy(){
        return appointedBy;
    }
    //Write only access getter method 7
    public void setAppointedBy(String appointedBy)
    {
        appointedBy = appointedBy;
    }
    //Attribute 8- Returns 'true' if staff joined, or 'false' if not.
    //Read only access setter method 8
    public boolean isJoined(){
        return joined;
    }
    //Write only access getter method 8
    public void setJoined(boolean joined)
    {
        joined = joined;
    }

    @Override
    //Returns a string containing hired staff details.
    public String toString() {
        return "Vacancy Number: " + vacancyNumber +
        "\nDesignation: " + designation +
        "\nJob Type: " + jobType +
        "\nStaff Name: " + (staffName.isEmpty() ? "N/A" : staffName) +
        "\nJoining Date: " + (joiningDate.isEmpty() ? "N/A" : joiningDate) +
        "\nQualification: " + (qualification.isEmpty() ? "N/A" : qualification) +
        "\nAppointed By: " + (appointedBy.isEmpty() ? "N/A" : appointedBy) +
        "\nJoined: " + (joined ? "Yes" : "No");
    }

    /**
     * Display method to print staff details such as vacancy number, designation, job type etc.
     */   
    public void display(){
        System.out.println("Vacancy Number is: " + vacancyNumber);
        System.out.println("Designation: " + designation);
        System.out.println("Job Type: " + jobType);
        System.out.println("Staff Name: " + staffName);
        System.out.println("Joining Date: " + joiningDate);
        System.out.println("Qualification: " + qualification);
        System.out.println("Appointed By: " + appointedBy);
        System.out.println("Joined: " + (joined ? "Yes" : "No"));
    }
}

