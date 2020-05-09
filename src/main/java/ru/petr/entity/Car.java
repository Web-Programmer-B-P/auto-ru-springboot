package ru.petr.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "car")
public class Car {
    private static final String PATTERN_YEAR = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";
    private static final String KM_AGE_PATTERN = "^(\\d{1,7}|100)?$";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 3, message = "{car.name}")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "{car.color.not.empty}")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "{car.color.regexp}")
    @Column(name = "color")
    private String color;

    @NotNull
    @Pattern(regexp = PATTERN_YEAR, message = "{car.year}")
    @Column(name = "year_of_issue")
    private String yearOfIssue;

    @Pattern(regexp = KM_AGE_PATTERN, message = "{car.km.age}")
    @Column(name = "km_age")
    private String kmAge;

    @Column(name = "handlebar")
    private String handlebar;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "wheel_drive")
    private String wheelDrive;

    @Valid
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "fk_engine"))
    private Engine engine;

    @Column(name = "car_body")
    private String carBody;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKmAge() {
        return kmAge;
    }

    public void setKmAge(String kmAge) {
        this.kmAge = kmAge;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getHandlebar() {
        return handlebar;
    }

    public void setHandlebar(String handlebar) {
        this.handlebar = handlebar;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getWheelDrive() {
        return wheelDrive;
    }

    public void setWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getCarBody() {
        return carBody;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("color='" + color + "'")
                .add("yearOfIssue='" + yearOfIssue + "'")
                .add("kmAge='" + kmAge + "'")
                .add("handlebar='" + handlebar + "'")
                .add("transmission='" + transmission + "'")
                .add("wheelDrive='" + wheelDrive + "'")
                .add("engine=" + engine)
                .add("carBody='" + carBody + "'")
                .toString();
    }
}
