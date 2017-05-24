package info.infomila.ui;

import info.infomila.IComponentSGBD;
import info.infomila.IComponentSGBDException;
import info.infomila.model.Client;
import info.infomila.model.Polissa;
import info.infomila.model.Sinistre;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mr. Robot
 */
public class UI extends javax.swing.JFrame {

    private IComponentSGBD dbConnector;
    private TableModel clientsModel;
    private TableModel sinistresModel;
    private List<Client> clients;
    private List<Sinistre> sinistres;
    private List<Sinistre> sinistresFiltrats;

    /**
     * Creates new form UI
     */
    public UI(IComponentSGBD dbConnector) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.dbConnector = dbConnector;
        initComponents();
        inirComponentsCorrectly();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtClients = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCognom1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        taNif = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        taNom = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        taDataNaix = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        taCognom2 = new javax.swing.JTextArea();
        btnActivarFiltre = new javax.swing.JButton();
        btnDesactivarFiltre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtSinistres = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        taNumSinistre = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnFiltreSinistre = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NIF", "Nom", "Cognom1", "Cognom2", "Data Naixement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtClientsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtClients);
        if (jtClients.getColumnModel().getColumnCount() > 0) {
            jtClients.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtClients.getColumnModel().getColumn(0).setMaxWidth(100);
            jtClients.getColumnModel().getColumn(1).setPreferredWidth(110);
            jtClients.getColumnModel().getColumn(1).setMaxWidth(110);
            jtClients.getColumnModel().getColumn(2).setPreferredWidth(120);
            jtClients.getColumnModel().getColumn(2).setMaxWidth(120);
            jtClients.getColumnModel().getColumn(3).setPreferredWidth(120);
            jtClients.getColumnModel().getColumn(3).setMaxWidth(120);
            jtClients.getColumnModel().getColumn(4).setPreferredWidth(140);
            jtClients.getColumnModel().getColumn(4).setMaxWidth(140);
        }

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("Nom");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taCognom1.setColumns(20);
        taCognom1.setRows(5);
        jScrollPane1.setViewportView(taCognom1);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setToolTipText("Nom");
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taNif.setColumns(20);
        taNif.setRows(5);
        jScrollPane3.setViewportView(taNif);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("Nom");
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taNom.setColumns(20);
        taNom.setRows(5);
        jScrollPane4.setViewportView(taNom);

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setToolTipText("Nom");
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taDataNaix.setColumns(20);
        taDataNaix.setRows(5);
        jScrollPane5.setViewportView(taDataNaix);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setToolTipText("Nom");
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taCognom2.setColumns(20);
        taCognom2.setRows(5);
        jScrollPane6.setViewportView(taCognom2);

        btnActivarFiltre.setText("Activar Filtre");
        btnActivarFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActivarFiltreMouseClicked(evt);
            }
        });

        btnDesactivarFiltre.setText("Desactivar Filtre");
        btnDesactivarFiltre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesactivarFiltreMouseClicked(evt);
            }
        });
        btnDesactivarFiltre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarFiltreActionPerformed(evt);
            }
        });

        jLabel1.setText("NIF");

        jLabel2.setText("Nom");

        jLabel3.setText("Cognom1");

        jLabel4.setText("Cognom2");

        jLabel5.setText("Data Naixement");

        jtSinistres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Num", "Data Assignació", "Data Obertura", "Data Tancament", "Descripció", "Num Polissa", "Tipus Sinistre", "Estat Sinistre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jtSinistres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtSinistresMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jtSinistres);
        if (jtSinistres.getColumnModel().getColumnCount() > 0) {
            jtSinistres.getColumnModel().getColumn(0).setPreferredWidth(100);
            jtSinistres.getColumnModel().getColumn(0).setMaxWidth(100);
            jtSinistres.getColumnModel().getColumn(1).setPreferredWidth(110);
            jtSinistres.getColumnModel().getColumn(1).setMaxWidth(110);
            jtSinistres.getColumnModel().getColumn(2).setPreferredWidth(120);
            jtSinistres.getColumnModel().getColumn(2).setMaxWidth(120);
            jtSinistres.getColumnModel().getColumn(3).setPreferredWidth(120);
            jtSinistres.getColumnModel().getColumn(3).setMaxWidth(120);
            jtSinistres.getColumnModel().getColumn(4).setPreferredWidth(140);
            jtSinistres.getColumnModel().getColumn(4).setMaxWidth(140);
            jtSinistres.getColumnModel().getColumn(5).setPreferredWidth(100);
            jtSinistres.getColumnModel().getColumn(5).setMaxWidth(100);
            jtSinistres.getColumnModel().getColumn(6).setPreferredWidth(100);
            jtSinistres.getColumnModel().getColumn(6).setMaxWidth(100);
            jtSinistres.getColumnModel().getColumn(7).setPreferredWidth(100);
            jtSinistres.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setToolTipText("Nom");
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taNumSinistre.setColumns(20);
        taNumSinistre.setRows(5);
        jScrollPane8.setViewportView(taNumSinistre);

        jLabel6.setText("Num Sinistre");

        btnFiltreSinistre.setText("Activar Filtre");
        btnFiltreSinistre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFiltreSinistreMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnFiltreSinistre))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(149, 149, 149)
                                    .addComponent(btnActivarFiltre))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDesactivarFiltre))))))
                .addContainerGap(366, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActivarFiltre)
                        .addComponent(btnDesactivarFiltre))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFiltreSinistre))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtClientsMouseClicked
        int row = jtClients.rowAtPoint(evt.getPoint());
        int col = jtClients.columnAtPoint(evt.getPoint());

        int codiClient = clients.get(row).getNumero();
        System.out.println("num CLient: " + codiClient);
        try {
            sinistres = dbConnector.getLlistatSinistresPerClient(codiClient);
            sinistresFiltrats = sinistres;
            SinistresTableModel stb = new SinistresTableModel(sinistres);
            jtSinistres.setModel(stb);
        } catch (IComponentSGBDException ex) {
            System.out.println("Error en recuperar sinistres: " + ex.getMessage());
        }

    }//GEN-LAST:event_jtClientsMouseClicked

    private void jtSinistresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSinistresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtSinistresMouseClicked

    private void btnDesactivarFiltreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarFiltreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesactivarFiltreActionPerformed

    private void btnActivarFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActivarFiltreMouseClicked
        filtrarLlistaClients();
    }//GEN-LAST:event_btnActivarFiltreMouseClicked

    private void btnDesactivarFiltreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesactivarFiltreMouseClicked
        disableFiltreClients();
    }//GEN-LAST:event_btnDesactivarFiltreMouseClicked

    private void btnFiltreSinistreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiltreSinistreMouseClicked
        sinistresModel = new SinistresTableModel(sinistres);
        jtSinistres.setModel(sinistresModel);
        filtrarSinistresPerCodi();
    }//GEN-LAST:event_btnFiltreSinistreMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        if (args.length != 1 && args.length != 2) {
            System.out.println("L'execució d'aquest programa necessita d'1 o 2 arguments: ");
            System.out.println("Argument 1: Nom de la classe de la capa de persistència (obligatori)");
            System.out.println("Argument 2: Nom del fitxer de configuració que espera la classe (optatiu)");
            System.exit(1);
        }

        String classeCapaPersistencia = args[0];
        String nomFitxerConfiguracio = null;
        if (args.length == 2) {
            nomFitxerConfiguracio = args[1];
        }

        IComponentSGBD obj = null;
        Class c = null;
        try {
            System.out.println(classeCapaPersistencia);
            c = Class.forName(classeCapaPersistencia);
        } catch (ClassNotFoundException ex) {
            System.out.println("No es troba la classe " + classeCapaPersistencia);
            System.exit(1);
        }
        try {
            if (nomFitxerConfiguracio == null) {
                obj = (IComponentSGBD) c.newInstance();
            } else {
                try {
                    Constructor x = c.getConstructor(String.class);
                    obj = (IComponentSGBD) x.newInstance(nomFitxerConfiguracio);
                } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    System.out.println("Problemes en intentar executar constructor de la " + classeCapaPersistencia + " amb paràmetre=" + nomFitxerConfiguracio);
                    System.out.println("Més informació: " + ex.getMessage());
                    if (ex.getCause() != null) {
                        System.out.println("Causat per: " + ex.getCause().getMessage());
                        if (ex.getCause().getCause() != null) {
                            System.out.println(ex.getCause().getCause().getMessage());
                        }
                    }
                    System.exit(1);
                }
            }
            System.out.println("Connexió establerta");

            final IComponentSGBD bdConector = obj;


            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new UI(bdConector).setVisible(true);
                }
            });

        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("No es pot obtenir l'objecte de persistència");
            System.out.println("Més informació: " + ex.getMessage());
            if (ex.getCause() != null) {
                System.out.println("Causat per: " + ex.getCause().getMessage());
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarFiltre;
    private javax.swing.JButton btnDesactivarFiltre;
    private javax.swing.JButton btnFiltreSinistre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jtClients;
    private javax.swing.JTable jtSinistres;
    private javax.swing.JTextArea taCognom1;
    private javax.swing.JTextArea taCognom2;
    private javax.swing.JTextArea taDataNaix;
    private javax.swing.JTextArea taNif;
    private javax.swing.JTextArea taNom;
    private javax.swing.JTextArea taNumSinistre;
    // End of variables declaration//GEN-END:variables

    private void inirComponentsCorrectly() {
        clients = dbConnector.getLlistatClients();

        clientsModel = new ClientsTableModel(clients);
        jtClients.setModel(clientsModel);
        taNumSinistre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnFiltreSinistre.setEnabled(!taNumSinistre.getText().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnFiltreSinistre.setEnabled(!taNumSinistre.getText().isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnFiltreSinistre.setEnabled(!taNumSinistre.getText().isEmpty());
            }
        });

    }

    private void filtrarLlistaClients() {
        System.out.println("nif: " + taNif.getText() + " - Nom: " + taNom.getText() + " - ");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dataNaix;
        try {
            if (!taDataNaix.getText().isEmpty()) {
                dataNaix = new java.sql.Date(df.parse(taDataNaix.getText()).getTime());
            } else {
                dataNaix = null;
            }
            clients = dbConnector.getClientFiltrat(taNif.getText(), taNom.getText(), taCognom1.getText(), taCognom2.getText(), dataNaix);
            clientsModel = new ClientsTableModel(clients);
            jtClients.setModel(clientsModel);
        } catch (ParseException ex) {
            System.out.println("Error parsejant data: " + ex.getMessage());
        } catch (IComponentSGBDException ex) {
            System.out.println("Error recuperant Clients Filtrats");
        }

    }

    private void disableFiltreClients() {
        clients = dbConnector.getLlistatClients();
        clientsModel = new ClientsTableModel(clients);
        jtClients.setModel(clientsModel);

        taNif.setText("");
        taNom.setText("");
        taCognom1.setText("");
        taCognom2.setText("");
        taDataNaix.setText("");

    }

    private void filtrarSinistresPerCodi() {
        try {
            int codiSinistre = Integer.parseInt(taNumSinistre.getText());
            Sinistre s = null;
            for(Sinistre si : sinistresFiltrats){
                if(si.getNumero() == codiSinistre) s = si;
            }
            
            sinistresFiltrats.clear();
            
            if(s != null) sinistresFiltrats.add(s);
            sinistresModel = new SinistresTableModel(sinistresFiltrats);
            jtSinistres.setModel(sinistresModel);
            
        } catch (NumberFormatException ex) {
        }
    }
}
