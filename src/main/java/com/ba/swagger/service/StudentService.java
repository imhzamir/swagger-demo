package com.ba.swagger.service;

import com.ba.swagger.bean.StudentInfo;
import com.ba.swagger.util.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {


    public List<StudentInfo> getStudents() {
        return Constant.STUDENT_LIST;
    }

    public StudentInfo getStudentById(Integer oid) {
        return Constant.STUDENT_LIST.stream().filter(s -> Objects.equals(s.getOid(), oid))
                .findAny().orElseThrow(() -> new RuntimeException(MessageFormat.format("No student found by {0}", oid)));
    }

    public StudentInfo saveStudent(StudentInfo studentInfo) {
        int studentOid = Constant.STUDENT_LIST.size() + 1;
        studentInfo.setOid(studentOid);
        Constant.STUDENT_LIST.add(studentInfo);
        return getStudentById(studentOid);
    }

    public void removeStudent(Integer oid) {
        Optional<StudentInfo> optional = Constant.STUDENT_LIST.stream()
                .filter(s -> Objects.equals(s.getOid(), oid))
                .findAny();
        optional.ifPresent(studentInfo -> Constant.STUDENT_LIST.remove(studentInfo));
    }

    public StudentInfo updateStudent(Integer oid, StudentInfo studentInfo) {
        StudentInfo oldStudent = Constant.STUDENT_LIST.stream().filter(s -> Objects.equals(s.getOid(), oid))
                .findAny().orElseThrow(() -> new RuntimeException(MessageFormat.format("No student found by {0}", oid)));
        Constant.STUDENT_LIST.remove(oldStudent);
        BeanUtils.copyProperties(studentInfo, oldStudent, "oid");
        Constant.STUDENT_LIST.add(oldStudent);
        return getStudentById(oid);
    }

}
