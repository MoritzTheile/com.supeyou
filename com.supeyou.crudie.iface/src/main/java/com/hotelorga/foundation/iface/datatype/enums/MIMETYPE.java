package com.hotelorga.foundation.iface.datatype.enums;

import java.util.HashMap;
import java.util.Map;

public enum MIMETYPE {
	// application/acad
	// application/applefile
	// application/astound
	// application/dsptype
	// application/dxf
	// application/futuresplash
	// application/gzip
	// application/listenup
	// application/mac-binhex40
	// application/mbedlet
	// application/mif
	// application/msexcel
	APPLICATION_MSEXCEL("application/msexcel"),
	// application/vnd.ms-excel
	APPLICATION_VNDMSEXCEL("application/vnd.ms-excel"),
	// application/mshelp
	// application/mspowerpoint
	// application/msword
	APPLICATION_MSWORD("application/msword"),
	// application/octet-stream
	APPLICATION_OCTEDSTREAM("application/octet-stream"),
	// application/oda
	// application/pdf
	APPLICATION_PDF("application/pdf"),
	// application/postscript
	// application/rtc
	// application/rtf
	// application/studiom
	// application/toolbook
	// application/vocaltec-media-desc
	// application/vocaltec-media-file
	// application/xhtml+xml
	// application/xml
	// application/x-bcpio
	// application/x-compress
	// application/x-cpio
	// application/x-csh
	// application/x-director
	// application/x-dvi
	// application/x-envoy
	// application/x-gtar
	// application/x-hdf
	// application/x-httpd-php
	// application/x-javascript
	// application/x-latex
	// application/x-macbinary
	// application/x-mif
	// application/x-netcdf
	// application/x-nschat
	// application/x-sh
	// application/x-shar
	// application/x-shockwave-flash
	// application/x-sprite
	// application/x-stuffit
	// application/x-supercard
	// application/x-sv4cpio
	// application/x-sv4crc
	// application/x-tar
	// application/x-tcl
	// application/x-tex
	// application/x-texinfo
	// application/x-troff
	// application/x-troff-man
	// application/x-troff-me
	// application/x-troff-ms
	// application/x-ustar
	// application/x-wais-source
	// application/x-www-form-urlencoded
	// application/zip
	// audio/basic
	// audio/echospeech
	// audio/tsplayer
	// audio/voxware
	// audio/x-aiff
	// audio/x-dspeeh
	// audio/x-midi
	// audio/x-mpeg
	// audio/x-pn-realaudio
	// audio/x-pn-realaudio-plugin
	// audio/x-qt-stream
	// audio/x-wav
	// drawing/x-dwf
	// image/cis-cod
	// image/cmu-raster
	// image/fif
	// image/gif
	IMAGE_GIF("image/gif"),
	// image/ief
	// image/jpeg
	IMAGE_JPEG("image/jpeg"),
	// image/png
	IMAGE_PNG("image/png"),
	// image/tiff
	// image/vasa
	// image/vnd.wap.wbmp
	// image/x-freehand
	// image/x-icon
	// image/x-portable-anymap
	// image/x-portable-bitmap
	// image/x-portable-graymap
	// image/x-portable-pixmap
	// image/x-rgb
	// image/x-windowdump
	// image/x-xbitmap
	// image/x-xpixmap
	// message/external-body
	// message/http
	// message/news
	// message/partial
	// message/rfc822
	// model/vrml
	// multipart/alternative
	// multipart/byteranges
	// multipart/digest
	// multipart/encrypted
	// multipart/form-data
	// multipart/mixed
	// multipart/parallel
	// multipart/related
	// multipart/report
	// multipart/signed
	// multipart/voice-message
	// text/comma-separated-values
	TEXT_COMMA_SEPERATED_VALUES("text/comma-separated-values"),
	// text/css
	TEXT_CSS("text/css"),
	// text/html
	TEXT_HTML("text/html"),
	// text/javascript
	TEXT_JAVASCRIPT("text/javascript"),
	// text/plain
	TEXT_PLAIN("text/plain"),
	// text/richtext
	// text/rtf
	// text/tab-separated-values
	// text/vnd.wap.wml
	// application/vnd.wap.wmlc
	// text/vnd.wap.wmlscript
	// application/vnd.wap.wmlscriptc
	// text/xml
	// text/xml-external-parsed-entity
	// text/x-setext
	// text/x-sgml
	// text/x-speech
	// video/mpeg
	// video/quicktime
	// video/vnd.vivo
	// video/x-msvideo
	// video/x-sgi-movie
	// workbook/formulaone
	// x-world/x-3dmf
	// x-world/x-vrml

	;

	private final String mimeTypeName;

	private static Map<String, MIMETYPE> name2mimetype;

	MIMETYPE(String mimeTypeName) {
		this.mimeTypeName = mimeTypeName;
		getName2MimeType().put(mimeTypeName, this);
	}

	private Map<String, MIMETYPE> getName2MimeType() {
		if (name2mimetype == null) {
			name2mimetype = new HashMap<>();
		}
		return name2mimetype;
	}

	public String getMimeTypeString() {
		return mimeTypeName;
	}

	public static MIMETYPE getByMimeTypeName(String mimeTypeName) {
		return name2mimetype.get(mimeTypeName);
	}

}
