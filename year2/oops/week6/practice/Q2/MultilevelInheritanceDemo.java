class Animal {
    void eat() {
        System.out.println("Animal eats.");
    }
}

class Mammal extends Animal {
    void walk() {
        System.out.println("Mammal walks.");
    }
}

class Dog extends Mammal {
    void bark() {
        System.out.println("Dog barks.");
    }
}

public class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();   // from Animal
        dog.walk();  // from Mammal
        dog.bark();  // from Dog
    }
}