package com.xtdx.utils;

import com.xtdx.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @program: online-voting-system
 * @description: 确认登录状态
 * @author: LEO
 * @create: 2021-05-13 17:34
 **/
public class CheckRole {
    /**
     *返回用户状态，-1未登录，0选民，1管理员
     * @param session
     * @return level
     */
    public static int checkLoginAndRole(HttpSession session){
        User user = (User) session.getAttribute("curUser");
        if(user == null){
            return -1;
        }
        return user.getLevel();
    }

}
