import java.util.*;

/**
 * MysticLibrary class demonstrating knowledge management and book collections
 * Extends MagicalStructure with library-specific functionality
 */
public class MysticLibrary extends MagicalStructure {
    // === Final fields ===
    private final Map<String, String> bookCollection;
    
    // === Mutable state ===
    private int knowledgeLevel;
    private String currentLibrarian;
    private Set<String> availableSubjects;
    private int maxBooks;

    // === Constructor variations ===
    
    // Few books constructor
    public MysticLibrary(String name, String location) {
        super(name, location, 120, true);
        this.bookCollection = new HashMap<>();
        this.knowledgeLevel = 50;
        this.currentLibrarian = "None";
        this.availableSubjects = new HashSet<>();
        this.maxBooks = 100;
        initializeFewBooks();
    }
    
    // Moderate collection constructor
    public MysticLibrary(String name, String location, String librarian) {
        super(name, location, 200, true);
        this.bookCollection = new HashMap<>();
        this.knowledgeLevel = 150;
        setCurrentLibrarian(librarian);
        this.availableSubjects = new HashSet<>();
        this.maxBooks = 500;
        initializeModerateCollection();
    }
    
    // Ancient archives constructor
    public MysticLibrary(String name, String location, String librarian, int capacity) {
        super(name, location, 400, true);
        this.bookCollection = new HashMap<>();
        this.knowledgeLevel = 300;
        setCurrentLibrarian(librarian);
        this.availableSubjects = new HashSet<>();
        setMaxBooks(capacity);
        initializeAncientArchives();
    }
    
    // Custom library constructor
    public MysticLibrary(String name, String location, String librarian, 
                        int capacity, Map<String, String> initialBooks) {
        super(name, location, 300, true);
        this.bookCollection = new HashMap<>();
        this.knowledgeLevel = 200;
        setCurrentLibrarian(librarian);
        this.availableSubjects = new HashSet<>();
        setMaxBooks(capacity);
        
        if (initialBooks != null) {
            for (Map.Entry<String, String> entry : initialBooks.entrySet()) {
                addBook(entry.getKey(), entry.getValue());
            }
        }
    }

