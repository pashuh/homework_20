package lombok;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
    private String email;
    @JsonProperty("last_name")
    private String lastName;
    private String token;
    private String job;
    private String password;
}
