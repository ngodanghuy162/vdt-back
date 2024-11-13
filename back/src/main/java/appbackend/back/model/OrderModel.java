package appbackend.back.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "order_tble")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("phone")
    @Column(name = "phone")
    private String phone;

    @JsonProperty("address")
    @Column(name = "address")
    private String address;

    @JsonProperty("linkfb")
    @Column(name = "linkfb")
    private String linkFB;

    @JsonProperty("matcha_sua_bo")
    @Column(name = "matcha_sua_bo")
    private int matchaSuaBo;

    @JsonProperty("matcha_sua_hat")
    @Column(name = "matcha_sua_hat")
    private int matchaSuaHat;

    @JsonProperty("matcha_dua")
    @Column(name = "matcha_dua")
    private int matchaDua;

    @JsonProperty("add_matcha")
    @Column(name = "add_matcha")
    private boolean addMatcha;

    @JsonProperty("premium_matcha")
    @Column(name = "premium_matcha")
    private boolean premiumMatcha;

    @JsonProperty("note")
    @Column(name = "note")
    private String note;

    @JsonProperty("total_price")
    @Column(name = "total_price")
    private int total_price;

    public OrderModel() {
    }

    public OrderModel(int totalPrice) {
        total_price = totalPrice;
    }

    public OrderModel(String name, String phone, String address, int matchaSuaBo, String linkFB, int matchaSuaHat, int matchaDua, boolean addMatcha, boolean premiumMatcha, String note, int totalPrice) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.matchaSuaBo = matchaSuaBo;
        this.linkFB = linkFB;
        this.matchaSuaHat = matchaSuaHat;
        this.matchaDua = matchaDua;
        this.addMatcha = addMatcha;
        this.premiumMatcha = premiumMatcha;
        this.note = note;
        total_price = totalPrice;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkFB() {
        return linkFB;
    }

    public void setLinkFB(String linkFB) {
        this.linkFB = linkFB;
    }

    public int getMatchaSuaBo() {
        return matchaSuaBo;
    }

    public void setMatchaSuaBo(int matchaSuaBo) {
        this.matchaSuaBo = matchaSuaBo;
    }

    public int getMatchaSuaHat() {
        return matchaSuaHat;
    }

    public void setMatchaSuaHat(int matchaSuaHat) {
        this.matchaSuaHat = matchaSuaHat;
    }

    public int getMatchaDua() {
        return matchaDua;
    }

    public void setMatchaDua(int matchaDua) {
        this.matchaDua = matchaDua;
    }

    public boolean isAddMatcha() {
        return addMatcha;
    }

    public void setAddMatcha(boolean addMatcha) {
        this.addMatcha = addMatcha;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isPremiumMatcha() {
        return premiumMatcha;
    }

    public void setPremiumMatcha(boolean premiumMatcha) {
        this.premiumMatcha = premiumMatcha;
    }
}
