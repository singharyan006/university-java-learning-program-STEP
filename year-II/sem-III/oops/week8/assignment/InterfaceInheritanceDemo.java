// Base interface Animal
interface Animal {
    void eat();
}

// Extended interface Pet
interface Pet extends Animal {
    void play();
}

// Dog class implementing Pet (and indirectly Animal)
class Dog implements Pet {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating dog food.");
    }

    @Override
    public void play() {
        System.out.println(name + " is playing fetch.");
    }

    public void showDetails() {
        System.out.println("Dog Name: " + name);
        eat();
        play();
    }
}

// Main class to test the implementation
public class InterfaceInheritanceDemo {
    public static void main(String[] args) {
        Dog myDog = new Dog("Bruno");
        myDog.showDetails();
    }
}
