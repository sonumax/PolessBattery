package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Polarity", schema = "dbo", catalog = "PolessBattery")
public class PolarityEntity {
    private int polarityId;
    private String namePolarity;
    private Set<BatteryEntity> batteries;

    @Id
    @GenericGenerator(name = "polar", strategy = "increment")
    @GeneratedValue(generator = "polar")
    @Column(name = "PolarityID")
    public int getPolarityId() {
        return polarityId;
    }

    public void setPolarityId(int polarityId) {
        this.polarityId = polarityId;
    }

    @Basic
    @Column(name = "NamePolarity")
    public String getNamePolarity() {
        return namePolarity;
    }

    public void setNamePolarity(String namePolarity) {
        this.namePolarity = namePolarity;
    }

    @OneToMany(mappedBy = "polarity")
    public Set<BatteryEntity> getBatteries() {
        return batteries;
    }

    public void setBatteries(Set<BatteryEntity> batteries) {
        this.batteries = batteries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PolarityEntity that = (PolarityEntity) o;

        if (polarityId != that.polarityId) return false;
        if (namePolarity != null ? !namePolarity.equals(that.namePolarity) : that.namePolarity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = polarityId;
        result = 31 * result + (namePolarity != null ? namePolarity.hashCode() : 0);
        return result;
    }
}
