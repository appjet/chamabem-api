package com.chamabem.interceptor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.annotations.interception.SecurityPrecedence;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import com.chamabem.core.exception.AccessDeniedException;
import com.chamabem.model.User;
import com.chamabem.security.RestSignatureFilter;
import com.chamabem.service.UserService;
import com.chamabem.util.HttpMethod;

@Provider
@SecurityPrecedence
public class AuthenticatorInterceptor implements PreProcessInterceptor {

	@Inject
	private UserService userService;

	@Inject
	private Logger log;
	
	@Context
	private UriInfo info;

	private Timestamp expires = new Timestamp(0);
	private String signatureReceived = "";
	private String apiKey = "";

	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method) throws Failure, WebApplicationException {
		String signatureText = "";

		List<String> listKeys = new ArrayList<String>();

		if (this.isFreeUrl(request.getUri().getAbsolutePath().getPath(), request.getHttpMethod())) {
			return null;
		} else {
			this.validateAuthHeader(request);

			if (request.getHttpMethod().equalsIgnoreCase(HttpMethod.GET)) {
				listKeys.addAll(this.info.getQueryParameters().keySet());
				Collections.sort(listKeys);

				for (String param : listKeys) {
					signatureText += this.info.getQueryParameters().getFirst(param);
				}

			} else {
				JSONObject jsonObject = null;

				try {
					String body = IOUtils.toString(request.getInputStream());

					if(StringUtils.isNotBlank(body))
						jsonObject = new JSONObject(body);

					request.setInputStream(IOUtils.toInputStream(body));

				} catch (Exception e) {
					ServerResponse serverResponse = new ServerResponse();
					serverResponse.setStatus(415);

					return serverResponse;
				}

				for (Iterator<?> iter = jsonObject.keys(); ((Iterator<?>) iter).hasNext();) {
					listKeys.add((String) iter.next());
				}

				Collections.sort(listKeys);

				for (String param : listKeys) {
					try {
						if (param.equalsIgnoreCase("user")) {
							JSONObject userJsonObject = (JSONObject) jsonObject.get(param);

							signatureText += userJsonObject.get("completeName");
							signatureText += userJsonObject.get("username");
							signatureText += userJsonObject.get("phone");
						} else {
							signatureText += (Object) jsonObject.get(param);
						}
					} catch (JSONException e) {
						this.log.info(e.getMessage());
					}
				}
			}

			signatureText += request.getHttpHeaders().getRequestHeaders().getFirst("expires");

			User user = this.userService.findByPrimaryKey(User.class, this.apiKey);

			if (user == null || user.getPrivateKey() == null || user.getPrivateKey() == "") {
				throw new AccessDeniedException("API Key invalida");
			}

			String signature = RestSignatureFilter.signature(signatureText, user.getPrivateKey());

			if (!this.signatureReceived.equals(signature)) {
				throw new AccessDeniedException("Acesso negado");
			}

			if (this.expires.getTime() < new Date().getTime()) {
				throw new AccessDeniedException("Url expirada");
			}

			this.log.info("Autenticacao efetuada");
			return null;
		}
	}

	private boolean isFreeUrl(String path, String httpMethod) {
		if (path.contains("User") && !httpMethod.equalsIgnoreCase(HttpMethod.PUT)){
			return true;
		}

		if(path.contains("User/credentials") && !httpMethod.equalsIgnoreCase(HttpMethod.PUT)){
			return true;
		}

		return false;
	}

	private void validateAuthHeader(HttpRequest request){
		if (request.getHttpHeaders().getRequestHeaders().getFirst("apiKey") == null) {
			throw new AccessDeniedException("API Key invalida");
		} else {
			this.apiKey = request.getHttpHeaders().getRequestHeaders().getFirst("apiKey");
		}

		if (request.getHttpHeaders().getRequestHeaders().getFirst("expires") == null) {
			throw new AccessDeniedException("Tempo de expiracao da URL invalido");
		} else {
			Long time = Long.valueOf(request.getHttpHeaders().getRequestHeaders().getFirst("expires"));
			this.expires.setTime(time);
		}

		if (request.getHttpHeaders().getRequestHeaders().getFirst("signature") == null) {
			throw new AccessDeniedException("Assinatura fornecida invalida");
		} else {
			this.signatureReceived = request.getHttpHeaders().getRequestHeaders().getFirst("signature");
		}

		this.log.info("Auth Header recebido normalmente");
	}
}