package ConnexionBD;

import entity.Quizs;
import service.ServiceQuizs;
import utils.DataSource;

public class ConnexionBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataSource ds1 =DataSource.getInstance();
        System.out.println(ds1);
        Quizs q=new Quizs(1, "Java Programming Quiz", "A quiz to test your knowledge of Java programming concepts, syntax, and best practices");
        ServiceQuizs sq=new ServiceQuizs();
        sq.insert(q);
        sq.delete(q);
    }

}

