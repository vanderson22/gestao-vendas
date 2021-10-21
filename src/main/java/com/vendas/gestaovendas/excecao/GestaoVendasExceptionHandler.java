package com.vendas.gestaovendas.excecao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
/**
 * [REMINDER] [COPY FROM DOC'S - ResponseEntityExceptionHandler] A convenient
 * base class for @ControllerAdvice classesthat wish to provide centralized
 * exception handling across all @RequestMapping methods
 * through @ExceptionHandler methods. This base class provides
 * an @ExceptionHandler method for handlinginternal Spring MVC exceptions. This
 * method returns a ResponseEntityfor writing to the response with a message
 * converter,in contrast to DefaultHandlerExceptionResolver which returns a
 * ModelAndView.
 * 
 * If there is no need to write error content to the response body, or whenusing
 * view resolution (e.g., via ContentNegotiatingViewResolver),then
 * DefaultHandlerExceptionResolver is good enough.
 * 
 * Note that in order for an @ControllerAdvice subclass to bedetected,
 * ExceptionHandlerExceptionResolver must be configured. Since:3.2Author:Rossen
 * StoyanchevSee Also:handleException(Exception,
 * WebRequest)org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver
 * 
 **/
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String CONST_VALID_NOT_BLANK = "NotBlank";
	private static final String CONST_VALID_LENGTH = "Length";
	private static final String CONST_VALID_NOT_BLANK_REQUIRED = " é obrigatório";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> errors = gerarListaDeErrors(ex.getBindingResult());

		// Atenção neste trecho de retorno
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	/*
	 * Exception customizada Esta exceção é lançada manualmente+
	 * 
	 * IMPORTANTE, a assinatura do método deve ser somente a exception e o request.
	 * conforme a seguir
	 */

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		String msgU = "Recurso não encontrado";
		String msgDev = ex.toString();

		List<Erro> errors = Arrays.asList(new Erro(msgU, msgDev));

		return handleExceptionInternal(ex, errors, new HttpHeaders(),
				ex.getExpectedSize() > 1 ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST, request);
	}

	private List<Erro> gerarListaDeErrors(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();

		bindingResult.getAllErrors().forEach(fieldError -> {
			String u = tratarMensagemUsuario(fieldError);
			String d = fieldError.toString();

			Erro erro = new Erro(u, d);

			erros.add(erro);

		});

		return erros;
	}

	/**
	 * identifica o tipo do fieldError.
	 * 
	 * Ex: na anotação @NotBlank - clicar com o direito e ver code anotação.
	 * procurar esse techo /** Defines several {@code @NotBlank} constraints on the
	 * same element.
	 *
	 * o código nesse caso é -> NotBlank
	 *
	 */
	private String tratarMensagemUsuario(ObjectError fieldError) {
		if (fieldError != null && fieldError.getCode() != null && fieldError.getCode().equals(CONST_VALID_NOT_BLANK)) {
			return fieldError.getDefaultMessage().concat(CONST_VALID_NOT_BLANK_REQUIRED);
		}

		if (fieldError != null && fieldError.getCode() != null && fieldError.getCode().equals(CONST_VALID_LENGTH)) {
			return fieldError.getDefaultMessage();
		}

		return null;
	}

}
