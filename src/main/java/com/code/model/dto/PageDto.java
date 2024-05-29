package com.code.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author xiaoxin
 * @version 1.0
 * @date 2023/12/29 14:27
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageDto {
    private Integer pageSize;
    private Integer pageNumber;
}
