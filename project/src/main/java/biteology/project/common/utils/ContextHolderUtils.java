package biteology.project.common.utils;


import biteology.project.entity.Account;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.naming.Context;

public class ContextHolderUtils {

    public static Account getContext() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
