/*
 * Copyright 2006 Sxip Identity Corporation
 */

package net.openid.message;

/**
 * @author Marius Scurtescu, Johnny Bufu
 */
public class IndirectError extends Message
{
    private String _returnTo;

    protected IndirectError(String msg, String returnTo)
    {
        this(msg, returnTo, false);
    }
    protected IndirectError(String msg, String returnTo, boolean compatibility)
    {
        set("openid.mode", "error");
        set("openid.error", msg);
        _returnTo = returnTo;

        if (! compatibility)
            set("ns", OPENID2_NS);
    }

    protected IndirectError(ParameterList params)
    {
        super(params);
    }

    public static IndirectError createIndirectError(String msg, String returnTo)
    {
        return createIndirectError(msg, returnTo, false);
    }

    public static IndirectError createIndirectError(String msg, String returnTo,
                                                    boolean compatibility)
    {
        IndirectError err = new IndirectError(msg, returnTo, compatibility);

        if (! err.isValid())
        {
            //todo: log
        }

        return err;
    }

    public static IndirectError createIndirectError(ParameterList params)
    {
        IndirectError err = new IndirectError(params);

        if (! err.isValid())
        {
            //todo: log
        }

        return err;
    }

    public String getReturnTo()
    {
        return _returnTo;
    }

    public void setErrorMsg(String msg)
    {
        set("openid.error", msg);
    }

    public String getErrorMsg()
    {
        return getParameterValue("openid.error");
    }

    public void setContact(String contact)
    {
        set("openid.contact", contact);
    }

    public String getContact()
    {
        return getParameterValue("openid.contact");
    }

    public void setReference(String reference)
    {
        set("openid.reference", reference);
    }

    public String getReference()
    {
        return getParameterValue("openid.reference");
    }

}
