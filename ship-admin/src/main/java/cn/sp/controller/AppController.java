package cn.sp.controller;

import cn.sp.pojo.ChangeStatusDTO;
import cn.sp.pojo.dto.RegisterAppDTO;
import cn.sp.pojo.dto.UnregisterAppDTO;
import cn.sp.pojo.vo.AppVO;
import cn.sp.pojo.vo.Result;
import cn.sp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 2YSP on 2020/12/23
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @ResponseBody
    @PostMapping("/register")
    public void register(@RequestBody @Validated RegisterAppDTO registerAppDTO) {
        appService.register(registerAppDTO);
    }

    @ResponseBody
    @PostMapping("/unregister")
    public void unregister(@RequestBody UnregisterAppDTO unregisterAppDTO) {
        appService.unregister(unregisterAppDTO);
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        List<AppVO> appVOList = appService.getList();
        model.put("appVOList", appVOList);
        return "applist";
    }

    @ResponseBody
    @PutMapping("/status")
    public Result updateEnabled(@RequestBody ChangeStatusDTO statusDTO){
        appService.updateEnabled(statusDTO);
        return Result.success();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")Integer id){
        appService.delete(id);
        return Result.success();
    }

    @ResponseBody
    @GetMapping("/all")
    public Result<List<AppVO>> getAppList(){
        return Result.success(appService.getList());
    }
}
