package issuetracker.issuetracker.domain.issue.dto.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class IssueLabelReqDTO {
    @NotNull
    private long id;
}
