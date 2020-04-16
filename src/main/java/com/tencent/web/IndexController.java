package com.tencent.web;

import com.tencent.BlogNotFoundException;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable("id") Integer id,@PathVariable("name") String name) {

//        测试异常
//        String blog = null;
//        if (blog == null) {
//            throw new BlogNotFoundException("博客找不到");
//        }
//        return "index";
//    }
        System.out.println("--------index------");
        System.out.println(id+":"+name);
        return "index";
    }
}
