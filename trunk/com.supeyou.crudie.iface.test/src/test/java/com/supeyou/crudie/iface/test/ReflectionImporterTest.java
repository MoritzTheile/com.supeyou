package com.supeyou.crudie.iface.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.supeyou.crudie.iface.datatype.enums.ROLE;
import com.supeyou.crudie.iface.dto.AbstrDTO;
import com.supeyou.crudie.iface.dto.User2GroupDTO;
import com.supeyou.crudie.iface.dto.UserDTO;
import com.supeyou.crudie.impl.dtohelper.ReflectionImporter;
import com.supeyou.crudie.impl.dtohelper.ReflectionImporter.Callback;

public class ReflectionImporterTest {

	@Test
	public void fillDTOTest() throws Exception {

		Map<String, String> attribName2Value = new HashMap<>();

		String id = "14";
		attribName2Value.put("Id", id);
		String email = "theile@mtheile.de";
		attribName2Value.put("EmailAddress", email);
		String active = "true";
		attribName2Value.put("Active", active);
		String locale = "de";
		attribName2Value.put("Locale", locale);
		String roles = "[DEVELOPER, ADMIN]";
		attribName2Value.put("Roles", roles);
		String amount = "9999"; // 99,99 Euro
		attribName2Value.put("Amount", amount);

		UserDTO dto = ReflectionImporter.fillDTO(new UserDTO(), attribName2Value);

		Assert.assertNotNull(dto.getId());
		Assert.assertEquals(id, dto.getId() + "");

		Assert.assertNotNull(dto.getEmailAddress());
		Assert.assertEquals(email, dto.getEmailAddress().value());

		Assert.assertNotNull(dto.getActive());
		Assert.assertEquals(active, dto.getActive() + "");

		Assert.assertNotNull(dto.getLocale());
		Assert.assertEquals(locale, dto.getLocale() + "");

		Assert.assertNotNull(dto.getAmount());
		Assert.assertEquals(amount, dto.getAmount() + "");

		Assert.assertNotNull(dto.getRoles());
		Assert.assertEquals(2, dto.getRoles().size());
		Assert.assertTrue(dto.getRoles().contains(ROLE.ADMIN));
		Assert.assertTrue(dto.getRoles().contains(ROLE.DEVELOPER));
	}

	@Test
	public void csvToDTOTest() throws Exception {

		final InputStream inputStream = ClassLoader.getSystemResourceAsStream("UserDTOs.csv");
		final List<UserDTO> dtos = new ArrayList<>();

		ReflectionImporter.getDTOsFromCSVStream(inputStream, Charset.forName("UTF-8"), new Callback<UserDTO>() {

			@Override
			public void filledDTO(UserDTO dto) {
				dtos.add(dto);
			}

			@Override
			public void finished() {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Assert.assertEquals(2, dtos.size());
				// random test:
				Assert.assertEquals(dtos.iterator().next().getEmailAddress().value(), "test@test.de");
			}

			@Override
			public UserDTO newDTO() {
				return new UserDTO();
			}

		});

	}

	public static void main(String[] args) {
		Object dto = new User2GroupDTO();
		for (Method method : dto.getClass().getMethods()) {
			if (!method.getName().startsWith("setDtoA")) {
				continue;
			}
			System.out.println("---------");
			System.out.println(method.getName());
			System.out.println(method.getDeclaringClass());
			System.out.println(method.getParameterTypes()[0]);
			System.out.println(AbstrDTO.class.getName());
			System.out.println(method.getParameterTypes()[0].toString().contains(AbstrDTO.class.getName()));
		}
	}
}
