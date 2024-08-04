package com.i_novus.education.cats_votes;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public class Commons {
    public static Long getUserId() {
        //метод получения айди пользователя по его куки/токену и тому подобное
        //todo
        return -1L ; //getPrincipal().getId();
    }

   /* public static UserSecurity getPrincipal() {
        return getAuthentication().getPrincipal();
    }

    public static String getAccessToken() {
        return getAuthentication().getAccessToken();
    }

    private static UserSecurity getAuthentication() {
        return (UserSecurity) getContext().getAuthentication();
    }*/
}
