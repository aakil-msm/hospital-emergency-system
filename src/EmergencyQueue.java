/**
 * Queue (FIFO) implemented with a custom linked structure.
 * Manages patients waiting in the emergency unit.
 */
public class EmergencyQueue {

    private class QueueNode {
        Patient patient;
        QueueNode next;
        QueueNode(Patient patient) { this.patient = patient; }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    /** Adds a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient \"" + patient.getName() + "\" (ID: " + patient.getPatientId()
                + ") added to the emergency queue.");
    }

    /** Removes and returns the patient at the front of the queue (next to be treated). */
    public Patient dequeue() {
        if (front == null) {
            System.out.println("Emergency queue is empty. No patient is waiting for treatment.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return patient;
    }

    /** Displays all patients currently waiting, in FIFO order. */
    public void displayQueue() {
        if (front == null) {
            System.out.println("Emergency queue is empty. No patients are waiting.");
            return;
        }
        System.out.println("----- Patients Waiting in Emergency Queue -----");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() { return front == null; }
    public int getSize() { return size; }
}