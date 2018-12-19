/*
 *  To the extent possible under law, Red Hat, Inc. has dedicated all copyright
 *  to this software to the public domain worldwide, pursuant to the CC0 Public
 *  Domain Dedication. This software is distributed without any warranty. 
 *  See <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package org.jboss.example.undertow;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.builder.HandlerBuilder;
import io.undertow.util.Headers;
import org.jboss.logging.Logger;

public class HelloHandler implements HttpHandler {

    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private final Logger log = Logger.getLogger(HelloHandler.class);

    private HttpHandler next;
    private String message;

    public HelloHandler(HttpHandler next) {
        this.message = DEFAULT_MESSAGE;
        this.next = next;
    }

    public HelloHandler(String message, HttpHandler next) {
        this.message = message;
        this.next = next;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            log.info("Running on I/O thread. Let's dispatch to task thread");
            return;
        }
        log.info("handleRequest() is invoked.");
        log.info("requestPath = " + exchange.getRequestPath());
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
        exchange.getResponseSender().send(message);
        exchange.endExchange();
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static class Builder implements HandlerBuilder {

        @Override
        public String name() {
            return "hello";
        }

        @Override
        public Map<String, Class<?>> parameters() {
            return Collections.<String, Class<?>>singletonMap("message", String.class);
        }

        @Override
        public Set<String> requiredParameters() {
            return Collections.singleton("message");
        }

        @Override
        public String defaultParameter() {
            return "message";
        }

        @Override
        public HandlerWrapper build(Map<String, Object> config) {
            return new Wrapper((String) config.get("message"));
        }
    }

    private static class Wrapper implements HandlerWrapper {

        private final String message;

        private Wrapper(String message) {
            this.message = message;
        }

        @Override
        public HttpHandler wrap(HttpHandler handler) {
            return new HelloHandler(message, handler);
        }
    }

}
