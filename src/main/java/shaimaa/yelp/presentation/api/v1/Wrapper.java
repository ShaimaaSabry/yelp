package shaimaa.yelp.presentation.api.v1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Wrapper<T> {
    @NotNull
    @Valid
    private T data;
}
