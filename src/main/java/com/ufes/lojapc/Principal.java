package com.ufes.lojapc;

import com.ufes.lojapc.dao.ComponenteDAO;
import com.ufes.lojapc.dao.IComponenteDAO;
import com.ufes.lojapc.presenter.AssistentePresenter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Principal {

    public static void main(String[] args) {
        try {
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeel);
            IComponenteDAO dao = new ComponenteDAO();
            new AssistentePresenter(dao);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
