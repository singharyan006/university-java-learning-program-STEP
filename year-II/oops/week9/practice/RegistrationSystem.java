// File: RegistrationSystem.java

class ContactInfo implements Cloneable {
    String email;
    String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy is fine here
    }

    @Override
    public String toString() {
        return "Email: " + email + ", Phone: " + phone;
    }
}

class Student implements Cloneable {
    String id;
    String name;
    ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Shallow copy: contact is shared
    public Student shallowClone() throws CloneNotSupportedException {
        return (Student) super.clone();
    }

    // Deep copy: contact is cloned separately
    public Student deepClone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.contact = (ContactInfo) contact.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "Student: " + id + ", Name: " + name + ", Contact: [" + contact + "]";
    }
}

public class RegistrationSystem {
    public static void main(String[] args) throws CloneNotSupportedException {
        ContactInfo contact = new ContactInfo("ritika@example.com", "9876543210");
        Student original = new Student("ST101", "Ritika", contact);

        Student shallow = original.shallowClone();
        Student deep = original.deepClone();

        System.out.println("Before modification:");
        System.out.println("Original: " + original);
        System.out.println("Shallow:  " + shallow);
        System.out.println("Deep:     " + deep);

        // Modify contact info
        contact.email = "updated@example.com";
        contact.phone = "0000000000";

        System.out.println("\nAfter modifying original contact:");
        System.out.println("Original: " + original);
        System.out.println("Shallow:  " + shallow); // reflects changes
        System.out.println("Deep:     " + deep);    // remains unchanged
    }
}
