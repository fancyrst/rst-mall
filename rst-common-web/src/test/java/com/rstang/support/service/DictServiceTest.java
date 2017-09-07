package com.rstang.support.service;

import com.rstang.common.entity.User;
import com.rstang.support.sys.entity.Dict;
import com.rstang.util.key.IDCreator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rstang.support.sys.service.DictService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

	@Autowired
	private DictService dictService;

	@Test
	public void testInsert() throws Exception {
		User user = new User();
		user.setId("u12345671");

		Dict dict = new Dict();
		dict.setId(IDCreator.getInstance().getID("sys.dict"));
		dict.setLabel("归属公司");
		dict.setType("11");
		dict.setValue("123");
		dict.setSort(12);
		dict.setDescription("归属DESC");
		dict.setCreateBy(user);
		dict.setUpdateBy(user);
		dict.setIsNewRecord(true);//将setIsNewRecord(true)，手动传入ID：IDCreator.getInstance().getID("sys.dict")
		dictService.save(dict);

//		Assert.assertEquals(1, dictService.findList(new Dict()));
	}

	@Test
	public void testQuery() throws Exception {
		List<Dict> dicts = dictService.findList(new Dict());
		for (Dict dict : dicts) {
			System.out.println(dict.getId()+"="+dict.getLabel());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = new User();
		user.setId("u12345672");

		Dict dict = new Dict();
		dict.setId("S0217090716315601");
		dict.setLabel("归属公司");
		dict.setValue("321");
		dict.setType("1");
		dict.setSort(12);
		dict.setDescription("归属DESC");
		dict.setCreateBy(user);
		dict.setUpdateBy(user);
		//dict.setIsNewRecord(true); 更新时保证ID有值即可
		dictService.save(dict);
	}

}