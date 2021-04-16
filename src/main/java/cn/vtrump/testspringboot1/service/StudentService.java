package cn.vtrump.testspringboot1.service;

import cn.vtrump.testspringboot1.mapper.StudentMapper;
import cn.vtrump.testspringboot1.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //Service：查询所有Student
    public List<Student> selectAll(){
        return studentMapper.selectAll();
    }

    //Service：增加Student
    public boolean addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    //Service：删除Student
    public boolean deleteStudent(Integer id){
        return studentMapper.deleteStudent(id);
    }

    //Service：修改Student
    public boolean updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }

    //Service：查询Student
    public Student selectStudent(Integer id){
        return studentMapper.selectStudent(id);
    }
}