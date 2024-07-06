public class main2 {public class Main {
    public static void main(String[] args) {
        Student student01 = new Student("Disandu", "w2083055");


        Module module1 = new Module("Mathematics", 85.0);
        Module module2 = new Module("Science", 78.0);
        Module module3 = new Module("Literature", 65.0);

        // Create a student
        Student student = new Student("John Doe", "12345");

        // Assign modules to the student
        student.setModule(0, module1);
        student.setModule(1, module2);
        student.setModule(2, module3);

        System.out.println(student.Average());
        System.out.println(student.calcGrade());
        System.out.println(student01.Average());

    }
}
}
