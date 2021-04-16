package cn.vtrump.testspringboot1.controller;

import cn.vtrump.testspringboot1.pojo.Student;
import cn.vtrump.testspringboot1.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/student")
@Api(tags = "Student增删改查相关接口")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("查询所有Student")
    @GetMapping("/select/all")
    public List<Student> selectAll(){
        return studentService.selectAll();
    }

    @ApiOperation("增加一个Student")
    @PostMapping("/add")
    public boolean addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @ApiOperation("按id删除一个Student")
    @DeleteMapping("/delete/{id}")
    public boolean deleteStudent(@PathVariable("id") Integer id){
        return studentService.deleteStudent(id);
    }

    @ApiOperation("更新一个Student")
    @PutMapping("/update")
    public boolean updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @ApiOperation("按id查询一个Student")
    @GetMapping("/select/{id}")
    public Student selectStudent(@PathVariable("id") Integer id){
        return studentService.selectStudent(id);
    }




}