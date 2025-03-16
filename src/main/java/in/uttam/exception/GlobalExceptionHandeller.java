package in.uttam.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandeller {
	@ExceptionHandler(Exception.class)
	public void handelGlobalExceptionHandeller(Exception e) {
		System.out.println(e.getMessage());
	}
}
