package com.xu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    public void test1() {
        Car car = new Car();
        car.setA(12);
        getValue(car);
    }


    public void getValue(Car car){
        Field[] field = car.getClass().getDeclaredFields();
        for(int j=0 ; j<field.length ; j++){
            String name = field[j].getName();

            name = name.substring(0,1).toUpperCase()+name.substring(1);
            String type = field[j].getGenericType().toString();
            if(type.equals("class java.lang.String")){
                Method m;
                String value;
                try {
                    m = car.getClass().getMethod("get"+name);
                    value = (String) m.invoke(car);
                    if(value != null && !"".equals(value)){
                        System.out.println(name);
                        System.out.println(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
