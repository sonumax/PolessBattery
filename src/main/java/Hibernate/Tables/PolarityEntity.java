package Hibernate.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Polarity", schema = "dbo", catalog = "PolessBattery")
public class PolarityEntity {
    private int polarityId;
    private String namePolarity;

    @Id
    @Column(name = "PolarityID", nullable = false)
    public int getPolarityId() {
        return polarityId;
    }

    public void setPolarityId(int polarityId) {
        this.polarityId = polarityId;
    }

    @Basic
    @Column(name = "NamePolarity", nullable = false, length = 15)
    public String getNamePolarity() {
        return namePolarity;
    }

    public void setNamePolarity(String namePolarity) {
        this.namePolarity = namePolarity;
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
