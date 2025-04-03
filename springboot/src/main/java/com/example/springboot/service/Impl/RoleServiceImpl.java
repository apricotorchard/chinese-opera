package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.domain.DTO.RoleDTO;
import com.example.springboot.domain.Permession;
import com.example.springboot.domain.Role;
import com.example.springboot.mapper.MenuMapper;
import com.example.springboot.mapper.RoleMapper;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Role> getRolesInfo() {
        List<Role> roles = this.list();
        for (Role role : roles) {
            List<Long> rolemenu = roleMapper.getRolemenu(role.getId());
            if(!rolemenu.isEmpty()){
                List<Permession> permessions = menuMapper.selectBatchIds(rolemenu);
                role.setMeuns(permessions);
            }
        }
        return roles;
    }

    @Override
    public int saveRoleInfo(RoleDTO roleDTO) {
        //1.首先先把用户增加上去
        Role role = new Role();
        role.setRoleName(roleDTO.getName());
        int insert = roleMapper.insert(role);
        //2.然后将角色id和选择menuid插入到role_menu表里面
        return insert;
    }

    @Override
    @Transactional
    public int updateRoleInfo(RoleDTO role) {
        Long roleid = role.getId();
        List<Long> menus = role.getMenus();
        if(menus!=null){
            roleMapper.deleteRoleMenu(roleid);
            roleMapper.insertRoleMenu(roleid,menus);
        }
        return 0;
    }

    @Override
    @Transactional
    public int removeRoleAndMenuById(long id) {
        roleMapper.deleteRoleMenu(id);
        roleMapper.deleteById(id);
        return 0;
    }
}
