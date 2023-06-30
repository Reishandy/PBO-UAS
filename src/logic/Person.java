// Author: Muhammad Akbar Reishandy
package logic;

public class Person {
    private final String name, email;
    private final int id;

    public Person(String name, String email, int id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (id != person.id) return false;
        if (!name.equals(person.name)) return false;
        return email.equals(person.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return """
               Borrower ID
               Name  : %s
               Email : %s
               No ID : %d""".formatted(name, email, id);
    }
}
