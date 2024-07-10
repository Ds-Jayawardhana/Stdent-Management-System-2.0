public class Student {//Creating Astudent Class
    private String name;
    private String studentID;
    private Module[] modules;

    public Student(String name, String studentID) {//Creating Attrburtes to the Object
        this.name = name;
        this.studentID = studentID;
        this.modules = new Module[3];
    }

    public void setModules(Module[] modules) {//Defning SetModule Method
        this.modules = modules;
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
            if (module != null) {
                total_modules = total_modules + module.getMarks();
            }
        }
        return total_modules / 3;
    }
    public double total(){
        double module_totals=0;
        for(Module module:modules){
            if(module!=null){
                module_totals+=module.getMarks();
            }
        }
        return module_totals;
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