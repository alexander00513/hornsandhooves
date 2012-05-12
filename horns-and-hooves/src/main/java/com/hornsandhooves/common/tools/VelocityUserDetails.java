/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hornsandhooves.common.tools;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс для доступа к ролям пользователя Spring Security со страниц Velocity
 * @author Alexander
 */
public class VelocityUserDetails {

    /**
     * Возвращает имя текущего пользователя
     * @return String
     */
    public static String getPrincipal() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "Guest";
        }
    }
    
    /**
     * Проверяет - принадлежат ли права пользователю
     * @param checkForAuths
     * @return boolean, true- принадлежат, false - нет.
     */
    public static boolean allGranted(String[] checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                continue;
            return false;
            }
        return true;
    }

    /**
     * Совпадают ли права пользователя с какими либо из перечисленных
     * @param checkForAuths
     * @return true - совпадают с какими либо из перечисленных, false - нет
     */
    public static boolean anyGranted(String[] checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return true;
        }
        return false;
    }
    
    /**
     * Совпадают ли права пользователя с какими либо из перечисленных
     * @param checkForAuths
     * @return true - не совпадают с какими либо из перечисленных, false - совпадают
     */
    public static boolean noneGranted(String[] checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
            return false;
        }
        return true;
    }

    private static Set<String> getUserAuthorities() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<String> roles = new HashSet<String>();
        if (obj instanceof UserDetails) {
            Collection<GrantedAuthority> gas = ((UserDetails) obj).getAuthorities();
                for (GrantedAuthority ga : gas) {
                    roles.add(ga.getAuthority());
                }
        }
        return roles;
    }
}
