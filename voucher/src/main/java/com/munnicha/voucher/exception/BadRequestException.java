package com.munnicha.voucher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author munnicha
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad voucher creation request.")
public class BadRequestException extends Exception {

}
