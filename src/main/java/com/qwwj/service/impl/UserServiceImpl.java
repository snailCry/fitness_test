package com.qwwj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qwwj.mapper.SysUserMapper;
import com.qwwj.mapper.SysUserMapperCustom;
import com.qwwj.pojo.SysUser;
import com.qwwj.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysUserMapperCustom userMapperCustom;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(SysUser user) throws Exception {
		userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(SysUser user) {
		//userMapper.updateByPrimaryKey(user);//更新所有，其他字段更新为null
		userMapper.updateByPrimaryKeySelective(user);//更新有值的
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserList(SysUser user) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(user.getUsername())) {
//			criteria.andEqualTo("username", user.getUsername());
			criteria.andLike("username", "%" + user.getUsername() + "%");
		}
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		List<SysUser> userList = userMapper.selectByExample(example);
		return userList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
		//开始分页
		Page<Object> pageInfo = PageHelper.startPage(page, pageSize,true);
		
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmptyOrWhitespace(user.getNickname())) {
			criteria.andLike("nickname", "%" + user.getNickname() + "%");
		}
		example.orderBy("id").desc();
		List<SysUser> userList = userMapper.selectByExample(example);
		
		long total = pageInfo.getTotal();
		System.out.println(total);
		return userList;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public SysUser queryUserByIdCustom(String userId) {
		List<SysUser> userList = userMapperCustom.queryUser222(userId);
		if (userList != null && !userList.isEmpty()) {
			return (SysUser)userList.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserTransactional(SysUser user) {
		userMapper.insert(user);
		//int a = 1 / 0;
		user.setSex(2);
		userMapper.updateByPrimaryKeySelective(user);
	}

}
