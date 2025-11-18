package unc.edu.pe.comprasdeelectrodomesticos.domain.model;

public class AddOn {
    private String name;
    private double price;
    private boolean isSelected;

    public AddOn(String name, double price) {
        this.name = name;
        this.price = price;
        this.isSelected = false;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}