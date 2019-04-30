package pl.home.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "You have to enter first name")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "You have to enter last name")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "You have to enter age")
    @Column(name = "age")
    private String age;

    @NotNull(message = "You have to set gender")
    @Column(name = "gender")
    private String gender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "university_id")
    private University university;

}
