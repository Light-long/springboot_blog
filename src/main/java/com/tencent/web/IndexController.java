package com.tencent.web;

import com.tencent.BlogNotFoundException;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

//        测试异常
//        String blog = null;
//        if (blog == null) {
//            throw new BlogNotFoundException("博客找不到");
//        }
//        return "index";
//    }
        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/admin/blogs")
    public String adminblogs() {
        return "admin/blogs-input";
    }
}
