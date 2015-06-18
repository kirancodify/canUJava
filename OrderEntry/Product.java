package oe;

public abstract class Product {
    private static int nextProductId = 2000;
    private int id;
    private String name;
    private String description;
    private double retailPrice;

    public Product()
    {
        id = nextProductId++;
    }
    
    public String toString()
    {
        return "[" + Util.getClassName(this) + "] " + id + " " + name + " " + Util.toMoney(retailPrice);
    }

    public static void setNextProductId(int nextProductId) {
        Product.nextProductId = nextProductId;
    }

    public static int getNextProductId() {
        return nextProductId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public int getId() {
        return id;
    }
}
