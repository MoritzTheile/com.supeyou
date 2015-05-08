package com.hotelorga.foundation.iface.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.hotelorga.foundation.iface.datatype.enums.MIMETYPE;
import com.hotelorga.foundation.iface.datatype.types.SingleLineString256Type;
import com.hotelorga.foundation.iface.dto.FileDTO;
import com.hotelorga.foundation.impl.FileCRUDServiceImpl;
import com.hotelorga.foundation.impl.initialdata.InitialData;

public class CRUDFileTest {

	@Test
	public void test() throws Exception {

		FileDTO dto = new FileDTO();

		String filename = "Weihnachtskampagne.csv";

		dto.setMimetype(MIMETYPE.TEXT_COMMA_SEPERATED_VALUES);
		SingleLineString256Type name = new SingleLineString256Type(filename);
		dto.setName(name);
		dto = FileCRUDServiceImpl.i().updadd(InitialData.i().initialAdmin, dto);

		Assert.assertEquals(MIMETYPE.TEXT_COMMA_SEPERATED_VALUES, dto.getMimetype());
		Assert.assertEquals(name, dto.getName());

		FileCRUDServiceImpl.i().setData(InitialData.i().initialAdmin, dto.getId(), resource2ByteArray(filename));

		byte[] data = FileCRUDServiceImpl.i().getData(InitialData.i().initialAdmin, dto.getId());

		Assert.assertNotNull(data);

		dto = FileCRUDServiceImpl.i().get(InitialData.i().initialAdmin, dto.getId());

		dto.setName(new SingleLineString256Type("testname.file.txt"));

		dto = FileCRUDServiceImpl.i().updadd(InitialData.i().initialAdmin, dto);

		dto = FileCRUDServiceImpl.i().get(InitialData.i().initialAdmin, dto.getId());

		Assert.assertTrue(dto.getSize().value() == data.length);

	}

	private byte[] resource2ByteArray(String filename) throws Exception {

		InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			return buffer.toByteArray();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
