package ua.com.alparibank.network.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name = "serial")
    private String serialNumber;
    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<NetworkDevice> devices;

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<NetworkDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<NetworkDevice> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return id == model.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
