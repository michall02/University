package pl.home.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private String value;
}
