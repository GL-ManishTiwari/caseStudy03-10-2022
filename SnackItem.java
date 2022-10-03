public class SnackItem {
    private String itemName;
    private String rate;
    private String status;
    private String discountQty;
    private String discountRate;

    public SnackItem() {
        super();
    }

    public SnackItem(String itemName, String rate, String status, String discountQty, String discountRate) {
        super();
        this.itemName = itemName;
        this.rate = rate;
        this.status = status;
        this.discountQty = discountQty;
        this.discountRate = discountRate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscountQty() {
        return discountQty;
    }

    public void setDiscountQty(String discountQty) {
        this.discountQty = discountQty;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

}
