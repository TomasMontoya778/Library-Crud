package Controller;

import Entity.Author;
import Model.AuthorModel;

import javax.swing.*;

public class AuthorController {

    public static void insertAuthor() {
        AuthorModel objAuthorModel = new AuthorModel();
        String name = JOptionPane.showInputDialog(null, "Insert the Author name.\n");
        String nacionality = JOptionPane.showInputDialog(null, "Insert the Nacionality's author\n");
        Author objAuthor = new Author();
        objAuthor.setName(name);
        objAuthor.setNacionality(nacionality);
        objAuthor = (Author) objAuthorModel.insert(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }

    public static void listAuthor() {
        AuthorModel objAuthorModel = new AuthorModel();
        String text = "----Authors List----\n";
        for (Object temp : objAuthorModel.readAll()) {
            Author objAuthor = (Author) temp;
            text += objAuthor.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listAuthorString() {
        AuthorModel objAuthorModel = new AuthorModel();
        String text = "-----Author List-----\n";
        for (Object temp : objAuthorModel.readAll()) {
            Author objAuthor = (Author) temp;
            text += objAuthor.toString();
        }

        return text;
    }

    public static void deleteAuthor() {
        AuthorModel objAuthorModel = new AuthorModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Insert the Author Id to be eliminate. (remember that if you delete an author, all his/her books will be deleted as well)\n" + listAuthorString()));
        Author objAuthor = objAuthorModel.findById(idToDelete);
        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "The Author wasn't find");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to delete that Author?\n" + objAuthor);
            if (confirm == 0) {
                objAuthorModel.delete(objAuthor);
            }
        }
    }

    public static void updateAuthor() {
        AuthorModel objAuthorModel = new AuthorModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the Author Id to be Update\n" + listAuthorString()));
        Author objAuthor = objAuthorModel.findById(idToUpdate);
        if (objAuthor == null) {
            JOptionPane.showMessageDialog(null, "The Author wasn't find");
        } else {
            String option = "";
            do {
                 option = JOptionPane.showInputDialog(null, """
                        1. Update Name.
                        2. Update Nacionality.
                        3 . Update All.
                        4. Cancel.
                        """);
                 switch (option){
                     case "1":
                         String name = JOptionPane.showInputDialog(null, "Insert the new Author name.");
                         objAuthor.setName(name);
                         int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Author?\n" + objAuthor);
                         if (confirm == 0) {
                             objAuthorModel.update(objAuthor);
                         }
                         break;
                     case "2":
                         String nacionality = JOptionPane.showInputDialog(null, "Insert the new Author nacionality");
                         objAuthor.setNacionality(nacionality);
                         int confirm2 = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Author?\n" + objAuthor);
                         if (confirm2 == 0) {
                             objAuthorModel.update(objAuthor);
                         }
                         break;
                     case "3":
                         String name2 = JOptionPane.showInputDialog(null, "Insert the new Author name.");
                         String nacionality2 = JOptionPane.showInputDialog(null, "Insert the new Author nacionality");
                         objAuthor.setName(name2);
                         objAuthor.setNacionality(nacionality2);
                         int confirm3 = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Author?\n" + objAuthor);
                         if (confirm3 == 0) {
                             objAuthorModel.update(objAuthor);
                         }
                         break;
                 }
            }while (!option.equals("4"));
        }
    }
}
