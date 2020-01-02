import View.StudentsListView;
import model.StudentsModel;
import model.StudentsModel_lmpl;

public class Main{

    public static void main(String[] args) {
        StudentsModel model = new StudentsModel_lmpl();
        StudentsListView studentListView = new StudentsListView(model);

    }
}
