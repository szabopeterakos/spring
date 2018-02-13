package jdbc3;

import java.util.List;

public class jdbs3 {
	
	public static void main(String [] args) {
		DB db = new DB();
		//db.addUser(new User("name",15,"email"));
		db.allUser();
		db.showUsersMeta();
        List list = db.getAllUsers();
        for (Object c:
             list) {
            if(c instanceof User){
                User cc = (User) c;
                System.out.println(cc);
            }

        }

    }

}
