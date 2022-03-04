package easymall.po;

public class RabbitmqData {
    private String receiverinfo;
    private String cartIds;
    private User user;

    public RabbitmqData() {
    }

    public RabbitmqData(String receiverinfo, String cartIds, User user) {
        this.receiverinfo = receiverinfo;
        this.cartIds = cartIds;
        this.user = user;
    }

    @Override
    public String toString() {
        return "RabbitmqData{" +
                "receiverinfo='" + receiverinfo + '\'' +
                ", cartIds='" + cartIds + '\'' +
                ", user=" + user +
                '}';
    }

    public String getReceiverinfo() {
        return receiverinfo;
    }

    public void setReceiverinfo(String receiverinfo) {
        this.receiverinfo = receiverinfo;
    }

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
