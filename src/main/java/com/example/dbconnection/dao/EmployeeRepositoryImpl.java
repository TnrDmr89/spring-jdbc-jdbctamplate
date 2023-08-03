package com.example.dbconnection.dao;

import com.example.dbconnection.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    /*
    * sql sorgusunu select için query metodu ya da insert,delete,update için update metodu ile çalıştırdığımızda
    * aldığımız column verilerini rowMapper içinde resultSete atılıyor.
    *
    *
    * query metodu biz bir tane liste döner,
    * */

    private JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Employee> rowMapper = (rs,rowNum) -> new Employee(rs.getInt("employee_id"),rs.getString("name"),
            rs.getInt("age"),rs.getString("address"));

    @Override
    //Get all Employees
    public List<Employee> getAllEmployee() {
        String sql = "select * from employee";
        return jdbcTemplate.query(sql,rowMapper);

    }

    @Override
    //Get employee with id
    public Optional<Employee> getEmployee(int id) {
        String sql = "select employee_id,name,age,address from employee where employee_id = ? ";
        Employee employee = null;
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper));
    }

    @Override
    //Save one employee to database
    public void save(Employee employee) {
       String sql = "INSERT INTO employee(employee_id, name, age, address) values (?,?,?,?)";
       Object[] args = new Object[]{employee.getEmployeeId(),employee.getName(),employee.getAge(),employee.getAddress()};
       jdbcTemplate.update(sql,args);
    }

    @Override
    //Save more than one employee to database
    public void saveListEmployee(List<Employee> employees) {
        String sql = "INSERT INTO employee(employee_id, name, age, address) values (?,?,?,?)";
        int[][] number = jdbcTemplate.batchUpdate(sql,employees,100,(PreparedStatement ps, Employee employee)->{
            ps.setInt(1,employee.getEmployeeId());
            ps.setString(2,employee.getName());
            ps.setInt(3,employee.getAge());
            ps.setString(4,employee.getAddress());
        });
    }

    @Override
    //Update employee with id in database
    public void update(int id, Employee employee) {
        String sql = "update employee set name = ?, age = ?, address = ? where employee_id = ?";
        jdbcTemplate.update(sql,employee.getName(),employee.getAge(),employee.getAddress(),id);
    }

    @Override
    //Delete employee with name in database
    public void delete(String name) {
        String sql = "delete from employee where name LIKE CONCAT( ?, '%') ";
        jdbcTemplate.update(sql,name);
    }

    @Override
    //Get employeeses who being grater than ?age in database
    public List<Employee> getGraterThanAge(int age) {
        String sql = "select employee_id,name,age,address from employee where age > ?";
        return jdbcTemplate.query(sql,new Object[]{age},rowMapper);
    }

    @Override
    //Get employeeses who being ?name in database
    public List<Employee> getNameWith(String name) {
        String sql = "select employee_id,name,age,address from employee where name = ?";
        return jdbcTemplate.query(sql,rowMapper,name);
    }

    @Override
    //Get employeeses who being grater and equal than ?age and ?name in database
    public List<Employee> getSameNameAndAge(String name, int age) {
        String sql = "select employee_id,name,age,address from employee where name = ? and age >= ?";
        return jdbcTemplate.query(sql,new Object[]{name,age},rowMapper);
    }


}
