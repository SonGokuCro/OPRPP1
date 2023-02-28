package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Class <code>StudentRecord</code> is used for structured storage
 * of student data. Representing record for a single student.
 */
public class StudentRecord {
    private String jmbag;
    private String lastName;
    private String firstName;
    private String finalGrade;

    public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
        this.jmbag = jmbag;
        this.lastName = lastName;
        this.firstName = firstName;
        this.finalGrade = finalGrade;
    }

    public String getJmbag() {
        return jmbag;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    /**
     * Method used for comparing two students.
     * @param o student that we use for comparison
     * @return <code>true</code> if two students have same jmbag, else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return jmbag.equals(that.jmbag);
    }

    /**
     * Used for calculating hash code of <code>jmbag</code>.
     * @return hashValue of given <code>jmbag</code> string
     */
    @Override
    public int hashCode() {
        return Objects.hash(jmbag);
    }
}
