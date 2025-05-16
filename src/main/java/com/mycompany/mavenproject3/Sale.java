public class Sale {
    private String name;
    private double harga;
    private int quant;
    
    public Sale(String name, double harga, int quant){
        this.name = name;
        this.harga = harga;
        this.quant = quant;
    }
    
    public String getName() { return name; }
    public void setName(String name) {this.name = name; }
    public double getHarga() { return harga; }
    public void setHarga (double harga) {this.harga = harga; }
    public int getQuant() { return quant; }
    public void setQuant(int quant) {this.quant = quant; }
}
