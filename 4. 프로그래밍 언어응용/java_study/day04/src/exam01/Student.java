package exam01;

public class Student {
    int id; // 학번
    String name; // 이름
    String subject; // 전공과목
    public Student(int _id, String _name, String _subject){
        id = _id;
        name = _name;
        subject = _subject;
    }

    void showInfo(){
        System.out.println("id="+id+", name="+name+", subject="+subject);
    }

}
