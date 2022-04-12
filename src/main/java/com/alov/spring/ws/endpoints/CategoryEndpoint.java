package com.alov.spring.ws.endpoints;

import com.alov.spring.ws.services.CategoryService;
import com.alov.spring.ws.soap.categories.GetCategoryByTitleRequest;
import com.alov.spring.ws.soap.categories.GetCategoryByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {

    private static final String NAMESPACE_URI = "http://www.alov.com/spring/ws/categories";
    private final CategoryService categoryService;

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.alov.com/spring/ws/groups">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByTitleRequest>
                    <f:title>Food</f:title>
                </f:getCategoryByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByTitleRequest")
    @ResponsePayload
    @Transactional
    public GetCategoryByTitleResponse getCategoryByTitle(@RequestPayload GetCategoryByTitleRequest request) {
        GetCategoryByTitleResponse response = new GetCategoryByTitleResponse();
        response.setCategory(categoryService.getByTitle(request.getTitle()));
        return response;
    }
}

