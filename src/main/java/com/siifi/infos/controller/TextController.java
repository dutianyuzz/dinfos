package com.siifi.infos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/7/17.
 */
@Controller
@RequestMapping("/infos")
public class TextController {
    @RequestMapping(value="/study")
    public String text(){
        return "lnstudy/study";
    }
}
