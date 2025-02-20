package com.everysesac.backend.domain.post.dto.request;

import com.everysesac.backend.domain.post.entity.PostType;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String titleKeyword;

    private String contentKeyword;

    private PostType postType;

    private String sortDirection;

    private String sortField;

    public Pageable getPageable() {
        return PageRequest.of(this.page - 1, this.size);
    }

    private String link;

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        if (link == null) {
            builder.append("page=").append(this.page);
            builder.append("&size=").append(this.size);
            linkBuilderAdd(titleKeyword, builder, "$titleKeyword=");
            linkBuilderAdd(contentKeyword, builder, "&contentKeyword=");
            if (postType != null) {
                linkBuilderAdd(postType.toString(), builder, "&postType=");
            }
            linkBuilderAdd(sortField, builder, "&sortField=");
            linkBuilderAdd(sortDirection, builder, "&sortDirection=");
        }
        return builder.toString();
    }

    private void linkBuilderAdd(String sortField, StringBuilder builder, String str) {
        if (StringUtils.hasText(sortField)) {
            builder.append(str).append(sortField);
        }
    }


}
