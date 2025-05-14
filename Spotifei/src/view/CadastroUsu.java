
package view;

import controller.ControllerCadastro;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CadastroUsu extends javax.swing.JFrame {
    private ControllerCadastro c;
    public CadastroUsu() {
        initComponents();
        c = new ControllerCadastro(this);
    }

    public JButton getBr_cadastrar() {
        return br_cadastrar;
    }

    public void setBr_cadastrar(JButton br_cadastrar) {
        this.br_cadastrar = br_cadastrar;
    }

    public JLabel getLbl_nome_cadastro() {
        return lbl_nome_cadastro;
    }

    public void setLbl_nome_cadastro(JLabel lbl_nome_cadastro) {
        this.lbl_nome_cadastro = lbl_nome_cadastro;
    }

    public JLabel getLbl_senha_cadastro() {
        return lbl_senha_cadastro;
    }

    public void setLbl_senha_cadastro(JLabel lbl_senha_cadastro) {
        this.lbl_senha_cadastro = lbl_senha_cadastro;
    }

    public JLabel getLbl_usuario_cadastro() {
        return lbl_usuario_cadastro;
    }

    public void setLbl_usuario_cadastro(JLabel lbl_usuario_cadastro) {
        this.lbl_usuario_cadastro = lbl_usuario_cadastro;
    }

    public JTextField getTxt_nome_cadastro() {
        return txt_nome_cadastro;
    }

    public void setTxt_nome_cadastro(JTextField txt_nome_cadastro) {
        this.txt_nome_cadastro = txt_nome_cadastro;
    }

    public JTextField getTxt_senha_cadastro() {
        return txt_senha_cadastro;
    }

    public void setTxt_senha_cadastro(JTextField txt_senha_cadastro) {
        this.txt_senha_cadastro = txt_senha_cadastro;
    }

    public JTextField getTxt_usuario_cadastro() {
        return txt_usuario_cadastro;
    }

    public void setTxt_usuario_cadastro(JTextField txt_usuario_cadastro) {
        this.txt_usuario_cadastro = txt_usuario_cadastro;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_nome_cadastro = new javax.swing.JLabel();
        txt_nome_cadastro = new javax.swing.JTextField();
        txt_usuario_cadastro = new javax.swing.JTextField();
        lbl_usuario_cadastro = new javax.swing.JLabel();
        lbl_senha_cadastro = new javax.swing.JLabel();
        txt_senha_cadastro = new javax.swing.JTextField();
        br_cadastrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Sistema de Alunos");

        lbl_nome_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_nome_cadastro.setText("Nome:");

        lbl_usuario_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_usuario_cadastro.setText("Email:");

        lbl_senha_cadastro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_senha_cadastro.setText("Senha:");

        br_cadastrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        br_cadastrar.setText("CADASTRAR");
        br_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                br_cadastrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 48)); // NOI18N
        jLabel1.setText("SPOTIFEI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_senha_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_senha_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_usuario_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_usuario_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_nome_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nome_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(br_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nome_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nome_cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_usuario_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_usuario_cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_senha_cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_senha_cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(br_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void br_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_br_cadastrarActionPerformed
        c.salvarUsuario();
    }//GEN-LAST:event_br_cadastrarActionPerformed

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
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CadastroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CadastroFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton br_cadastrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_nome_cadastro;
    private javax.swing.JLabel lbl_senha_cadastro;
    private javax.swing.JLabel lbl_usuario_cadastro;
    private javax.swing.JTextField txt_nome_cadastro;
    private javax.swing.JTextField txt_senha_cadastro;
    private javax.swing.JTextField txt_usuario_cadastro;
    // End of variables declaration//GEN-END:variables
}
