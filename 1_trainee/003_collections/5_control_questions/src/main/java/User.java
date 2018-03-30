import java.util.Objects;

public class User {

    private String name;
    private String passport;

    public User(String name, String passport) {
        this.setName(name);
        this.setPassport(passport);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.getName(), user.getName())
                && Objects.equals(this.getPassport(), user.getPassport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPassport());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
