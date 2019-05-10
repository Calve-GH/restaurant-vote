package com.github.calve.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = TempVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TempVoteRestController {
    public static final String REST_URL = "rest/vote";



}
