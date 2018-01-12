package com.videoondemand.model;

import java.net.PasswordAuthentication;
import com.common.Field;

public class UserOld {
    private int id;
    private PasswordAuthentication credenziali;
    private int ruolo;

    public static class UserBuilder {
        private int _id;
        private String _username;
        private char[] _password;
        private int _ruolo;
        public void set_id(int _id) {
            this._id = _id;
        }
        public UserBuilder setUsername(String _username) {
            this._username = _username;
            return this;
        }
        public UserBuilder setPassword(char[] _password) {
            this._password = _password;
            return this;
        }
        public UserBuilder setRuolo(int _ruolo) {
            this._ruolo = _ruolo;
            return this;
        }

        public UserOld build() {
            return new UserOld(this);
        }
    }

    private UserOld(UserBuilder userBuilder) {
        if(!userBuilder._username.isEmpty() && userBuilder._password.length > 0) {
            this.credenziali = new PasswordAuthentication(userBuilder._username, userBuilder._password);
        } else {
            throw new UnsupportedOperationException("User not defined correctly!");
        }

        if(userBuilder._ruolo <= 0) {
            userBuilder._ruolo = Field.DB.Costant.USER_ROLE_TYPE;
        }

        this.ruolo = userBuilder._ruolo;
        this.id = userBuilder._id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return credenziali.getUserName();
    }

    public String getPassword() {
        return String.valueOf(this.credenziali.getPassword());
    }


}

