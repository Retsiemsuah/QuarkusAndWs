package de.scheffler.logic.impl.common.validation;

import org.apache.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.util.LinkedList;

public abstract class ResponseEndpointValidator {
    Response errorResponse=null;
    Integer expectedParameterCount=0;
    LinkedList<Class> expectedParameterClasses;
    boolean allNotNullabele=false;

    public ResponseEndpointValidator afterValidationOf(Object...args){
        paramsAreFormallyValid(args);
        if(errorResponse==null) {
            paramsAreLogicallyValid(args);
        }
        return this;
    }
    private void paramsAreFormallyValid(Object[] args){
        if(expectedParameterCount==null)
            return;
        checkParamCount(args);
        checkParamTypes(args);
        if(allNotNullabele){
            checkParamsAreNull(args);
        }
    }

    private void checkParamCount(Object[] args) {
        if(args.length!=expectedParameterCount) {
            setErrorResponseToIternalServerErrorOccured();
        }
    }
    private void checkParamTypes(Object[] args) {
        if(expectedParameterClasses.isEmpty()){
            return;
        }
        int i = 0;
        for(Class expectedParamClass : expectedParameterClasses){
            if(args[i].getClass() != expectedParamClass) {
                setErrorResponseToIternalServerErrorOccured();
                return;
            }
        }
    }

    protected void checkParamsAreNull(Object[] args){
        for(Object arg: args)
            if(arg==null)
                setErrorResponseToIternalServerErrorOccured();
    }

    protected abstract ResponseEndpointValidator paramsAreLogicallyValid(Object[] args);

    protected  void setErrorResponseToIternalServerErrorOccured(){
        setErrorResponse(Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build());
    }
    public void setErrorResponse(Response errorResponse) {
        this.errorResponse = errorResponse;
    }

    public Response getErrorResponse() {
        return errorResponse;
    }

    public boolean preparedErrorResponseIsNotEmpty(){
        return errorResponse!=null;
    }

    public ResponseEndpointValidator withExpectedParameterCountOf(int expectedParameterCount) {
        this.expectedParameterCount = expectedParameterCount;
        return this;
    }

    public ResponseEndpointValidator ofClasses(LinkedList<Class> parameterClasses) {
        this.expectedParameterClasses = parameterClasses;
        return this;
    }

    public ResponseEndpointValidator allNotNullable() {
        this.allNotNullabele = true;
        return this;
    }

    public  ResponseEndpointValidator build(){
        return this;
    }
}