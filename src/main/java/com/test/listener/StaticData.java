package com.test.listener;

import com.google.common.collect.Lists;
import com.test.entity.ApiUser;
import com.test.entity.Role;
import com.test.misc.RoleType;

import java.util.List;
import java.util.Set;

public class StaticData {

    public static List<Role> createRoles() {
        final Role roleCompanyAdmin = new Role();
        roleCompanyAdmin.setType(RoleType.ROLE_MANAGER);
        final Role roleEmployee = new Role();
        roleEmployee.setType(RoleType.ROLE_WAITER);
        return Lists.newArrayList(roleCompanyAdmin, roleEmployee);
    }

    public static ApiUser userDetails(final Set<Role> roles){
        final ApiUser apiUserDetail = new ApiUser();
        apiUserDetail.setPassword("password");
        apiUserDetail.setRoles(roles);
        return apiUserDetail;
    }
}
