package p.plagodzinski.blogengine.entity.dto;

public class ExceptionDTO {

    private final String errormsg;

    public ExceptionDTO(final String errormsg) {
        this.errormsg = errormsg;
    }

    public String getErrormsg() {
        return errormsg;
    }
}
