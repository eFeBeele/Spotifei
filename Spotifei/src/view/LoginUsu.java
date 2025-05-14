
package view;

import controller.ControllerLogin;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginUsu extends javax.swing.JFrame {
private ControllerLogin c;
    public LoginUsu() {
        initComponents();
        c = new ControllerLogin(this);
    }

    public JButton getBt_cadastro() {
        return bt_cadastro;
    }

    public void setBt_cadastro(JButton bt_cadastro) {
        this.bt_cadastro = bt_cadastro;
    }

    public JButton getBt_login() {
        return bt_login;
    }

    public void setBt_login(JButton bt_login) {
        this.bt_login = bt_login;
    }

    public JLabel getLbl_senha_login() {
        return lbl_senha_login;
    }

    public void setLbl_senha_login(JLabel lbl_senha_login) {
        this.lbl_senha_login = lbl_senha_login;
    }

    public JLabel getLbl_usuario_login() {
        return lbl_usuario_login;
    }

    public void setLbl_usuario_login(JLabel lbl_usuario_login) {
        this.lbl_usuario_login = lbl_usuario_login;
    }

    public JTextField getTxt_senha_login() {
        return txt_senha_login;
    }

    public void setTxt_senha_login(JTextField txt_senha_login) {
        this.txt_senha_login = txt_senha_login;
    }

    public JTextField getTxt_usuario_login() {
        return txt_usuario_login;
    }

    public void setTxt_usuario_login(JTextField txt_usuario_login) {
        this.txt_usuario_login = txt_usuario_login;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_usuario_login = new javax.swing.JLabel();
        txt_usuario_login = new javax.swing.JTextField();
        txt_senha_login = new javax.swing.JTextField();
        lbl_senha_login = new javax.swing.JLabel();
        bt_login = new javax.swing.JButton();
        bt_cadastro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Sistema de Alunos");

        lbl_usuario_login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_usuario_login.setText("Usuário:");

        lbl_senha_login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_senha_login.setText("Senha:");

        bt_login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_login.setText("LOGIN");
        bt_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_loginActionPerformed(evt);
            }
        });

        bt_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_cadastro.setText("CADASTRO");
        bt_cadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cadastroActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 48)); // NOI18N
        jLabel1.setText("SPOTIFEI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_senha_login, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_usuario_login, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_login, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_usuario_login, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_senha_login, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(110, 110, 110))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_usuario_login, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario_login))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_senha_login, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_senha_login))
                .addGap(41, 41, 41)
                .addComponent(bt_login)
                .addGap(18, 18, 18)
                .addComponent(bt_cadastro)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_loginActionPerformed
        c.loginUsuario();
        this.setVisible(false);
    }//GEN-LAST:event_bt_loginActionPerformed

    private void bt_cadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cadastroActionPerformed
        CadastroUsu cf = new CadastroUsu();
        cf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bt_cadastroActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoginFrame().setVisible(true);
//            }
//        });
//    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cadastro;
    private javax.swing.JButton bt_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_senha_login;
    private javax.swing.JLabel lbl_usuario_login;
    private javax.swing.JTextField txt_senha_login;
    private javax.swing.JTextField txt_usuario_login;
    // End of variables declaration//GEN-END:variables
}
