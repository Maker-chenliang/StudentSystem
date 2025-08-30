package student;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        //boolean flag=true;
        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student("1", "小明", 21, "广西");
        Student s2 = new Student("2", "李华", 17, "河北");
        Student s3 = new Student("3", "小李", 18, "陕西");
        Student s4 = new Student("4", "思思", 19, "广东");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        loop:
        while (true) {
            System.out.println("");
            System.out.println("学生管理系统");
            System.out.println("1.添加学生信息");
            System.out.println("2.删除学生信息");
            System.out.println("3.查询学生信息");
            System.out.println("4.修改学生信息");
            System.out.println("5.退出");
            System.out.println("");
            System.out.println("请输入你的选择（选项前面的数字)");
            Scanner u = new Scanner(System.in);
            int i = u.nextInt();
            switch (i) {
                case 1 -> {
                    System.out.println("添加学生信息");
                    add(list);
                }
                case 2 -> {
                    System.out.println("删除学生信息");
                    list = delete(list);
                }
                case 3 -> {
                    System.out.println("查询学生信息");
                    found(list);

                }
                case 4 -> {
                    System.out.println("修改学生信息");
                    update(list);
                }
                case 5 -> {
                    System.out.println("退出");
                    //flag=false;
                    break loop;
                }
                default -> System.out.println("没有这个选项,请重新输入");

            }
        }


    }

    //添加学生信息
    public static void add(ArrayList<Student> list1) {
        Scanner u = new Scanner(System.in);
        Student s1 = new Student();
        String[] info=null;
        boolean flag=true;
        loop:while(flag)
        {
            boolean Flag=false;
            System.out.println("请分别输入你要录入的学生Id，name，age，addres，中间用空格隔开");
            String input = u.nextLine();
            info=input.split(" ");
            if(info.length!=4){
                System.out.println("输入格式有误，请重新输入");
                continue;
            }
            String newid=info[0];
            for(int i=0;i<list1.size();i++)
            {
                Student s2=list1.get(i);
                String oldid=s2.getId();
                if(newid.equals(oldid)){
                    Flag=true;
                    break;
                }
            }
            if(Flag){
                System.out.println("该id已经存在，请重新输入");
            }else{
                flag=false;
            }
        }//
        try{
            s1.setId(info[0]);
            s1.setName(info[1]);
            s1.setAge(Integer.parseInt(info[2]));
            s1.setAddres(info[3]);
            list1.add(s1);
            System.out.println("学生信息添加成功");
        }catch (NumberFormatException e) {
            System.out.println("年龄格式错误，请输入数字");
        } catch (Exception e) {
            System.out.println("添加失败：" + e.getMessage());
        }
        if (To()) {
            add(list1);
        }

    }

    //删除学生信息
    public static ArrayList<Student> delete(ArrayList<Student> list2) {
//        loop:
//        while (true) {
//            System.out.println("请输入你要删除的学生信息的id");
//            Scanner u = new Scanner(System.in);
//            for (int i = 0; i < list2.size(); i++) {
//                Student s = list2.get(i);
//                String id = s.getId();
//                String id1 = u.next();
//                if (id1.equals(id)) {
//                    Student t = list2.remove(i);
//                    System.out.println("删除成功");
//                    break loop;
//                }
//
//            }
//            System.out.println("找不到该学生信息，无法删除，请重新输入该学生信息");
//        }
        System.out.println("请输入该学生id");
        Scanner u = new Scanner(System.in);
        int index = Found(list2);
        Student s = list2.get(index);
        String id = s.getId();
        System.out.println("请再次输入该学生id");
        String id1 = u.next();
        if (id1.equals(id)) {
            Student t = list2.remove(index);
            System.out.println("删除成功");
            System.out.println("");

        }
        if (To()) {
            delete(list2);
        }
        return list2;
    }


    //查询学生信息
    public static void found(ArrayList<Student> list3) {
        if (list3.size() == 0) {
            System.out.println("当前无任何学生信息");
            return;
        }

//        loop:
//        while (true) {
//            System.out.println("请输入你要查询的学生id");
//            String id = u.next();
//            for (int i = 0; i < list3.size(); i++) {
//                Student s = list3.get(i);
//                if (id.equals(s.getId())) {
//                    System.out.println(s.getId() + " " + s.getName() + " " + s.getAge() + " " + s.getAddres());
//                    System.out.println("");
//                    break loop;
//                }
//
//            }
//            System.out.println("找不到该学生信息，查询失败，请重新输入");
//
//        }
        System.out.println("请输入该学生id");
        int index = Found(list3);
        Student s = list3.get(index);
        System.out.println(s.getId() + " " + s.getName() + " " + s.getAge() + " " + s.getAddres());
        System.out.println("");
        if (To()) {
            found(list3);
        }
    }

    //修改学生信息
    public static void update(ArrayList<Student> list4) {
        Scanner u = new Scanner(System.in);
//        loop:while(true) {
//            System.out.println("请输入你要修改的学生信息的id");
//
//            String id = u.next();
//            for (int i = 0; i < list4.size(); i++) {
//                Student s = list4.get(i);
//                if (id.equals(s.getId())) {
//                    break loop;
//                }
//            }
//            System.out.println("找不到该学生信息，删除失败，请重新输入Id");
        // }
        while(true) {
            System.out.println("请输入你的操作");
            System.out.println("1.修改学生id 2.修改学生name 3.修改学生age 4.修改学生address");
            int j = u.nextInt();
            switch (j) {
                case 1 -> {
                    System.out.println("请输入该学生的id");
                    int index1 = (Found(list4));
                    System.out.println("请输入该学生的新id");
                    String newid = u.next();
                    Student old = list4.get(index1);
                    old.setId(newid);
                    list4.set(index1, old);
                    System.out.println("修改成功");
                }
                case 2 -> {
                    System.out.println("请输入该学生的id");
                    int index2 = (Found(list4));
                    System.out.println("请输入新学生的name");
                    String newname = u.next();
                    Student old2 = list4.get(index2);
                    old2.setName(newname);
                    list4.set(index2, old2);
                    System.out.println("修改成功");
                }
                case 3 -> {
                    System.out.println("请输入该学生的id");
                    int index3 = (Found(list4));
                    System.out.println("请输入新学生的age");
                    int newage = u.nextInt();
                    Student old3 = list4.get(index3);
                    old3.setAge(newage);
                    list4.set(index3, old3);
                    System.out.println("修改成功");
                }
                case 4 -> {
                    System.out.println("请输入该学生的id");
                    int index4 = (Found(list4));
                    System.out.println("请输入新学生的addres");
                    String newaddres = u.next();
                    Student old4 = list4.get(index4);
                    old4.setAddres(newaddres);
                    list4.set(index4, old4);
                    System.out.println("修改成功");
                }
                default -> {
                    System.out.println("没有这个选项，请重新输入");
                }
            }
            if (To()) {
                update(list4);
            }else{break;}
        }
    }

    //下一步操作
    public static boolean To() {
        System.out.println("请输入你的下一步操作");
        System.out.println("0.退出 1.继续");
        Scanner u = new Scanner(System.in);
        int to = u.nextInt();
        if (to == 1) {
            return true;
        } else {
            return false;
        }

    }

    //寻找学生信息
    public static int Found(ArrayList<Student> list5) {

        int i;
        loop:
        while (true) {
            Scanner u = new Scanner(System.in);
            String id1=u.next();
            for (i = 0; i < list5.size(); i++) {
                Student s = list5.get(i);
                String id = s.getId();

                if (id1.equals(id)) {

                    break loop;
                }

            }
            System.out.println("找不到该学生信息，请重新输入该学生信息");
        }
        return i;
    }


}

