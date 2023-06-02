package issuetracker.issuetracker.domain.label;


import issuetracker.issuetracker.domain.issue.IssueAttachedLabel;
import issuetracker.issuetracker.domain.label.dto.LabelDTO;
import issuetracker.issuetracker.domain.label.dto.LabelFilterDTO;
import issuetracker.issuetracker.domain.label.dto.LabelListDTO;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {

    @Query("SELECT " +
            "label.label_id AS id, " +
            "label.title AS title, " +
            "label.description AS description, " +
            "label.font_color, " +
            "label.background_color AS `background_color` " +
            "FROM label")
    List<LabelListDTO> getLabelList();


    @Query("SELECT " +
            "label.label_id AS id, " +
            "label.title AS title, " +
            "label.background_color AS background_color " +
            "FROM label")
    List<LabelFilterDTO> getLabelFilter();

    @Query("SELECT l.* " +
            "FROM label l " +
            "JOIN label_list ll  " +
            "ON ll.issue_id = :id")
    List<Label> findAllAttachedLabelByIssues(@Param("id") Long id);

    @Modifying
    @Query("DELETE ll FROM label_list ll JOIN label l WHERE ll.label_id = :labelId")
    void deleteAllIssueInLabelAndLabel(@Param("labelId") long labelId);
}
