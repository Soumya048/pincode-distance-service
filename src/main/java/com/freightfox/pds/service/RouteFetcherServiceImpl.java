package com.freightfox.pds.service;


import com.freightfox.pds.constants.StatusCode;
import com.freightfox.pds.model.LatLongData;
import com.freightfox.pds.model.RouteRequest;
import com.freightfox.pds.response.RouteResult;
import com.freightfox.pds.util.BaseResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RouteFetcherServiceImpl implements RouteFetcherService {

    private static final String directionsApiUrl = "https://maps.googleapis.com/maps/api/directions/json";
    private static final String geocodingApiUrl = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String  mode = "driving";
    private static final String apiKey = "AIzaSyCkmF-sctglWOHOVjY9eYzfqHnBpC8BCsw";

    @Autowired
    private AsyncCallService asyncCallService;


    /**
     * This method is the main rout fetching method
     * first It will fetch the lat/log for  both pinCode and save the data in db in async method call
     * Next step it will call google map api for distance and time data and return the res. it will save this data as well in async method call
     * @param request
     * @return
     */
    @Override
    public RouteResult fetchRouteBetweenTwoPinCode(RouteRequest request) {
        RouteResult response;
        try {
            String fromPinCode = request.getFromPinCode();
            String toPinCode = request.getToPinCode();

            if (fromPinCode.equalsIgnoreCase(toPinCode)) {
                return BaseResponseUtil.createBaseResponse(new RouteResult()
                        , StatusCode.BAD_REQUEST, "Both pincode should not be equal");
            }

            // checking db for existing rout data
            response = getRoutFromDB(fromPinCode, toPinCode, mode);
            if (ObjectUtils.isNotEmpty(response)) {
                return BaseResponseUtil.createBaseResponse(response, StatusCode.OK);
            }

            // checking db for existing lat/long data
            LatLongData fromPinCodeLatLong = getLatLongDataFromDB(fromPinCode);
            LatLongData toPinCodeLatLong = getLatLongDataFromDB(toPinCode);

            if (ObjectUtils.isEmpty(fromPinCodeLatLong)) {
                fromPinCodeLatLong = fetchLatLongByPincode(fromPinCode);
                asyncCallService.savePinCodeLatLong(fromPinCode, fromPinCodeLatLong);
            }
            if (ObjectUtils.isEmpty(toPinCodeLatLong)) {
                toPinCodeLatLong = fetchLatLongByPincode(toPinCode);
                asyncCallService.savePinCodeLatLong(toPinCode, toPinCodeLatLong);
            }

            //If not there in db new api call to get the rout
            if (ObjectUtils.isNotEmpty(fromPinCodeLatLong) && ObjectUtils.isNotEmpty(toPinCodeLatLong)) {
                response = fetchRouteFromGoogleMaps(fromPinCodeLatLong, toPinCodeLatLong, mode);
                // saving the result
                asyncCallService.saveRoutForTwoPinCode(fromPinCode, toPinCode, response);
                return BaseResponseUtil.createBaseResponse(new RouteResult(), StatusCode.OK);
            } else {
                return BaseResponseUtil.createBaseResponse(new RouteResult(), StatusCode.NO_DATA);
            }
        } catch (Exception e) {
            log.error("Error while fetching rout: {}", e.getMessage());
            return BaseResponseUtil.createBaseResponse(new RouteResult(), StatusCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This medthod will get lat log for pinCode by api call
     * @param pinCode
     * @return
     */
    private LatLongData fetchLatLongByPincode(String pinCode) {
        return null;
    }


    /**
     * This method will fetch the rout between two loc
     * @param from
     * @param to
     * @return
     */
    private RouteResult fetchRouteFromGoogleMaps(LatLongData from, LatLongData to, String mode) {
        return null;
    }


    /**
     * This method will get the lat/long from db if exist
     * @param pinCode
     * @return
     */
    private LatLongData getLatLongDataFromDB(String pinCode) {
        return null;
    }

    /**
     * This method will check db for two pinCode if there is any existing data is there or not
     * @param fromPinCode
     * @param toPinCode
     * @param mode
     * @return
     */
    private RouteResult getRoutFromDB(String fromPinCode, String toPinCode, String mode) {
        return null;
    }


}
