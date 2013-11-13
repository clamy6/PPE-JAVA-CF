import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Charline
 * Date: 06/11/13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Gestion g = new Gestion();
        g.runMenu();
    }
}
