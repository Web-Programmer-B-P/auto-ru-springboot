package ru.petr.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "engine")
public class Engine {
    private static final String HORSE_POWER_PATTERN = "^(\\d{1,4}|10)?$";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fuel_type")
    private String fuelType;

    @NotEmpty(message = "{engine.horse.power.not.empty}")
    @Pattern(regexp = HORSE_POWER_PATTERN, message = "{engine.horse.power.regexp}")
    @Column(name = "horse_power")
    private String horsePower;

    public Engine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id && horsePower == engine.horsePower && Objects.equals(fuelType, engine.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Engine.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("fuelType='" + fuelType + "'")
                .add("horsePower=" + horsePower)
                .toString();
    }
}
