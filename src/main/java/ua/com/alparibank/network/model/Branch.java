package ua.com.alparibank.network.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToOne
    private LocationAddr location;
    @OneToMany(targetEntity = NetworkDevice.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", updatable = false)
    private List<NetworkDevice> devices;

    public Branch() {
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

    public LocationAddr getLocation() {
        return location;
    }

    public void setLocation(LocationAddr location) {
        this.location = location;
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
        Branch branch = (Branch) o;
        return id == branch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location=" + location +
                '}';
    }
}
