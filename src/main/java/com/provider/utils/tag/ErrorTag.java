package com.provider.utils.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class ErrorTag extends SimpleTagSupport {
    StringWriter stringWriter = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(stringWriter);
        getJspContext().getOut().println(stringWriter.toString());
    }
}
