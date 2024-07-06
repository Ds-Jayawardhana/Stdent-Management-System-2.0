
public class Student {
    String name;
    String studentID;
    Module[] modules;

    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
        this.modules = new Module[3];
    }

    public void setModule(int index, Module module) {
        if (index >= 0 && index < 3) {
            this.modules[index] = module;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return this.studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Module[] getModules() {
        return this.modules;
    }

    public double Average() {
        double total_modules = 0;
        for (Module module : modules) {
            total_modules = total_modules + module.getModule();

        }
        return total_modules / 3;
    }

    public String calcGrade() {
        double avgGrade = Average();
        if (avgGrade >= 80) {
            return "Distinction Pass";
        } else if (avgGrade >= 70) {
            return "Merit Pass";
        } else if (avgGrade >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}