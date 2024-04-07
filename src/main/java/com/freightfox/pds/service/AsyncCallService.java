package com.freightfox.pds.service;

import com.freightfox.pds.model.LatLongData;
import com.freightfox.pds.response.RouteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncCallService {


    /**
     * saves the pincode lat/long
     * @param pinCode
     * @param latLongData
     */
    @Async
    public void savePinCodeLatLong(String pinCode, LatLongData latLongData) {

    }


    /**
     * This method will save the Map Api result in db
     * @param from
     * @param to
     * @param result
     */
    @Async
    public void saveRoutForTwoPinCode(String from, String to, RouteResult result) {

    }


}
