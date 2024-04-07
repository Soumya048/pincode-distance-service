package com.freightfox.pds.service;


import com.freightfox.pds.model.RouteRequest;

public interface RouteFetcherService {

    Object fetchRouteBetweenTwoPinCode(RouteRequest request);

}
