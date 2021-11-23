package tb.soft;

import java.util.Objects;

public class PersonZadanie extends Person {

    public PersonZadanie(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }
    //zdefiniowanie metody equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getBirthYear() == person.getBirthYear() && getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName());
    }
    //zdefiniowanie metody hashCode
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthYear());
    }

}