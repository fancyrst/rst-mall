package com.rstang.support.service;

import com.rstang.common.entity.User;
import com.rstang.support.sys.dao.entity.SysDict;
import com.rstang.support.sys.dao.mapper.SysDictMapper;
import com.rstang.util.key.IDCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Test
	public void testDictInsert() throws Exception{
		User user = new User();
		user.setId("u12345671");

		SysDict dict = new SysDict();
		dict.setId(IDCreator.getInstance().getID("sys.dict"));
		dict.setLabel("归属公司");
		dict.setType("11");
		dict.setValue("123");
		dict.setSort(12);
		dict.setDescription("归属DESC");
		dict.setCreateBy(user.getId());
		dict.setUpdateBy(user.getId());

		sysDictMapper.insertSelective(dict);

	}



}