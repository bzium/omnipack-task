package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ExceptionDTO.class)
@JsonDeserialize(as = ExceptionDTO.class)
public interface ExceptionDTO {
    String getErrorMsg();
}
