package pl.home.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "You have to enter university name")
    @NotEmpty(message = "You have to enter university name")
    @Column(name = "university_name")
    private String universityName;

    @NotNull(message = "You have to enter university country")
    @NotEmpty(message = "You have to enter university country")
    @Column(name = "university_country")
    private String universityCountry;

    @NotNull(message = "You have to enter university city")
    @NotEmpty(message = "You have to enter university city")
    @Column(name = "university_city")
    private String universityCity;

    @Override
    public String toString() {
        return this.universityName;
    }
}
