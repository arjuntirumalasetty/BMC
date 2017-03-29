package businessojects;

/**
 * Created by Arjun on 3/29/2017.
 */

public class CoachDetails {
    private String coachName;
    private int  age;
    private int experienceInYears;
    private int experienceInMonths;
    private String coachPhoneNo;
    private String coachEmail;

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public int getExperienceInMonths() {
        return experienceInMonths;
    }

    public void setExperienceInMonths(int experienceInMonths) {
        this.experienceInMonths = experienceInMonths;
    }

    public String getCoachPhoneNo() {
        return coachPhoneNo;
    }

    public void setCoachPhoneNo(String coachPhoneNo) {
        this.coachPhoneNo = coachPhoneNo;
    }

    public String getCoachEmail() {
        return coachEmail;
    }

    public void setCoachEmail(String coachEmail) {
        this.coachEmail = coachEmail;
    }
}
