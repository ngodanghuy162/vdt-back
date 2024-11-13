package appbackend.back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_table")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String address;
    private String linkFB;
    private int matchaSuaBo;
    private int matchaSuaHat;
    private int matchaDua;
    private boolean addMatcha;
    private boolean premiumMatcha;
    private String note;

    public OrderModel() {
    }

    public OrderModel(String name, String phone, String address, int matchaSuaBo, String linkFB, int matchaSuaHat, int matchaDua, boolean addMatcha, boolean premiumMatcha, String note) {
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
