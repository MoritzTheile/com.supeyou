package com.supeyou.crudie.web.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supeyou.crudie.iface.datatype.enums.MIMETYPE;
import com.supeyou.crudie.iface.datatype.types.FileIDType;
import com.supeyou.crudie.iface.dto.FileDTO;
import com.supeyou.crudie.impl.FileCRUDServiceImpl;
import com.supeyou.crudie.web.client.resources.GWTSTATICS;

public class FileDownloadServlet extends HttpServlet {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private static final long serialVersionUID = 47361934699242166L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String fileId = req.getParameter(GWTSTATICS.FILE_ID_TOKEN);

			FileDTO file = FileCRUDServiceImpl.i().get(SessionStore.getAuthenticatedActor(req.getSession()), new FileIDType(fileId));

			if (file != null) {

				byte[] data = FileCRUDServiceImpl.i().getData(SessionStore.getAuthenticatedActor(req.getSession()), new FileIDType(fileId));

				String mimetype = MIMETYPE.APPLICATION_OCTEDSTREAM.getMimeTypeString();
				if (file.getMimetype() != null) {
					mimetype = file.getMimetype().getMimeTypeString();
				}

				//
				// Set the response and go!
				//
				resp.setContentType(mimetype);
				resp.setContentLength((int) data.length);
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

				//
				// Stream to the requester.
				//
				{
					ServletOutputStream op = resp.getOutputStream();
					int BUFSIZE = (32 * 1024); // 32K
					int length = 0;
					byte[] bbuf = new byte[BUFSIZE];
					ByteArrayInputStream in = new ByteArrayInputStream(data);
					try {
						while ((in != null) && ((length = in.read(bbuf)) != -1)) {
							op.write(bbuf, 0, length);
						}
					} finally {
						cleanUp(op, in);
					}
				}

			} else {

				resp.setContentType("text/html");

				resp.getWriter().println("<html>");
				resp.getWriter().println("Download gescheitert. Datei konnte nicht gefunden werden.");
				resp.getWriter().println("</html>");
				resp.getWriter().flush();
				resp.getWriter().close();

			}

		} catch (Exception e) {

			log.log(Level.WARNING, "Problems downloading file", e);

			resp.setContentType("text/html");
			resp.getWriter().write(e.getMessage());
			resp.getWriter().flush();
			resp.getWriter().close();

		}
	}

	private void cleanUp(ServletOutputStream op, ByteArrayInputStream in) {
		try {
			in.close();
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception on cleaning up", e);
		}
		try {
			op.flush();
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception on cleaning up", e);
		}
		try {
			op.close();
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception on cleaning up", e);
		}
	}

}