package project.obj;

public class Plot {
    String owner;
    int owner_id;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPlot_id() {
        return plot_id;
    }

    public void setPlot_id(int plot_id) {
        this.plot_id = plot_id;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isForRent() {
        return isForRent;
    }

    public void setForRent(boolean forRent) {
        isForRent = forRent;
    }

    String adress;
    int plot_id;
    int price;
    int rent;
    boolean isSale;
    boolean isForRent;
    public enum typeEnum {
        BOS,
        MARKET,
        MAGAZA,
        EMLAKCI
    }
    public enum satisDurumuEnum {
        hayir,
        satilik,
        kiralik
    }
    Enum<typeEnum> type;

    public Enum<satisDurumuEnum> getSatisDurumu() {
        return satisDurumu;
    }

    public void setSatisDurumu(Enum<satisDurumuEnum> satisDurumu) {
        this.satisDurumu = satisDurumu;
    }

    Enum<satisDurumuEnum> satisDurumu;

    public void setType(Enum<typeEnum> type) {
        this.type = type;
    }

    public Enum<typeEnum> getType() {
        return type;
    }

}
