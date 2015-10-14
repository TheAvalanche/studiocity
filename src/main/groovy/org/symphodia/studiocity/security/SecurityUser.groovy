package org.symphodia.studiocity.security

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.authority.AuthorityUtils
import org.symphodia.studiocity.model.User

class SecurityUser extends org.springframework.security.core.userdetails.User {

    @JsonIgnore
    User user

    SecurityUser(User user) {
        super(user.email, user.password, AuthorityUtils.createAuthorityList(user.role.toString()))
        this.user = user
    }
}