    // === Getters and Setters ===
    public Map<String, String> getBookCollection() {
        return new HashMap<>(bookCollection); // Defensive copy
    }
    
    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }
    
    public void setKnowledgeLevel(int knowledgeLevel) {
        if (knowledgeLevel < 0 || knowledgeLevel > 1000) {
            throw new IllegalArgumentException("Knowledge level must be between 0 and 1000");
        }
        this.knowledgeLevel = knowledgeLevel;
    }
    
    public String getCurrentLibrarian() {
        return currentLibrarian;
    }
    
    public void setCurrentLibrarian(String librarian) {
        if (librarian == null || librarian.trim().isEmpty()) {
            this.currentLibrarian = "None";
            setActive(false);
        } else {
            this.currentLibrarian = librarian.trim();
            setActive(true);
            setCurrentMaintainer(librarian);
        }
    }
    
    public Set<String> getAvailableSubjects() {
        return new HashSet<>(availableSubjects); // Defensive copy
    }
    
    public int getMaxBooks() {
        return maxBooks;
    }
    
    public void setMaxBooks(int maxBooks) {
        if (maxBooks < 10 || maxBooks > 10000) {
            throw new IllegalArgumentException("Max books must be between 10 and 10000");
        }
        this.maxBooks = maxBooks;
    }

    // === Book Management ===
    public boolean addBook(String title, String subject) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Book subject cannot be null or empty");
        }
        
        if (bookCollection.size() >= maxBooks) {
            System.out.println("Library at capacity! Cannot add more books.");
            return false;
        }
        
        String trimmedTitle = title.trim();
        String trimmedSubject = subject.trim();
        
        if (bookCollection.containsKey(trimmedTitle)) {
            System.out.println("Book '" + trimmedTitle + "' already exists in the library.");
            return false;
        }
        
        bookCollection.put(trimmedTitle, trimmedSubject);
        availableSubjects.add(trimmedSubject);
        increaseKnowledge(5);
        System.out.println("Added book: '" + trimmedTitle + "' (Subject: " + trimmedSubject + ")");
        return true;
    }
    
    public boolean removeBook(String title) {
        if (title == null) return false;
        
        String subject = bookCollection.remove(title.trim());
        if (subject != null) {
            System.out.println("Removed book: '" + title + "'");
            decreaseKnowledge(3);
            
            // Check if this was the last book of this subject
            if (!bookCollection.containsValue(subject)) {
                availableSubjects.remove(subject);
                System.out.println("No more books on " + subject + " - subject removed.");
            }
            return true;
        }
        return false;
    }
    
    public String findBook(String title) {
        return bookCollection.get(title);
    }
    
    public List<String> findBooksBySubject(String subject) {
        List<String> books = new ArrayList<>();
        for (Map.Entry<String, String> entry : bookCollection.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(subject)) {
                books.add(entry.getKey());
            }
        }
        return books;
    }
    
    public boolean studyBook(String title) {
        if (!isActive()) {
            System.out.println("Library is closed! Cannot study books.");
            return false;
        }
        
        String subject = bookCollection.get(title);
        if (subject == null) {
            System.out.println("Book '" + title + "' not found in the library.");
            return false;
        }
        
        System.out.println("Studying '" + title + "' on " + subject + " at " + getStructureName());
        increaseKnowledge(10);
        enhanceMagicPower(5);
        return true;
    }
    
    public void researchSubject(String subject) {
        if (!isActive()) {
            System.out.println("Library is closed! Cannot conduct research.");
            return;
        }
        
        if (!availableSubjects.contains(subject)) {
            System.out.println("No books available on " + subject);
            return;
        }
        
        List<String> relevantBooks = findBooksBySubject(subject);
        System.out.println("Researching " + subject + " using " + relevantBooks.size() + " books");
        increaseKnowledge(relevantBooks.size() * 5);
        enhanceMagicPower(relevantBooks.size() * 2);
    }

    // === Knowledge Management ===
    private void increaseKnowledge(int amount) {
        int newLevel = Math.min(1000, knowledgeLevel + amount);
        setKnowledgeLevel(newLevel);
    }
    
    private void decreaseKnowledge(int amount) {
        int newLevel = Math.max(0, knowledgeLevel - amount);
        setKnowledgeLevel(newLevel);
    }
    
    public void organizeLibrary() {
        if ("None".equals(currentLibrarian)) {
            System.out.println("No librarian available to organize the library.");
            return;
        }
        
        System.out.println(currentLibrarian + " organizes " + getStructureName());
        increaseKnowledge(bookCollection.size() / 10);
        enhanceMagicPower(10);
    }
    
    public void catalogBooks() {
        System.out.println("=== Library Catalog for " + getStructureName() + " ===");
        Map<String, List<String>> subjectGroups = new HashMap<>();
        
        for (Map.Entry<String, String> entry : bookCollection.entrySet()) {
            String subject = entry.getValue();
            subjectGroups.computeIfAbsent(subject, k -> new ArrayList<>()).add(entry.getKey());
        }
        
        for (Map.Entry<String, List<String>> entry : subjectGroups.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue().size() + " books):");
            for (String book : entry.getValue()) {
                System.out.println("  - " + book);
            }
        }
    }

    // === Factory methods for library types ===
    public static MysticLibrary createScholarLibrary(String name, String location, String scholar) {
        MysticLibrary library = new MysticLibrary(name, location, scholar);
        library.addBook("Introduction to Magic", "Magic Theory");
        library.addBook("Basic Alchemy", "Alchemy");
        library.addBook("Herb Gathering", "Herbalism");
        return library;
    }
    
    public static MysticLibrary createRoyalLibrary(String name, String location, String royalLibrarian) {
        MysticLibrary library = new MysticLibrary(name, location, royalLibrarian, 1000);
        initializeRoyalCollection(library);
        return library;
    }
    
    public static MysticLibrary createAncientRepository(String name, String location, String keeper) {
        MysticLibrary library = new MysticLibrary(name, location, keeper, 5000);
        initializeAncientCollection(library);
        library.setKnowledgeLevel(500);
        return library;
    }

    // === Initialization methods ===
    private void initializeFewBooks() {
        addBook("Basic Magic", "Magic Theory");
        addBook("Simple Spells", "Spellcasting");
        addBook("Herb Guide", "Herbalism");
    }
    
    private void initializeModerateCollection() {
        initializeFewBooks();
        addBook("Intermediate Magic", "Magic Theory");
        addBook("Potion Making", "Alchemy");
        addBook("History of Kingdoms", "History");
        addBook("Dragon Lore", "Mythology");
        addBook("Enchantment Basics", "Enchantment");
    }
    
    private void initializeAncientArchives() {
        initializeModerateCollection();
        addBook("Advanced Transmutation", "Alchemy");
        addBook("Time Magic", "Chronomancy");
        addBook("Planar Travel", "Planar Studies");
        addBook("Ancient Prophecies", "Divination");
        addBook("Lost Civilizations", "Archaeology");
        addBook("Forbidden Arts", "Dark Magic");
    }
    
    private static void initializeRoyalCollection(MysticLibrary library) {
        String[] subjects = {"Magic Theory", "History", "Politics", "Military Strategy", "Economics"};
        String[][] books = {
            {"Royal Magic Protocols", "Court Wizardry", "State Enchantments"},
            {"Kingdom Chronicles", "Royal Lineages", "Treaties and Alliances"},
            {"Diplomacy Arts", "Leadership Principles", "Governance Methods"},
            {"Castle Defense", "Siege Warfare", "Military Tactics"},
            {"Trade Regulations", "Tax Systems", "Resource Management"}
        };
        
        for (int i = 0; i < subjects.length; i++) {
            for (String book : books[i]) {
                library.addBook(book, subjects[i]);
            }
        }
    }
    
    private static void initializeAncientCollection(MysticLibrary library) {
        initializeRoyalCollection(library);
        String[] ancientSubjects = {"Ancient Magic", "Lost Languages", "Cosmic Studies", "Artifact Creation"};
        String[][] ancientBooks = {
            {"Primordial Spells", "Creation Magic", "World Shaping"},
            {"Dead Languages", "Ancient Runes", "Forgotten Scripts"},
            {"Star Magic", "Celestial Bodies", "Cosmic Forces"},
            {"Legendary Weapons", "Magic Items", "Power Sources"}
        };
        
        for (int i = 0; i < ancientSubjects.length; i++) {
            for (String book : ancientBooks[i]) {
                library.addBook(book, ancientSubjects[i]);
            }
        }
    }

    // === Utility methods ===
    public int getBookCount() {
        return bookCollection.size();
    }
    
    public int getSubjectCount() {
        return availableSubjects.size();
    }
    
    public boolean hasBook(String title) {
        return bookCollection.containsKey(title);
    }
    
    public boolean hasSubject(String subject) {
        return availableSubjects.contains(subject);
    }
    
    public boolean isAtCapacity() {
        return bookCollection.size() >= maxBooks;
    }
    
    public String getLibraryStatus() {
        if (knowledgeLevel >= 500) return "Ancient Archive";
        if (knowledgeLevel >= 300) return "Great Library";
        if (knowledgeLevel >= 150) return "Scholarly Library";
        if (knowledgeLevel >= 50) return "Basic Library";
        return "Poor Collection";
    }

    // === Override methods ===
    @Override
    public String getStructureInfo() {
        return String.format("MysticLibrary: %s (Librarian: %s, Books: %d/%d, Knowledge: %d)", 
            getStructureName(), currentLibrarian, bookCollection.size(), maxBooks, knowledgeLevel);
    }

    @Override
    public String toString() {
        return "MysticLibrary{" +
                "name='" + getStructureName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", librarian='" + currentLibrarian + '\'' +
                ", books=" + bookCollection.size() + "/" + maxBooks +
                ", knowledge=" + knowledgeLevel +
                ", subjects=" + availableSubjects.size() +
                ", active=" + isActive() +
                '}';
    }
}