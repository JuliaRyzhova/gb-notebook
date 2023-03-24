package notebook.model;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

    private User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.phone = userBuilder.phone;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String phone;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %s\nИмя: %s,\nФамилия: %s,\nТелефон: %s", id, firstName, lastName, phone);
    }
}
