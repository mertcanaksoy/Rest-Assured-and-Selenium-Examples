package functionalTests.testDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String name;
    private String employeeID;
    private String department;
    private String employeeType;
    private String location;
    private String mail;
    private String title;
}