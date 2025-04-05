package domain;

public class Student implements Person{

    private String id;
    private String name;
    private int age;
    private String address;

    public Student(String id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student(String id) {
        this.id = id;
    }

    /*
    Métodos:
    1. studyHours() retorna age/2,
    2. equals(Person other) retorna true si los objetos tienen el mismo id
    3. toString retorna
    "Student {" + "id=" + id + ", name=" +
    name + ", age=" + age + ", study hours="+studyHours()+", address="+address+'}';
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private double studyHours(){
        return age/2;
    }

    @Override
    public boolean equals(Person other) {
        Student student = (Student) other;
        return this.id.equals(student.id);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getAddress() {
        return "";
    }

    @Override
    public String toString() {
        return "Student {" + "id=" + id + ", name=" +
            name + ", age=" + age + ", study hours="+studyHours()+", address="+address+'}';
    }
}
