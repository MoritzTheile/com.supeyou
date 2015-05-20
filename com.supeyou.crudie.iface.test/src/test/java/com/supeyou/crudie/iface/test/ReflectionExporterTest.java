package com.supeyou.crudie.iface.test;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.crudie.iface.datatype.enums.LOCALE;
import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.datatype.types.AmountType;
import com.supeyou.crudie.iface.datatype.types.DateType;
import com.supeyou.crudie.iface.datatype.types.UserIDType;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.dtohelper.ReflectionExporter;

public class ReflectionExporterTest {

	@Test
	public void dto2map() throws Exception {

		UserDTO dto = new UserDTO();

		UserIDType id = new UserIDType(12L);
		dto.setId(id);
		dto.addRole(ROLE.ADMIN);
		dto.addRole(ROLE.USER);

		boolean active = true;
		dto.setActive(active);
		AmountType amount = new AmountType(new Integer("99"));
		dto.setAmount(amount);
		DateType date = new DateType("2015-02-16");
		dto.setBirthday(date);
		dto.setLocale(LOCALE.de);

		Map<String, String> row = ReflectionExporter.fillStringMap(dto);

		print(row);

		Assert.assertEquals(id + "", row.get("Id"));
		Assert.assertNotNull(row.get("Roles"));
		Assert.assertTrue(row.get("Roles").contains("ADMIN"));
		Assert.assertTrue(row.get("Roles").contains("USER"));
		Assert.assertEquals(active + "", row.get("Active"));
		Assert.assertEquals(amount + "", row.get("Amount"));
		Assert.assertEquals(date + "", row.get("Birthday"));
		Assert.assertEquals(LOCALE.de + "", row.get("Locale"));

	}

	private void print(Map<String, String> row) {

		for (String key : row.keySet()) {
			String value = row.get(key);
			System.out.println(key + " = " + value);
		}

		System.out.println("-------------------");

		for (String key : row.keySet()) {
			System.out.print(key + ";");
		}
		System.out.println();
		for (String key : row.keySet()) {
			System.out.print(row.get(key) + ";");
		}
		System.out.println();

	}

}
