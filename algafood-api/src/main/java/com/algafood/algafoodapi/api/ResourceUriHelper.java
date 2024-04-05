package com.algafood.algafoodapi.api;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

@UtilityClass // class final, error se declara um construtor, métodos static
public class ResourceUriHelper {

    public static void addUriComponentsBUilder(Object resouceId) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(resouceId).toUri();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
