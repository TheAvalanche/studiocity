package org.symphodia.studiocity.security

import org.springframework.security.core.authority.AuthorityUtils
import org.symphodia.studiocity.model.User

class SecurityUser extends org.springframework.security.core.userdetails.User {

    User user

    SecurityUser(User user) {
        super(user.email, user.password, AuthorityUtils.createAuthorityList(user.role.toString()))
        this.user = user
    }
}
