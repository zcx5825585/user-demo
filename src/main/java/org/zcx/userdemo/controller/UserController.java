package org.zcx.userdemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.web.bind.annotation.*;
import org.zcx.userdemo.VO.User;
import org.zcx.userdemo.VO.UserQuery;
import org.zcx.userdemo.service.UserService;
import org.zcx.userdemo.util.HttpResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("search")
    public HttpResult search(UserQuery query,
                             @RequestParam(name = "page", required = false, defaultValue = "1") int pageNum,
                             @RequestParam(name = "rows", required = false, defaultValue = "2") int pageSize) {
        Map<String, Object> searchInfo = new HashMap<String, Object>();
        if (query != null) {
            searchInfo.put("query", query);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.search(searchInfo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return HttpResult.success(pageInfo);
    }

    @DeleteMapping("/{id}")
    public HttpResult delete(@PathVariable("id") Long id) {
        userService.deleteByPrimaryKey(id);
        return HttpResult.success();
    }

    @PostMapping
    public HttpResult insert(@RequestBody User record) {
        if (record.getId() != null) {
            return HttpResult.failed();
        }
        if (StringUtil.isEmpty(record.getUserName())) {
            return HttpResult.failed("用户名不能为空");
        }
        userService.insert(record);
        return HttpResult.success();
    }

    @PutMapping(("/{id}"))
    public HttpResult update(@PathVariable("id") Long id, @RequestBody User record) {
        if (StringUtil.isEmpty(record.getUserName())) {
            return HttpResult.failed("用户名不能为空");
        }
        record.setId(id);
        userService.updateByPrimaryKey(record);
        return HttpResult.success();
    }
}
