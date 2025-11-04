public class ProductEqualityDemo {

    static class Product {
        private int productId;
        private String productName;

        public Product(int productId, String productName) {
            this.productId = productId;
            this.productName = productName;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Product other = (Product) obj;
            return this.productId == other.productId;
        }

        @Override
        public String toString() {
            return "Product{id=" + productId + ", name='" + productName + "'}";
        }
    }

    public static void main(String[] args) {
        Product p1 = new Product(201, "Laptop");
        Product p2 = new Product(201, "Laptop");
        Product p3 = p1;

        System.out.println("p1 == p2: " + (p1 == p2));           // false: different references
        System.out.println("p1.equals(p2): " + p1.equals(p2));   // true: same productId
        System.out.println("p1 == p3: " + (p1 == p3));           // true: same reference
    }
}
