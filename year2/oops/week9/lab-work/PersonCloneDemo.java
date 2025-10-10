class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }

    // Deep copy constructor
    public Address(Address other) {
        this.city = other.city;
    }

    @Override
    public String toString() {
        return "Address [City: " + city + "]";
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow clone
    public Person shallowClone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    // Deep clone
    public Person deepClone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = new Address(this.address); // new Address object
        return cloned;
    }

    @Override
    public String toString() {
        return "Person [Name: " + name + ", " + address + "]";
    }
}

public class PersonCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Chennai");
        Person original = new Person("Ravi", addr);

        Person shallow = original.shallowClone();
        Person deep = original.deepClone();

        System.out.println("Before modification:");
        System.out.println("Original: " + original);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);

        // Modify original's address
        original.address.city = "Bangalore";

        System.out.println("\nAfter modifying original's address:");
        System.out.println("Original: " + original);
        System.out.println("Shallow Clone: " + shallow); // reflects change
        System.out.println("Deep Clone: " + deep);       // remains unchanged
    }
}
