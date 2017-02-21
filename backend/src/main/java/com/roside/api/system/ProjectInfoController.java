package com.roside.api.system;

import com.roside.mybatis.mapper.ProjectInfoMapper;
import com.roside.service.ProjectInfoService;
import com.roside.system.JsonResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by Eric on 2017-02-21.
 */
@RestController
@RequestMapping("/api")
public class ProjectInfoController {

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProjectInfoService projectInfoService;

    @ApiOperation(value = "获取项目清单", notes = "获取项目清单")
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public JsonResponse getProjectList(){
        return JsonResponse.ok(projectInfoService.getProjectList());
    }
}
