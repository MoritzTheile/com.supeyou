package com.supeyou.crudie.web.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.supeyou.crudie.iface.datatype.enums.MIMETYPE;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.datatype.types.SingleLineString256Type;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.impl.FileCRUDServiceImpl;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public class FileUploadServlet extends HttpServlet {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private static final long serialVersionUID = 89343450671706L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Ansonsten werden Umlaute in Dateinamen verhunzt:
		req.setCharacterEncoding("UTF-8");

		// process only multipart requests
		if (ServletFileUpload.isMultipartContent(req)) {

			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {

				List<FileItem> items = upload.parseRequest(req);

				String fileId = null;

				// FileId finden
				for (FileItem item : items) {

					if (item.getFieldName().equals(GWTSTATICS.FILE_ID_TOKEN)) {

						fileId = item.getString();

						break;

					}

				}

				if (fileId == null) {

					throw new Exception("es wurde keine fileId gefunden");

				}

				for (FileItem item : items) {

					// process only file upload - discard other form item types
					if (item.isFormField())
						continue;

					String fileName = item.getName();

					// get only the file name not whole path
					if (fileName != null) {
						fileName = FilenameUtils.getName(fileName);
					}

					InputStream inputStream = null;

					try {

						inputStream = item.getInputStream();

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						int nRead;

						byte[] data = new byte[16384];

						while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
							buffer.write(data, 0, nRead);
						}

						byte[] allData = buffer.toByteArray();

						FileCRUDServiceImpl.i().setData(SessionStore.getAuthenticatedActor(req.getSession()), new FileIDType(new Long(fileId)), allData);

					} finally {
						inputStream.close();
					}

					FileDTO fileDTO = FileCRUDServiceImpl.i().get(SessionStore.getAuthenticatedActor(req.getSession()), new FileIDType(new Long(fileId)));

					fileDTO.setName(new SingleLineString256Type(fileName));

					fileDTO.setMimetype(getMIMETYPE(item.getContentType()));

					FileCRUDServiceImpl.i().updadd(SessionStore.getAuthenticatedActor(req.getSession()), fileDTO);

					// resp.setStatus(HttpServletResponse.SC_CREATED);
					resp.setStatus(HttpServletResponse.SC_OK);
					// resp.getWriter().print("The file was created successfully.");
					resp.flushBuffer();

				}
			} catch (Exception e) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
						"An error occurred while creating the file : " + e.getMessage());
			}

		} else {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
					"Request contents type is not supported by the servlet.");
		}
	}

	private MIMETYPE getMIMETYPE(String mimetypeString) {
		MIMETYPE mimetype = MIMETYPE.getByMimeTypeName(mimetypeString);
		if (mimetype == null) {
			log.info("no MIMETYPE found for " + mimetypeString);
		}
		return mimetype;
	}
}