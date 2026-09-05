/**
 * Stack (LIFO) implemented with a custom linked structure.
 * Stores completed treatment records; most recent treatment is on top.
 */
public class TreatmentStack {

    private class StackNode {
        TreatmentRecord record;
        StackNode next;
        StackNode(TreatmentRecord record) { this.record = record; }
    }

    private StackNode top;
    private int size;

    /** Pushes a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for Patient ID " + record.getPatientId()
                + " added to treatment history.");
    }

    /** Removes and returns the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (top == null) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** Displays all treatment records, most recent first. */
    public void displayStack() {
        if (top == null) {
            System.out.println("Treatment history is empty.");
            return;
        }
        System.out.println("----- Treatment History (Most Recent First) -----");
        StackNode current = top;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() { return top == null; }
    public int getSize() { return size; }
}