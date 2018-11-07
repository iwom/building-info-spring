package com.builder.demo.exception;

import com.builder.demo.exception.service.FloorServiceException;
import com.builder.demo.exception.service.RoomServiceException;
import com.builder.demo.model.error.ErrorMessage;
import com.builder.demo.exception.service.BuildingServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(value = {BuildingServiceException.class})
    public ResponseEntity<Object> handleBuildingServiceException(
            BuildingServiceException buildingEx, WebRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(buildingEx.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), buildingEx.getHttpStatus());
    }

    @ExceptionHandler(value = {FloorServiceException.class})
    public ResponseEntity<Object> handleFloorServiceException(
            FloorServiceException floorEx, WebRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(floorEx.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), floorEx.getHttpStatus());
    }

    @ExceptionHandler(value = {RoomServiceException.class})
    public ResponseEntity<Object> handleRoomServiceException(
            RoomServiceException roomEx, WebRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(roomEx.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), roomEx.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(
            Exception ex, WebRequest request
    ) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}