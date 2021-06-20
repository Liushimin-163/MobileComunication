public class ServerPackage {
    double price;

    public ServerPackage() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String showInfo() {
        return "ServerPackage{" +
                "price=" + price +
                '}';
    }
}
