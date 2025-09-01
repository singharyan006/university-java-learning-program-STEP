class Animal {}

class Dog extends Animal {}

class Cat extends Animal {}

public class TestInstanceof {
    public static void main(String[] args) {
        Animal[] animals = {
            new Dog(),
            new Cat(),
            new Dog(),
            new Animal(),
            new Cat(),
            new Dog()
        };

        int dogCount = 0;
        int catCount = 0;

        for (Animal a : animals) {
            if (a instanceof Dog) {
                dogCount++;
            } else if (a instanceof Cat) {
                catCount++;
            }
        }

        System.out.println("Number of Dog objects: " + dogCount);
        System.out.println("Number of Cat objects: " + catCount);
    }
}
