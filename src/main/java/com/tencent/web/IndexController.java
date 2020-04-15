package com.tencent.web;

import com.tencent.BlogNotFoundException;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index()  {

        String blog = null;
        if (blog == null) {
            throw new BlogNotFoundException("博客找不到");
        }
        return "index";
    }

}
