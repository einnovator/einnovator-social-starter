package org.einnovator.social.client.web;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpStatusCodeException;

public abstract class ControllerBase {
	
	public static final String ATTRIBUTE_REDIRECT_URI = "redirect_uri";

	private final Log logger = LogFactory.getLog(getClass());

	protected String format(HttpStatus status) {
		return status + " " + status.getReasonPhrase();
	}
	
	protected String format(Object... objs) {
		StringBuilder sb = new StringBuilder();
		for (Object obj: objs) {
			if (obj!=null) {
				if (sb.length()>0) {
					sb.append(" ");
				}
				sb.append(obj);				
			}
		}
		return sb.toString();
	}

	protected <T> ResponseEntity<T> status(String msg, Throwable t, HttpServletResponse response, Object... objs) {
		HttpStatus status = null;
		if (t instanceof HttpStatusCodeException) {
			status = ((HttpStatusCodeException) t).getStatusCode();
		}
		if (status==null) {
			status = HttpStatus.BAD_REQUEST;
		}
		response.setStatus(status.value());
		logger.error(String.format("%s: %s %s %s", msg, format(status), t, format(objs)));
		return ResponseEntity.status(status).build();
	}

	protected void info(String msg, Object... objs) {
		if (logger.isInfoEnabled()) {
			logger.info(String.format("%s: %s", msg, format(objs)));			
		}
	}

	protected void debug(String msg, Object... objs) {
		if (logger.isDebugEnabled()) {
			logger.info(String.format("%s: %s", msg, format(objs)));			
		}
	}

	protected <T> ResponseEntity<T> status(String msg, HttpStatus status, HttpServletResponse response, Object... objs) {
		response.setStatus(status.value());
		if (status.is2xxSuccessful() || status.is3xxRedirection()) {
			logger.info(String.format("%s %s %s", msg, format(status), format(objs)));
		} else {
			logger.error(String.format("%s %s %s", msg, format(status), format(objs)));			
		}
		return ResponseEntity.status(status).build();
	}

	protected <T> ResponseEntity<T> unauthorized(String msg, HttpServletResponse response, Object... objs) {
		return status(msg, HttpStatus.UNAUTHORIZED, response, objs);		
	}
	
	protected <T> ResponseEntity<T> forbidden(String msg, HttpServletResponse response, Object... objs) {
		return status(msg, HttpStatus.FORBIDDEN, response, objs);		
	}

	protected <T> ResponseEntity<T> notfound(String msg, HttpServletResponse response, Object... objs) {
		return status(msg, HttpStatus.NOT_FOUND, response, objs);		
	}
	
	protected <T> ResponseEntity<T> badrequest(String msg, HttpServletResponse response, Object... objs) {
		return status(msg, HttpStatus.BAD_REQUEST, response, objs);		
	}

	protected <T> ResponseEntity<T> ok(T result, String msg, HttpServletResponse response, Object... objs) {
		status(msg, HttpStatus.OK, response, objs);		
		return new ResponseEntity<T>(result, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> nocontent(String msg, HttpServletResponse response, Object... objs) {
		return status(msg, HttpStatus.NO_CONTENT, response, objs);		
	}

	protected <T> ResponseEntity<T> created(URI location, String msg, HttpServletResponse response, Object... objs) {
		status(msg, HttpStatus.CREATED, response, objs);	
		return ResponseEntity.created(location).build();
	}

	public static boolean adminAccess(HttpServletRequest request, Model model) {
		boolean _admin = request.getRequestURI().indexOf("/admin")>=0;
		model.addAttribute("_admin", _admin);		
		return _admin;
	}

}
