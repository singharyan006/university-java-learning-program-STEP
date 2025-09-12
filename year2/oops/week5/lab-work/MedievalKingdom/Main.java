/**
 * Main demonstration class for the Medieval Kingdom Management System
 * Shows all features including access modifiers, immutable objects, instanceof, and constructor overloading
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🏰 MEDIEVAL KINGDOM MANAGEMENT SYSTEM DEMO 🏰");
        System.out.println("=" .repeat(60));
        
        // === 1. IMMUTABLE KINGDOM CONFIG DEMONSTRATION ===
        System.out.println("\n1. IMMUTABLE KINGDOM CONFIG");
        System.out.println("-".repeat(30));
        
        // Default kingdom
        KingdomConfig defaultKingdom = KingdomConfig.createDefaultKingdom();
        System.out.println("Default Kingdom: " + defaultKingdom.getKingdomName());
        
        // Template kingdoms
        KingdomConfig magicalKingdom = KingdomConfig.createFromTemplate("magical");
        KingdomConfig defensiveKingdom = KingdomConfig.createFromTemplate("defensive");
        System.out.println("Magical Kingdom: " + magicalKingdom.getKingdomName());
        System.out.println("Defensive Kingdom: " + defensiveKingdom.getKingdomName());
        
        // === 2. CONSTRUCTOR CHAINING DEMONSTRATION ===
        System.out.println("\n2. CONSTRUCTOR CHAINING EXAMPLES");
        System.out.println("-".repeat(35));
        
        // MagicalStructure chaining
        MagicalStructure basic = new MagicalStructure("Basic Tower", "Forest");
        MagicalStructure withPower = new MagicalStructure("Power Tower", "Mountain", 500);
        MagicalStructure complete = new MagicalStructure("Complete Tower", "Castle", 800, true);
        
        System.out.println("Basic constructor: " + basic.getStructureInfo());
        System.out.println("With power: " + withPower.getStructureInfo());
        System.out.println("Complete: " + complete.getStructureInfo());
        
        // === 3. WIZARD TOWER VARIATIONS ===
        System.out.println("\n3. WIZARD TOWER CONSTRUCTOR VARIATIONS");
        System.out.println("-".repeat(40));
        
        WizardTower emptyTower = new WizardTower("Empty Spire", "Wasteland");
        WizardTower apprenticeTower = WizardTower.createApprenticeTower("Learning Hall", "Academy", "Young Mage");
        WizardTower battleTower = WizardTower.createBattleMageTower("War Spire", "Battlefield", "Battle Mage");
        WizardTower archmageTower = WizardTower.createArchmageTower("Grand Spire", "Capital", "Archmage Supreme");
        
        System.out.println("Empty Tower: " + emptyTower.getStructureInfo());
        System.out.println("Apprentice Tower: " + apprenticeTower.getStructureInfo());
        System.out.println("Battle Tower: " + battleTower.getStructureInfo());
        System.out.println("Archmage Tower: " + archmageTower.getStructureInfo());
        
        // === 4. CASTLE VARIATIONS ===
        System.out.println("\n4. ENCHANTED CASTLE VARIATIONS");
        System.out.println("-".repeat(35));
        
        EnchantedCastle simpleFort = new EnchantedCastle("Border Post", "North Border");
        EnchantedCastle royalCastle = new EnchantedCastle("Royal Palace", "Capital", "King Arthur");
        EnchantedCastle mountainFortress = EnchantedCastle.createMountainFortress("Iron Hold", "High Peak", "General Stone");
        
        System.out.println("Simple Fort: " + simpleFort.getStructureInfo());
        System.out.println("Royal Castle: " + royalCastle.getStructureInfo());
        System.out.println("Mountain Fortress: " + mountainFortress.getStructureInfo());
        
        // === 5. LIBRARY COLLECTIONS ===
        System.out.println("\n5. MYSTIC LIBRARY COLLECTIONS");
        System.out.println("-".repeat(32));
        
        MysticLibrary basicLibrary = new MysticLibrary("Village Library", "Small Town");
        MysticLibrary royalLibrary = MysticLibrary.createRoyalLibrary("Royal Archives", "Palace", "Royal Librarian");
        MysticLibrary ancientRepository = MysticLibrary.createAncientRepository("Ancient Vault", "Lost City", "Eternal Keeper");
        
        System.out.println("Basic Library: " + basicLibrary.getStructureInfo());
        System.out.println("Royal Library: " + royalLibrary.getStructureInfo());
        System.out.println("Ancient Repository: " + ancientRepository.getStructureInfo());
        
        // === 6. DRAGON LAIR TYPES ===
        System.out.println("\n6. DRAGON LAIR TYPES");
        System.out.println("-".repeat(22));
        
        DragonLair basicLair = new DragonLair("Abandoned Cave", "Dark Forest");
        DragonLair fireLair = new DragonLair("Volcano Lair", "Fire Mountain", "Flameheart");
        DragonLair ancientLair = DragonLair.createAncientLair("Ancient Cavern", "Primordial Peak", "Worldshaker");
        DragonLair iceLair = DragonLair.createElementalLair("Frost Cavern", "Frozen Wastes", "Iceclaw", "Ice");
        
        System.out.println("Basic Lair: " + basicLair.getStructureInfo());
        System.out.println("Fire Lair: " + fireLair.getStructureInfo());
        System.out.println("Ancient Lair: " + ancientLair.getStructureInfo());
        System.out.println("Ice Lair: " + iceLair.getStructureInfo());
        
        // === 7. KINGDOM MANAGER WITH INSTANCEOF ===
        System.out.println("\n7. KINGDOM MANAGER DEMONSTRATION");
        System.out.println("-".repeat(35));
        
        KingdomManager kingdom = new KingdomManager(defaultKingdom);
        
        // Add various structures
        kingdom.addStructure(archmageTower);
        kingdom.addStructure(royalCastle);
        kingdom.addStructure(royalLibrary);
        kingdom.addStructure(fireLair);
        
        kingdom.displayKingdomStatus();
        
        // === 8. STRUCTURE INTERACTIONS (instanceof) ===
        System.out.println("\n8. STRUCTURE INTERACTION TESTING");
        System.out.println("-".repeat(35));
        
        System.out.println("Can Wizard Tower interact with Library? " + 
            KingdomManager.canStructuresInteract(archmageTower, royalLibrary));
        System.out.println("Can Dragon Lair interact with Castle? " + 
            KingdomManager.canStructuresInteract(fireLair, royalCastle));
        System.out.println("Can Library interact with Dragon Lair? " + 
            KingdomManager.canStructuresInteract(royalLibrary, fireLair));
        
        // === 9. MAGIC BATTLE SYSTEM ===
        System.out.println("\n9. MAGICAL BATTLE DEMONSTRATIONS");
        System.out.println("-".repeat(35));
        
        String battle1 = KingdomManager.performMagicBattle(archmageTower, fireLair);
        System.out.println(battle1);
        System.out.println();
        
        String battle2 = KingdomManager.performMagicBattle(mountainFortress, battleTower);
        System.out.println(battle2);
        System.out.println();
        
        // === 10. KINGDOM POWER CALCULATION ===
        System.out.println("\n10. KINGDOM POWER CALCULATION");
        System.out.println("-".repeat(32));
        
        Object[] allStructures = {archmageTower, royalCastle, royalLibrary, fireLair, mountainFortress};
        int totalPower = KingdomManager.calculateKingdomPower(allStructures);
        System.out.println("Total Kingdom Power: " + totalPower);
        
        // === 11. SPECIALIZED BEHAVIORS ===
        System.out.println("\n11. SPECIALIZED STRUCTURE BEHAVIORS");
        System.out.println("-".repeat(38));
        
        // Wizard Tower behaviors
        System.out.println("=== Wizard Tower Behaviors ===");
        archmageTower.castSpell("Meteor");
        archmageTower.learnSpell("Ultimate Power");
        archmageTower.practiceSpells();
        
        System.out.println();
        
        // Castle behaviors
        System.out.println("=== Castle Behaviors ===");
        royalCastle.raiseDrawbridge();
        royalCastle.trainGarrison();
        royalCastle.defendAgainstAttack(500);
        
        System.out.println();
        
        // Library behaviors
        System.out.println("=== Library Behaviors ===");
        royalLibrary.studyBook("Royal Magic Protocols");
        royalLibrary.researchSubject("Magic Theory");
        royalLibrary.organizeLibrary();
        
        System.out.println();
        
        // Dragon Lair behaviors
        System.out.println("=== Dragon Lair Behaviors ===");
        fireLair.addTreasure("Fire Rubies", 10, 5000);
        fireLair.sortTreasure();
        fireLair.defendHoard(800);
        
        // === 12. KINGDOM-WIDE OPERATIONS ===
        System.out.println("\n12. KINGDOM-WIDE OPERATIONS");
        System.out.println("-".repeat(30));
        
        kingdom.performKingdomwideOperation("ENHANCE_MAGIC");
        System.out.println();
        kingdom.performKingdomwideOperation("DEFENSE_DRILL");
        System.out.println();
        
        // === 13. FINAL KINGDOM STATUS ===
        System.out.println("\n13. FINAL KINGDOM STATUS");
        System.out.println("-".repeat(27));
        kingdom.displayKingdomStatus();
        
        // === 14. IMMUTABILITY DEMONSTRATION ===
        System.out.println("\n14. IMMUTABILITY DEMONSTRATION");
        System.out.println("-".repeat(33));
        
        // Show that KingdomConfig is truly immutable
        String[] originalTypes = defaultKingdom.getAllowedStructureTypes();
        originalTypes[0] = "ModifiedType"; // Try to modify the returned array
        
        String[] stillOriginal = defaultKingdom.getAllowedStructureTypes();
        System.out.println("Original config unchanged: " + stillOriginal[0]); // Still "WizardTower"
        
        System.out.println("\n🎉 MEDIEVAL KINGDOM MANAGEMENT SYSTEM DEMO COMPLETE! 🎉");
    }
}