package com.bupt.microbootsecurity.controller;

import com.bupt.microbootsecurity.dto.TestDTO;
import com.bupt.microbootsecurity.service.impl.TestServiceImpl;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * POST、PUT请求，使用requestBody传递参数；
 * GET请求，使用requestParam/PathVariable传递参数。
 */
@RestController(value = "TemplateController")
@RequestMapping("/template")
@Validated
public class TemplateController {

    @Resource
    TestServiceImpl testServiceImpl;

    @GetMapping("/{num}")
    public Integer numProcess(@PathVariable("num") @Min(2) @Max(10) Integer num) {
        return num * num;
    }

    @GetMapping("/getByEmail")
    public TestDTO getByAccount(@RequestParam String email) {
        TestDTO testDTO = new TestDTO();
        testDTO.setEmail(email);
        return testDTO;
    }

//    @PostMapping("/test-validation")
//    public void testValidation(@RequestBody @Validated TestDTO testDTO) {
//        this.testService.save(testDTO);
//    }

    @Autowired
    public void setTestService(TestServiceImpl prettyTestServiceImpl) {
        this.testServiceImpl = prettyTestServiceImpl;
    }

}
