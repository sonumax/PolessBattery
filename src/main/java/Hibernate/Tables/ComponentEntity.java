package Hibernate.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Component", schema = "dbo", catalog = "PolessBattery")
public class ComponentEntity {
    private int componentId;
    private String nameComponent;

    @Id
    @Column(name = "ComponentID", nullable = false)
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "NameComponent", nullable = false, length = 30)
    public String getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentEntity that = (ComponentEntity) o;

        if (componentId != that.componentId) return false;
        if (nameComponent != null ? !nameComponent.equals(that.nameComponent) : that.nameComponent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = componentId;
        result = 31 * result + (nameComponent != null ? nameComponent.hashCode() : 0);
        return result;
    }
}
