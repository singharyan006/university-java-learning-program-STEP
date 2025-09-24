public class GameCharactersDemo {
    public static void main(String[] args) {
        Character[] army = new Character[3];
        army[0] = new Warrior("Thorin", "Axe", 80);
        army[1] = new Mage("Gandalf", 120);
        army[2] = new Archer("Legolas", 60);

        System.out.println("Battle Start:\n");
        for (Character c : army) {
            c.attack();
            System.out.println();
        }

        System.out.println("Special actions:");
        ((Warrior) army[0]).defend();
        ((Mage) army[1]).castSpell("Fireball");
        ((Archer) army[2]).longRangeShot();
    }
}

abstract class Character {
    protected String name;

    public Character(String name) {
        this.name = name;
    }

    public abstract void attack();
}

class Warrior extends Character {
    private String weapon;
    private int defense;

    public Warrior(String name, String weapon, int defense) {
        super(name);
        this.weapon = weapon;
        this.defense = defense;
    }

    @Override
    public void attack() {
        System.out.println(name + " swings " + weapon + " dealing heavy melee damage!");
    }

    public void defend() {
        System.out.println(name + " raises shield, defense increased by " + defense);
    }
}

class Mage extends Character {
    private int mana;

    public Mage(String name, int mana) {
        super(name);
        this.mana = mana;
    }

    @Override
    public void attack() {
        System.out.println(name + " casts a magic bolt, consuming 10 mana.");
        mana -= 10;
        System.out.println(name + " remaining mana: " + mana);
    }

    public void castSpell(String spell) {
        System.out.println(name + " casts " + spell + " with spectacular effects!");
    }
}

class Archer extends Character {
    private int range;

    public Archer(String name, int range) {
        super(name);
        this.range = range;
    }

    @Override
    public void attack() {
        System.out.println(name + " fires a precise arrow dealing long-range damage (range " + range + ")");
    }

    public void longRangeShot() {
        System.out.println(name + " performs a long-range shot at distance " + range + " meters!");
    }
}
