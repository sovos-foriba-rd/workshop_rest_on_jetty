package com.foriba.jetty.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AsyncHelper {
    public static AsyncContext getAsyncContext(ServletRequest request, ServletResponse response) {
        AsyncContext asyncContext = null;
        if (request.isAsyncStarted()) {
            asyncContext = request.getAsyncContext();
        }
        else {
            asyncContext = request.startAsync(request, response);
            asyncContext.setTimeout(2000);
        }
        return asyncContext;
    }
}