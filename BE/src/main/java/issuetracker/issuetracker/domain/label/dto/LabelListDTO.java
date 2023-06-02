package issuetracker.issuetracker.domain.label.dto;

import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LabelListDTO {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String description;
    @Column("font_color")
    private String fontColor;
    @NotNull
    private String backgroundColor;
}
