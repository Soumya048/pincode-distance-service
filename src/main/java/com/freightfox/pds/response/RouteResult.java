package com.freightfox.pds.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteResult extends BaseResponse {

    private String distance;
    private String duration;

}
