package com.lanou.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 17/12/4.
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public String getName() {
        return "myPermissionRealm";
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*授权方法*/
        //1. 认证的结果: 取出的user实体类/用户名
        String username = (String) principalCollection.getPrimaryPrincipal();

        //TODO:2. 从数据库中获取该用户的所有角色和权限

        // 模拟start >>>
        List<String> roleList = new ArrayList<String>();
        roleList.add("CEO");
        roleList.add("HR");

        List<String> perList = new ArrayList<String>();
        perList.add("user:create");
        perList.add("user:query");
        // 模拟over <<<

        //3. 将获取的权限和角色都统一起来
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        info.addStringPermissions(perList);


        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        /*获得用户此次输入的用户名*/
        String username = (String) authenticationToken.getPrincipal();

        // TODO: 此处应去数据库查询是否存在

        //----模拟 start----
        if (!"jaso".equals(username)){
            return null;
        }
        //----模拟 over----

        /*获得用户此次输入的密码*/
        String password = new String((char[]) authenticationToken.getCredentials());

        // TODO: 此处应去数据库查询是否存在

        //----模拟 start----
        if (!"123".equals(password)){
            return null;
        }
        //----模拟 over----


        /*返回认证成功信息*/
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
