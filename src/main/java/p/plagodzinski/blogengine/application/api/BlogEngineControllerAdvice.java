package p.plagodzinski.blogengine.application.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import p.plagodzinski.blogengine.application.exceptions.NotFoundPostException;
import p.plagodzinski.blogengine.application.exceptions.NotFoundReviewException;
import p.plagodzinski.blogengine.entity.dto.ExceptionDTO;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class BlogEngineControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogEngineControllerAdvice.class);

    @ExceptionHandler({NotFoundPostException.class})
    public ResponseEntity<ExceptionDTO> handleNotFoundPostException(final NotFoundPostException e) {
        return new ResponseEntity<>(
                buildExceptionDTO("Not found post with id " + e.getPostId()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundReviewException.class})
    public ResponseEntity<ExceptionDTO> handleNotFoundReviewException(
            final NotFoundReviewException e) {
        return new ResponseEntity<>(
                buildExceptionDTO("Not found review with id " + e.getReviewId()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionDTO> handleConstraintViolationException(
            final ConstraintViolationException e) {
        return new ResponseEntity<>(buildExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ExceptionDTO> handleConstrainException(
            final TransactionSystemException ex) {
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            final ConstraintViolationException constraintViolationException =
                    (ConstraintViolationException) ex.getRootCause();
            return handleConstraintViolationException(constraintViolationException);
        } else {
            return new ResponseEntity<>(
                    buildExceptionDTO("Something is wrong with database"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ExceptionDTO buildExceptionDTO(final String errorMessage) {
        LOGGER.error(errorMessage);
        return new ExceptionDTO(errorMessage);
    }
}
