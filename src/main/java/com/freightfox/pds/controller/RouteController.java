package com.freightfox.pds.controller;


import com.freightfox.pds.constants.StatusCode;
import com.freightfox.pds.exception.ErrorFieldsMessage;
import com.freightfox.pds.exception.ErrorMessage;
import com.freightfox.pds.model.RouteRequest;
import com.freightfox.pds.response.RouteResult;
import com.freightfox.pds.service.RouteFetcherService;
import com.freightfox.pds.util.BaseResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class RouteController {


    @Autowired
    private RouteFetcherService routeFetcherService;


    @Operation(summary = "[V1] PinCode Distance")
    @ApiResponses(value = {
            @ApiResponse(responseCode =  "201", description = "Success", content = @Content(schema = @Schema(implementation = RouteResult.class)))
            , @ApiResponse(responseCode = "400", description = "Bad data", content = @Content(schema = @Schema(implementation = ErrorFieldsMessage.class)))
            , @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    }
    )
    @PostMapping(value = "/v1/route")
    public ResponseEntity fetchRouteHandler(@Valid @RequestBody final RouteRequest routeRequest) {
        try {
            if (ObjectUtils.isEmpty(routeRequest)) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(routeFetcherService.fetchRouteBetweenTwoPinCode(routeRequest));
        } catch (Exception e) {
            log.error("Error while fetching rout data: {}", e.getMessage());
            return ResponseEntity.ok(BaseResponseUtil.createBaseResponse(StatusCode.INTERNAL_SERVER_ERROR));
        }
    }


}
