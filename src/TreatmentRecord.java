/**
 * Represents a completed treatment record for a patient.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionDate;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails, String completionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionDate = completionDate;
    }

    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public String getTreatmentDetails() { return treatmentDetails; }
    public String getCompletionDate() { return completionDate; }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Treatment: " + treatmentDetails +
                " | Completed On: " + completionDate;
    }
}