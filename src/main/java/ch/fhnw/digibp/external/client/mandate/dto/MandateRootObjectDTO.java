package ch.fhnw.digibp.external.client.mandate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mandate"
})
public class MandateRootObjectDTO {
    private MandateDTO mandate;

    @JsonProperty("mandate")
    public MandateDTO getMandate() {
        return mandate;
    }

    @JsonProperty("mandate")
    public void setMandate(MandateDTO mandate) {
        this.mandate = mandate;
    }
}