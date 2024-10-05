package com.ba.swagger.util;


import com.ba.swagger.bean.StudentInfo;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public static List<StudentInfo> STUDENT_LIST = new ArrayList<>() {
        {
            add(new StudentInfo(1, "Zamir", "01864444670"));
            add(new StudentInfo(2, "Nurul", "01923442385"));
        }
    };

}
