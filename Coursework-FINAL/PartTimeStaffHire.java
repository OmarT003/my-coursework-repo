
/**
 * @author Omar Tamimi
 * @version 24.2.2025
 * Subclass which extends StaffHire
 */
public class PartTimeStaffHire extends StaffHire
{
    //Four variables for PartTimeStaffHire
    private int workingHour;
    private double wagesPerHour;
    private String shifts;
    private boolean terminated;

    /**
     * Constructor accepts 11 parameters for objects of class PartTimeStaffHire
     * including working hours, wages per hour and shift, also sets terminated 
     * status to false.
     */
    public PartTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName,
    String joiningDate, String qualification, String appointedBy, boolean joined,
    int workingHour, double wagesPerHour, String shifts){
        //A call is made to the superclass constructor with 8 parameters
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
        //attributes are assigned the corresponding parameter values
        this.workingHour = workingHour;
        this.wagesPerHour = wagesPerHour;
        this.shifts = shifts;
        // terminated status is assigned to false as instructed in the guidelines
        this.terminated = false;
    }

    /**
     * Each new attribute has a corresponding accessor method. 
     *
     */
    //Attribute 1- Returns working hours per day
    //Read only access setter method 1
    public int getWorkingHour(){
        return workingHour;   
    }
    //Getter method 1- updates working hours
    public void setWorkingHour(int workingHour){
        this.workingHour = workingHour;
    }
    //Attribute 2- Returns wages per hour
    //Read only access setter method 2
    public double getWagesPerHour(){
        return wagesPerHour;   
    }
    //Getter method 2- updates wages per hour
    public void setWagesPerHour(int workingHour){
        wagesPerHour = wagesPerHour;
    }
    //Attribute 3- Returns the shift type (Morning, afternoon, etc..)
    //Read only access setter method 3
    public String getShifts(){
        return shifts;   
    }
    //Getter method 3- Updates shift if staff joins.
    public void setShifts(String shifts){
        //if statement assigns the new value to the shifts attribute only if the staff has joined.
        if (isJoined()){
            shifts = shifts;
        } 
        else {
            System.out.println("Staff member has not joined.");
        }
    }

    //Termination method for PartTimeStaff- Returns true if terminated, otherwise false.
    public boolean isTerminated(){
        return terminated;
    }

    //if the staff is already terminated, a suitable message is displayed, else, set details to empty.
    public void terminate(){
        if (!terminated){
            setStaffName("");
            setJoiningDate("");
            setQualification("");
            setAppointedBy("");
            setJoined(false);
            terminated = true;
        }
        else {
            System.out.println("Staff member already terminated");
        }
    }

    //A method to display the details of the part time staff including wage, hours
    //shifts and daily wage.
    @Override
    public void display(){
        //Super method also calls display method from Superclass StaffHire.
        super.display();
        System.out.println("Working Hours are: " + workingHour);
        System.out.println("Wages per Hour is: " + wagesPerHour);
        System.out.println("Shifts: " + shifts);
        System.out.println("Terminated?: " + (terminated ? "Yes" : "No"));
        System.out.println("Daily Income is: " + (wagesPerHour * workingHour));
    }
    //recall the toString() method in StaffHire superclass,
    //later added method to clear details if staff is terminated
    @Override
public String toString() {
    if (terminated) {
        //If the staff is terminated then show message.
         return "Vacancy Number: " + getVacancyNumber() +
               "\nDesignation: " + getDesignation() +
               "\nJob Type: " + getJobType() +
               "\nStaff Name: N/A" +
               "\nJoining Date: N/A" +
               "\nQualification: N/A" +
               "\nAppointed By: N/A" +
               "\nJoined: No" +
               "\nStatus: Terminated" +
               "\nStaff details have been cleared.";
    } else {
        //If not terminated then show staff details
        return super.toString() +
               "\nWages Per Hour: " + wagesPerHour +
               "\nWorking Hours: " + workingHour +
               "\nShifts: " + shifts +
               "\nTerminated: No" +
               "\nDaily Income: " + (wagesPerHour * workingHour);
    }
}

}

