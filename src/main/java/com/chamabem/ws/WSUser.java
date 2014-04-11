package com.chamabem.ws;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONObject;

import com.chamabem.core.exception.AccessDeniedException;
import com.chamabem.model.User;
import com.chamabem.request.RegIdRequest;
import com.chamabem.security.GenerateRSAKeys;
import com.chamabem.service.UserService;

@Path("User")
@Produces(MediaType.APPLICATION_JSON)
public class WSUser {
	
	@Inject
	private UserService userService;

	@GET
    public Response authenticate(@QueryParam("username") String username,
    					  		 @QueryParam("password") String password) {
		
		User user = this.userService.findUserByCredentials(username, password);

        if(user != null){
        	return Response.status(200).entity("Usuario encontrado.").build();
        } else {
        	return Response.status(401).entity("Usuário não existe.").build();
        }
    }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) throws UnsupportedEncodingException {
		User userSearched = this.userService.findUserByUsername(user.getUsername());

		if(userSearched != null){
			return Response.status(401).entity("Usuario ja existe").build();
		} else {
			HashMap<String, String> keys = new GenerateRSAKeys().getKeys();

			String privateKey = keys.get("privateKey");
			String publicKey = keys.get("publicKey"); 
			user.setPrivateKey(privateKey);
			user.setPublicKey(GenerateRSAKeys.cleanApiKey(publicKey));

	        this.userService.register(user);

	        return Response.status(200).entity("Registrado com sucesso").build();
		}
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User user){
		User userSearched = this.userService.findUserByUsername(user.getUsername());

		if(userSearched == null){
			return Response.status(401).entity("Usuario não existe").build();
		} else {
	        this.userService.update(user);

	        return Response.status(200).entity("Atualizado com sucesso").build();
		}
	}

	@GET
	@Path("credentials")
    public Response credentials(@QueryParam("username") String username,
    					  		@QueryParam("password") String password) {

		User user = this.userService.findUserByCredentials(username, password);

        if(user != null){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("privateKey", user.getPrivateKey());
        	map.put("publicKey", user.getPublicKey());
        	map.put("name", user.getCompleteName());
        	map.put("phone", user.getPhone());
        	map.put("username", user.getUsername());

        	return Response.status(200).entity(new JSONObject(map).toString()).build();
        } else {
        	return Response.status(401).entity("Usuário não existe.").build();
        }
    }
	
	
	@PUT
	@Path("/regId")
	public Response updateRegId(RegIdRequest regIdRequest){
		User user = null;

		if(StringUtils.isNotBlank(regIdRequest.getUsername()) && StringUtils.isNotBlank(regIdRequest.getPassword())){
			user = this.userService.findUserByCredentials(regIdRequest.getUsername(), regIdRequest.getPassword());
		} else {
			throw new AccessDeniedException("Usuario ou Senha incorretos!");
		}

		if(user != null){
			user.setRegId(regIdRequest.getRegId());
			this.userService.updateUser(user);
		} else {
			throw new AccessDeniedException("Usuario nao encontrado!");
		}

		return Response.status(200).entity("RegId atualizado com sucesso").build();
	}
}