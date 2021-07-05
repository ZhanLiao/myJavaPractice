package serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @Author: ZhanLiao
 * @Description:
 * @Date: 2021/6/29 16:48
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        String path_name = "D:\\Working\\javaSE\\myJavaPractice\\src\\serializable\\student.bin";

        // 序列化
        Student student = new Student();
        student.setName("zl");
        student.setID("101");
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path_name));
            outputStream.writeObject(student);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 反序列化
        Student newStudent = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path_name));
            newStudent = (Student) inputStream.readObject();
            inputStream.close();
            Class<? extends Student> studentClass = newStudent.getClass();
            for (Field field : studentClass.getDeclaredFields()) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(newStudent);

    }

}

class Student implements /*Serializable*/ Externalizable {
    String name;
    /*transient*/ String ID;
    static long serializableID = 110L;

    public Student(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", ID='").append(ID).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(ID);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        ID = (String) in.readObject();
    }
}
