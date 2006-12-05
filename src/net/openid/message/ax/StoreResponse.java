/*
 * Copyright 2006 Sxip Identity Corporation
 */

package net.openid.message.ax;

import net.openid.message.ParameterList;
import net.openid.message.MessageException;
import net.openid.message.Parameter;

import java.util.Iterator;

/**
 * Implements the extension for Attribute Exchange store responses.
 *
 * @author Marius Scurtescu, Johnny Bufu
 */
public class StoreResponse extends AxMessage
{
    /**
     * Constructs a Store Response with an empty parameter list.
     */
    public StoreResponse()
    {
    }

    /**
     * Constructs a StoreResponse from a parameter list.
     * <p>
     * The parameter list should be extracted from a received message with the
     * getExtensionParams method of the Message class.
     */
    public StoreResponse(ParameterList params) throws MessageException
    {
        super(params);

        if (! isValid())
            throw new MessageException("Invalid parameters for a store response");
    }

    /**
     * Marks the Store Response as a failure, by setting the appropirate
     * parameters.
     *
     * @param       description     Describes the error condition leading to
     *                              the failure response
     */
    public void setFailure(String description)
    {
        _parameters.set(new Parameter("status", "failure"));

        if (description != null)
            _parameters.set(new Parameter("status.description", description));
    }

    /**
     * Returns true if the Store Response is a failure message, true if it is
     * a success response.
     */
    public boolean hasFailed()
    {
        return _parameters.hasParameter("status")  &&
                "failure".equals(_parameters.getParameterValue("status"));
    }

    /**
     * Gets the status of the Store Response if the 'status' parameter is part
     * of the response, or null otherwise.
     */
    public String getStatus()
    {
        return _parameters.hasParameter("status")  ?
                _parameters.getParameterValue("status") : null;
    }

    /**
     * Gets the status of the Store Response if the 'status.description'
     * parameter is part of the response, or null otherwise.
     */
    public String getStatusDescription()
    {
        return _parameters.hasParameter("status.description") ?
                _parameters.getParameterValue("status.description") : null;
    }

    /**
     * Checks the validity of the extension.
     * <p>
     * Used when constructing a extension from a parameter list.
     *
     * @return      True if the extension is valid, false otherwise.
     */
    private boolean isValid()
    {
        if ( getStatusDescription() != null && getStatus() == null)
            return false;

        Iterator it = _parameters.getParameters().iterator();
        while (it.hasNext())
        {
            Parameter param = (Parameter) it.next();
            String paramName = param.getKey();

            if (! paramName.equals("status") &&
                    ! paramName.equals("status.description"))
                return false;

            if ( paramName.equals("status") &&
                    ! "failure".equals(param.getValue()) )
                return false;
        }

        return true;
    }

}