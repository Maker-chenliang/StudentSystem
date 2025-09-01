package student;

import test1.user;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        //boolean flag=true;
        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student("1", "小明", 21, "广西");
        Student s2 = new Student("2", "李华", 15, "河北");
        Student s3 = new Student("3", "小李", 18, "陕西");
        Student s4 = new Student("4", "思思", 19, "广东");
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        ArrayList<User> User = new ArrayList<>();
        User u1 = new User("xiaoxiao111", "123456", "17171717171717171X", "17787878721");
        User.add(u1);

        USER(User);
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

    //用户
    public static void USER(ArrayList<User> user) {

        System.out.println("学生管理系统");
        int O = 0, w = -1;

        loop:
        while (true) {
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("请输入你的选择");
            Scanner u = new Scanner(System.in);
            int c = u.nextInt();
            switch (c) {
                case 2 -> {
                    System.out.println("注册");
                    register(user);
                }
                case 1 -> {
                    System.out.println("登录");
                    if (login(user)) {
                        w = checkip(user, O);
                        if (w >= 0) {
                            boolean W = checkpass(user, w);
                            if (W) {
                                System.out.println("登录成功");
                                break loop;
                            }
                        }
                    } else {
                        System.out.println("该用户不存在，请先注册");
                        continue;
                    }
                }
                default -> System.out.println("没有这个选项,请重新输入");
            }
        }

    }

    //检验密码是否正确
    public static boolean checkpass(ArrayList<User> user4, int O) {
        Scanner u = new Scanner(System.in);
        String password = null;
        User q = user4.get(O);
        String passwordcheck1 = q.getPassword();
        while (true) {
            System.out.println("请输入password");
            password = u.next();
            if (passwordcheck1.equals(password)) {
                return true;
            } else {
                System.out.println("password输入有误，请重新输入");
                continue;
            }
        }
    }

    //验证用户personid phoneNumber是否正确
    public static int checkip(ArrayList<User> user3, int o) {
        Scanner u = new Scanner(System.in);
        System.out.println("personid长度为18位，不能以0为开头，前17位必须都是数字，最后一位可以是数字，也可以是X");
        System.out.println("phonenumber长度为11位，不能以0开头，必须都是数字");
        String input;
        String info[] = null;
        while (true) {
            loop:
            while (true) {
                System.out.println("请分别输入personid phonenumber");
                input = u.nextLine();
                info = input.split(" ");
                boolean inputid = check3(info[0]);
                if (!inputid) {
                    System.out.println("personid输入格式错误，请重新输入");
                    continue;
                }

                if (!check2(info[1], 2)) {
                    System.out.println("personnumber输入格式错误，请重新输入");
                    continue;
                }
                break loop;
            }
            int I = 0, j = 0;
            for (o = 0; o < user3.size(); o++) {
                User u1 = user3.get(o);
                String ph = u1.getPhoneNumber();
                String pi = u1.getPersonid();
                if (ph.equals(info[1])) {
                    I = 1;
                }
                if (pi.equals(info[0])) {
                    j = 1;
                }
                if (I == 1 && j == 1) {
                    return o;
                }
            }

            if (I != 1) {
                System.out.println("用户phonenumber输入错误，请重新输入");
            }
            if (j != 1) {
                System.out.println("用户personid输入错误，请重新输入");
            }
            continue;
        }

    }

    //验证用户名是否存在
    public static boolean login(ArrayList<User> user2) {
        System.out.println("id唯一，长度在3到15位之间，只能是字母加数字组合，但不能全是数字");
        Scanner u = new Scanner(System.in);
        String logid, oldid, newid=null;
        while (true) {
            System.out.println("请输入你的用户名");
            logid = u.next();
            newid = logid;
            if ((newid.length() >= 3 && newid.length() <= 15) && check2(newid, 1)) {
                u.nextLine();
            }else{System.out.println("用户id输入错误，请重新输入");
            continue;}
            for (int i = 0; i < user2.size(); i++) {
                User u1 = user2.get(i);
                oldid = u1.getUsername();

                if (newid.equals(oldid)) {
                    return true;
                }
            }
            return false;
        }

    }


    //用户注册
    public static void register(ArrayList<User> user1) {
        Scanner u = new Scanner(System.in);
        System.out.println("id唯一，长度在3到15位之间，只能是字母加数字组合，但不能全是数字");
        System.out.println("personid长度为18位，不能以0为开头，前17位必须都是数字，最后一位可以是数字，也可以是X");
        System.out.println("phonenumber长度为11位，不能以0开头，必须都是数字");
        String info[] = null;
        boolean flag = true;
        int I = 0;
        loop:
        while (flag) {
            System.out.println("请分别输入你要注册的用户id,password,personid,phoneNumber,中间用空格隔开");
            System.out.println("");
            String input = u.nextLine();
            info = input.split(" ");
            if (info.length != 4) {
                System.out.println("输入格式有误，请重新输入");
                System.out.println("");
                continue;
            }
            boolean a = check6(info, user1), c = check5(info), b = check4(info);
            if (a && b && c) {
                System.out.println("注册成功");
                User o = new User();
                o.setUsername(info[0]);
                o.setPassword(info[1]);
                o.setPersonid(info[2]);
                o.setPhoneNumber(info[3]);
                user1.add(o);
                break loop;
            }
            if (a == false) {
                System.out.println("用户Id输入错误或者是该用户id已经存在，请重新输入");
            }
            if (b == false) {
                System.out.println("用户personid输入错误，请重新输入");
            }
            if (c == false) {
                System.out.println("用户phoneNumber输入错误，请重新输入");
            }
            System.out.println("");
        }

    }

    //核验id
    public static boolean check6(String info[], ArrayList<User> user3) {

        for (int i = 0; i < user3.size(); i++) {
            User u1 = user3.get(i);
            String oldid = u1.getUsername();
            String newid = info[0];
            if (!newid.equals(oldid) && (newid.length() >= 3 && newid.length() <= 15) && check2(newid, 1)) {

                return true;
            }

        }
        return false;
    }

    //检验手机号
    public static boolean check5(String info[]) {
        String newphonenumber = info[3];
        if (newphonenumber.length() != 11) {
            return false;
        }
        if (check2(newphonenumber, 2)) {

            return true;
        } else {
            return false;
        }
    }

    //检验personid
    public static boolean check4(String info[]) {
        String newpersonid = info[2];
        if (check3(newpersonid)) {
            return true;
        } else {
            return false;
        }
    }

    //检验数字字母组合和手机号
    public static boolean check2(String Newid, int I) {
        switch (I) {
            case 1 -> {
                char arr[] = Newid.toCharArray();
                int i1 = 0;
                for (int i = 0; i < Newid.length(); i++) {
                    char a = arr[i];
                    if (a >= '0' && a <= '9') {
                        i1++;
                    }
                    if ((a >= '0' && a <= '9') || (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z')) {

                    } else {
                        return false;
                    }
                }
                if (i1 == Newid.length()) {
                    return false;
                }
                return true;
            }
            case 2 -> {
                char arr1[] = Newid.toCharArray();
                for (int i = 1; i < Newid.length(); i++) {
                    char a = arr1[i];
                    if ((a <= '0' || a >= '9') || Newid.length() != 11 || arr1[0] == '0') {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    //检验身份证号码personid
    public static boolean check3(String Oldpersonid) {
        char arr[] = Oldpersonid.toCharArray();
        if (Oldpersonid.length() != 18 || arr[0] == '0') {
            return false;
        }
        for (int i = 0; i < Oldpersonid.length() - 1; i++) {
            char a = arr[i];
            if (a < '0' || a > '9') {
                return false;
            }

        }
        if ((arr[17] <= '9' && arr[17] >= '0') || arr[17] == 'x' || arr[17] == 'X') {
            return true;
        } else {
            return false;
        }
    }


    //添加学生信息
    public static void add(ArrayList<Student> list1) {
        Scanner u = new Scanner(System.in);
        Student s1 = new Student();
        String[] info = null;
        boolean flag = true;
        loop:
        while (flag) {
            boolean Flag = false;
            System.out.println("请分别输入你要录入的学生Id，name，age，addres，中间用空格隔开");
            String input = u.nextLine();
            info = input.split(" ");
            if (info.length != 4) {
                System.out.println("输入格式有误，请重新输入");
                continue;
            }
            String newid = info[0];
            for (int i = 0; i < list1.size(); i++) {
                Student s2 = list1.get(i);
                String oldid = s2.getId();
                if (newid.equals(oldid)) {
                    Flag = true;
                    break;
                }
            }
            if (Flag) {
                System.out.println("该id已经存在，请重新输入");
            } else {
                flag = false;
            }
        }//
        try {
            s1.setId(info[0]);
            s1.setName(info[1]);
            s1.setAge(Integer.parseInt(info[2]));
            s1.setAddres(info[3]);
            list1.add(s1);
            System.out.println("学生信息添加成功");
        } catch (NumberFormatException e) {
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
        while (true) {
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
            } else {
                break;
            }
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
            String id1 = u.next();
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


