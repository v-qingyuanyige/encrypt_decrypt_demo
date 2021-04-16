package cn.vtrump.testspringboot1.mapper;

import cn.vtrump.testspringboot1.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    /*@Results({
            @Result(column = "id",jdbcType = JdbcType.INTEGER,property = "id"),
            @Result(column = "name",jdbcType = JdbcType.VARCHAR,property = "name")
    })*/

    //查询所有学生信息
    @Select("select * from student")
    List<Student> selectAll();

    //增加一个学生
    @Insert("insert into student values(#{id}, #{name})")
    boolean addStudent(Student student);

    //根据id删除一个学生
    @Delete("delete from student where id = #{id}")
    boolean deleteStudent(Integer id);

    //修改学生信息
    @Update("update student set name = #{name} where id = #{id}")
    boolean updateStudent(Student student);

    //根据id查询学生信息
    @Select("select * from student where id = #{id}")
    Student selectStudent(Integer id);


}