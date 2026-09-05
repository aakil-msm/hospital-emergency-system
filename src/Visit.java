/**
 * Represents a single hospital visit record.
 * Doubles as the node type for VisitLinkedList (singly linked list).
 */
public class Visit {
    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;
    private Visit next;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    public int getVisitId() { return visitId; }
    public String getVisitDate() { return visitDate; }
    public String getDoctorName() { return doctorName; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatment() { return treatment; }
    public Visit getNext() { return next; }
    public void setNext(Visit next) { this.next = next; }

    @Override
    public String toString() {
        return "Visit ID: " + visitId +
                " | Date: " + visitDate +
                " | Doctor: " + doctorName +
                " | Diagnosis: " + diagnosis +
                " | Treatment: " + treatment;
    }
}