package com.code.model.dto;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToyPageDto extends PageDto{

    private String name;

    private Integer typeId;
    private Integer brandId;
    private Integer materialId;



}

