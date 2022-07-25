package airhacks.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Greggio Riccardo
 */
@Entity
@Table(name = "t_etichette")
public class Etichetta extends BaseEntity {

    @Column(nullable = false)
    @NotBlank
    private String name;

    public Etichetta() {
    }

    public Etichetta(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Etichetta{" + "name=" + name + '}';
    }

}
