package com.furntrade.furntrademanagmentservet.Dtos;
import org.springframework.hateoas.RepresentationModel;

public class AccountDto extends RepresentationModel<AccountDto>
    {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
