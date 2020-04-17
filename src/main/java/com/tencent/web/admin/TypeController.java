package com.tencent.web.admin;

import com.tencent.entity.Type;
import com.tencent.service.TypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

//增加或删除或修改数据，返回list页面是都应该重定向

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //查询所有type,并显示在admin/types页面
    @GetMapping("/types")
    public String typeLists(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable, Model model) {

        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    //跳转到添加页面
    @GetMapping("types/input")
    public String toAddPage(Model model) {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //跳转到编辑页面
    @GetMapping("/types/{id}/input")
    public String toEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //新增type类型，验证是否为空，是否存在，然后跳转到admin/types页面
    @PostMapping("/types")
    public String addType(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //检查添加的type 是否重复
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name","nameRepeat","添加的分类已经存在了了");
        }

        //后端校验传入的type，如果为空，则出现异常
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("msg","操作失败");
        } else {
            attributes.addFlashAttribute("msg","操作成功");
        }
        return "redirect:/admin/types";
    }

    //修改type类型
    @PostMapping("/types/{id}")
    public String updateType(@Valid Type type,BindingResult result,@PathVariable("id") Long id,RedirectAttributes attributes) throws NotFoundException {
        //检查添加的type 是否重复
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name","nameRepeat","添加的分类已经存在了");
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }
        Type t = typeService.update(id, type);
        if (t == null) {
            attributes.addFlashAttribute("msg","修改失败");
        }else  {
            attributes.addFlashAttribute("msg","修改成功");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/types";
    }

}
