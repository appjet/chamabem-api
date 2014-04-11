package com.chamabem.ws;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.chamabem.core.exception.ValidationException;
import com.chamabem.dto.StatusDTO;
import com.chamabem.model.TaxiCall;
import com.chamabem.model.User;
import com.chamabem.request.StatusRequest;
import com.chamabem.service.CallService;
import com.chamabem.util.ResponseUtils;
import com.chamabem.util.Validation;

@Path("Call")
@Produces(MediaType.APPLICATION_JSON)
public class WSCall {

	@Inject
	private CallService callService;

	@Context
	org.jboss.resteasy.spi.HttpResponse response;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public Response register(TaxiCall taxiCall,
    						 @HeaderParam("apiKey") String apiKey){

		User user = this.callService.findByPrimaryKey(User.class, apiKey);
		taxiCall.setUser(user);
		taxiCall.setStatus("P");

		callService.includeCall(taxiCall);
	    return Response.status(200).entity("Registrado com sucesso").build();
    }

	@GET
	@Path("/status")
	public StatusDTO viewStatus(@QueryParam("callId") String callId) throws ValidationException{

		TaxiCall taxiCall = new TaxiCall();
		
		List<Validation> paramValidations = new ArrayList<Validation>();

		if(StringUtils.isBlank(callId))
			paramValidations.add(new Validation("O campo 'callId' não pode ser nulo."));
		
		ResponseUtils.validate(paramValidations);
		
		if(StringUtils.isNotBlank(callId)){
			taxiCall = this.callService.findByPrimaryKey(TaxiCall.class, callId);
		}

		if(StringUtils.isNotBlank(taxiCall.getDriverName()) 
				&& StringUtils.isNotBlank(taxiCall.getTimeApproximate())
				&& StringUtils.isNotBlank(taxiCall.getMeetingPoint())){
			
			StatusDTO statusDTO = new StatusDTO();
			statusDTO.setDriverName(taxiCall.getDriverName());
			statusDTO.setTimeApproximate(taxiCall.getTimeApproximate());
			statusDTO.setMeetingPoint(taxiCall.getMeetingPoint());
			
			return statusDTO;
		}

		return null;
	}
	
	@PUT
	@Path("/status")
	public Response updateStatus(StatusRequest statusRequest){
		TaxiCall taxiCall = null;

		if(StringUtils.isNotBlank(statusRequest.getCallId())){
			taxiCall = this.callService.findByPrimaryKey(TaxiCall.class, statusRequest.getCallId());
		}

		if(taxiCall != null){
			taxiCall.setStatus(statusRequest.getStatus());
			this.callService.updateStatus(taxiCall);
		}

		return Response.status(200).entity("Status alterado com sucesso").build();
	}
}