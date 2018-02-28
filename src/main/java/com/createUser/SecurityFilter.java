package com.createUser;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@SuppressWarnings("restriction")
@Component
public class SecurityFilter implements Filter {

	@Value("${apitoken}")
	private String apitoken;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
		 HttpServletResponse response = (HttpServletResponse) res;
		 String authHeader = request.getHeader("authorization");
		 String decodedAuth = "";
		 if ("OPTIONS".equals(request.getMethod())) {
		        response.setStatus(HttpServletResponse.SC_OK);
		        chain.doFilter(req, res);
		    } else {

		        if (authHeader == null || !authHeader.startsWith("Basic ")) {
		            throw new ServletException("Missing or invalid Authorization header");
		        } 
		        String[] authParts = authHeader.split("\\s+");
		        String authInfo = authParts[1]; 
		        byte[] bytes = null;
		        try {
		            bytes = new BASE64Decoder().decodeBuffer(authInfo);
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		        decodedAuth = new String(bytes);		        
		        if(decodedAuth != null && !decodedAuth.equals(apitoken))
	        	{
	        		throw new ServletException("Invalid token");
	        	}
		        chain.doFilter(req, res);
		    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
