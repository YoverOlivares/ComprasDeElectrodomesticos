package unc.edu.pe.comprasdeelectrodomesticos.domain.model;

public class Gift {
    private String name;
    private int imageResourceId; // Referencia a R.drawable.xxx

    public Gift(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() { return name; }
    public int getImageResourceId() { return imageResourceId; }
}