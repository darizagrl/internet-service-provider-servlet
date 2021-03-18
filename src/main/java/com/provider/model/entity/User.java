package com.provider.model.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private List<Tariff> tariffs;
    private Double balance;
    private boolean isBlocked;

    private User(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.tariffs = builder.tariffs;
        this.balance = builder.balance;
        this.isBlocked = builder.isBlocked;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public static class Builder {
        private Integer id;
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private Role role;
        private List<Tariff> tariffs;
        private Double balance;
        private boolean isBlocked;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setTariffs(List<Tariff> tariffs) {
            this.tariffs = tariffs;
            return this;
        }

        public Builder setBalance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder setBlocked(boolean blocked) {
            isBlocked = blocked;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                firstname.equals(user.firstname) &&
                lastname.equals(user.lastname) &&
                email.equals(user.email) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
