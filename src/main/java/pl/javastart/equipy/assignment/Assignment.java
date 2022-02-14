package pl.javastart.equipy.assignment;

import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime rentStart) {
        this.start = rentStart;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime rentEnd) {
        this.end = rentEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
