package dcomp.lpweb.vendas.api.controller.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class HeaderLocationEvento extends ApplicationEvent {

    private final HttpServletResponse response;
    private final Integer id;

    public HeaderLocationEvento(Object source, HttpServletResponse response, Integer id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
