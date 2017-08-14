package com.myschool.sms.api.ping;

import com.myschool.sms.api.common.response.envelope.SuccessResponseEnvelope;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokesh on 28/12/16.
 */
@RestController
@RequestMapping("/api/v1")
public class PingController {

    @RequestMapping(value = "/user/ping", method = RequestMethod.GET)
    public SuccessResponseEnvelope pingUser() {
        PingResponse response = new PingResponse("ping pong by user- ");
        return new SuccessResponseEnvelope<>(response);
    }

    @RequestMapping(value = "/admin/ping", method = RequestMethod.GET)
    public SuccessResponseEnvelope pingAdmin() {
        PingResponse response = new PingResponse("admin ping!!");
        return new SuccessResponseEnvelope<>(response);
    }

}
