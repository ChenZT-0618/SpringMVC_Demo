package springmvc.crud.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.crud.dao.DepartmentDao;
import springmvc.crud.dao.EmployeeDao;
import springmvc.crud.entities.Employee;

import java.util.Map;

/**
 * @author ChenZT
 */

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;




    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Model model){
        model.addAttribute("employee",employeeDao.get(id));
        model.addAttribute("departments",departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "emp" ,method = RequestMethod.GET)
    public String input(Model model){
        // 把department的集合存放在departments这个属性名中
        // jsp可以通过request进行取出使用。
        model.addAttribute("departments",departmentDao.getDepartments());
        model.addAttribute("employee",new Employee());
        return "input";
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) Integer id,Model model){
        if (id!=null){
            model.addAttribute("employee",employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }



    @RequestMapping(value = "/emp" ,method = RequestMethod.POST)
    public String save(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }



    @RequestMapping("emps")
    public String list(Model model){
        model.addAttribute("employees",employeeDao.getAll());
        return "list";
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
