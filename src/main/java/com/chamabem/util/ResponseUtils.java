package com.chamabem.util;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.InvalidValue;
import org.jboss.resteasy.spi.HttpResponse;

import com.chamabem.core.exception.ValidationException;

@Provider
public class ResponseUtils {

	@Inject
	private static Logger log;

	public static void setResponseHeaders(HttpResponse response) throws IOException {
//		log.info("Entrou: setResponseHeaders()");
//		response.getOutputHeaders().putSingle("page-number", search.getPageNumber());
//		response.getOutputHeaders().putSingle("page-size", search.getPageSize());
//		response.getOutputHeaders().putSingle("total-records", search.getTotalRecords());
//		response.getOutputHeaders().putSingle("total-pages", search.getTotalPages());
//		log.info("Setou HTTP Headers");
//		// se request tentou buscar uma pagina maior que o que realmente existe,
//		// setar HTTP CODE 204: "No content"
//		/*
//		 * if (search.getPageNumber() > search.getTotalPages()) {
//		 * log.debug("Tentou buscar uma pagina inexistente. Setando HTTP Code 204"
//		 * ); response.sendError(204); }
//		 */
//		log.info("Saiu: setResponseHeaders()");
	}

//	public static <E> List<E> build(TBNativeQuery<E> query, org.jboss.resteasy.spi.HttpResponse response) {
//		if (query.getResultCount() != null && query.getResultCount() > 0) {
//			response.getOutputHeaders().putSingle("total-count", query.getResultCount());
//			response.getOutputHeaders().putSingle("current-page", query.getPage());
//			response.getOutputHeaders().putSingle("total-pages", query.getPageCount());
//			response.getOutputHeaders().putSingle("page-size", query.getMaxResults());
//		}
//		return query.getResultList();
//	}
//
//	public static <E> List<E> build(TBEntityQuery<E> query, org.jboss.resteasy.spi.HttpResponse response) {
//		if (query.getResultCount() != null && query.getResultCount() > 0) {
//			response.getOutputHeaders().putSingle("total-count", query.getResultCount());
//			response.getOutputHeaders().putSingle("current-page", query.getPage());
//			response.getOutputHeaders().putSingle("total-pages", query.getPageCount());
//			response.getOutputHeaders().putSingle("page-size", query.getMaxResults());
//			;
//		}
//		return query.getResultList();
//	}

	public static void validate(InvalidValue[] invalidValues) throws ValidationException {

		if (invalidValues.length == 0) {
			return;
		}

		ValidationResponse validationResponse = new ValidationResponse();

		for (InvalidValue invalidValue : invalidValues) {
			validationResponse.add(new Validation(invalidValue.getPropertyPath(), invalidValue.getMessage()));
		}

		throw new ValidationException(validationResponse);
	}

	public static void validate(List<Validation> validationItems) throws ValidationException {

		if (validationItems.isEmpty()) {
			return;
		}

		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setValidationItems(validationItems);

		throw new ValidationException(validationResponse);
	}

	public static void validate(InvalidValue[] invalidValues, List<Validation> validationItems) throws ValidationException {

		if (validationItems.isEmpty() && invalidValues.length == 0) {
			return;
		}

		for (InvalidValue invalidValue : invalidValues) {
			validationItems.add(new Validation(invalidValue.getPropertyPath(), invalidValue.getMessage()));
		}

		ValidationResponse validationResponse = new ValidationResponse();
		validationResponse.setValidationItems(validationItems);

		throw new ValidationException(validationResponse);
	}
}
