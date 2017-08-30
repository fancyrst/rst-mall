package com.rstang.support.service;

import com.rstang.support.sys.entity.Dict;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rstang.support.service.DictService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest {

	@Autowired
	private DictService dictService;

	@Test
	public void testInsert() throws Exception {
		Dict dict = new Dict();
		dict.setLabel("≤‚ ‘");
		dict.setDescription("≤‚ ‘DESC");
		dictService.save(dict);

		Assert.assertEquals(1, dictService.findList(new Dict()));
	}

	@Test
	public void testQuery() throws Exception {
	}
	
	
	@Test
	public void testUpdate() throws Exception {
	}

}