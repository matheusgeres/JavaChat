package javachat.ui;

import java.awt.Rectangle;
import javachat.network.Client;
import javachat.JavaChat;
import javax.swing.AbstractListModel;
import javax.swing.BoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author DrLabman
 */
public class ChatWindow extends javax.swing.JFrame {

    /**
     * Creates new form ChatWindow
     */
    public ChatWindow() {
        initComponents();

        this.setTitle("Java Chat");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupModeType = new javax.swing.ButtonGroup();
        jSpinner1 = new javax.swing.JSpinner();
        jRadioButtonServer = new javax.swing.JRadioButton();
        jRadioButtonClient = new javax.swing.JRadioButton();
        jLabelHost = new javax.swing.JLabel();
        jTextFieldHostname = new javax.swing.JTextField();
        jLabelPort = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jToggleButtonOnline = new javax.swing.JToggleButton();
        jTextFieldMessage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaChat = new javax.swing.JTextArea();
        jButtonSend = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonClear = new javax.swing.JButton();
        jButtonUpdateName = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsers = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroupModeType.add(jRadioButtonServer);
        jRadioButtonServer.setText("Server");
        jRadioButtonServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonServerActionPerformed(evt);
            }
        });

        buttonGroupModeType.add(jRadioButtonClient);
        jRadioButtonClient.setSelected(true);
        jRadioButtonClient.setText("Client");
        jRadioButtonClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonClientActionPerformed(evt);
            }
        });

        jLabelHost.setText("Host:");

        jTextFieldHostname.setText("localhost");

        jLabelPort.setText("Port:");

        jTextFieldPort.setText("12345");

        jToggleButtonOnline.setText("Connect");
        jToggleButtonOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonOnlineActionPerformed(evt);
            }
        });

        jTextFieldMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldMessageKeyTyped(evt);
            }
        });

        jTextAreaChat.setEditable(false);
        jTextAreaChat.setColumns(20);
        jTextAreaChat.setRows(5);
        jScrollPane1.setViewportView(jTextAreaChat);

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jTextFieldName.setText("user");

        jLabel1.setText("Name:");

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonUpdateName.setText("Update Name");
        jButtonUpdateName.setEnabled(false);
        jButtonUpdateName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateNameActionPerformed(evt);
            }
        });

        jListUsers.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListUsers);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Users");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextFieldMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClear))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButtonServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jLabelHost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jToggleButtonOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUpdateName, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonServer)
                    .addComponent(jRadioButtonClient)
                    .addComponent(jToggleButtonOnline)
                    .addComponent(jTextFieldHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHost)
                    .addComponent(jLabelPort)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonUpdateName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSend)
                    .addComponent(jButtonClear)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jRadioButtonServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonServerActionPerformed
            jTextFieldHostname.setEnabled(false);
            jTextFieldName.setText("server");
	}//GEN-LAST:event_jRadioButtonServerActionPerformed

	private void jRadioButtonClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonClientActionPerformed
            jTextFieldHostname.setEnabled(true);
            jTextFieldName.setText("user");
	}//GEN-LAST:event_jRadioButtonClientActionPerformed

	private void jToggleButtonOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonOnlineActionPerformed
            if (jToggleButtonOnline.isSelected()) {
                boolean connected = false;
                String name = jTextFieldName.getText();
                if (validateName(name)) {
                    // Connect
                    if (jRadioButtonServer.isSelected()) {
                        // Server
                        connected = JavaChat.startServer(jTextFieldPort.getText(), name);
                    } else {
                        // Client
                        connected = JavaChat.startClient(jTextFieldHostname.getText(), jTextFieldPort.getText(), name);
                    }
                }
                lockServerDetails(connected);
                jToggleButtonOnline.setSelected(connected);
            } else {
                // Disconnect the client/server
                JavaChat.disconnect();
                // Unlock the server details so we can change them before connecting again
                lockServerDetails(false);
            }
	}//GEN-LAST:event_jToggleButtonOnlineActionPerformed

	private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
            Client client = JavaChat.getClient();
            if (client != null && client.isConnected()) {
                String msg = jTextFieldMessage.getText();
                if (!msg.equals("")) {
                    jTextFieldMessage.setText("");
                    client.sendMsg(msg);
                }
            } else {
                println("Not connected: Unable to send message.");
            }
	}//GEN-LAST:event_jButtonSendActionPerformed

	private void jTextFieldMessageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMessageKeyTyped
            if (evt.getKeyChar() == '\n') {
                jButtonSend.doClick();
            }
	}//GEN-LAST:event_jTextFieldMessageKeyTyped

	private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
            jTextAreaChat.setText("");
	}//GEN-LAST:event_jButtonClearActionPerformed

	private void jButtonUpdateNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateNameActionPerformed
            Client client = JavaChat.getClient();
            if (client != null) {
                String name = jTextFieldName.getText();
                if (validateName(name)) {
                    client.setName(name);
                }
            }
	}//GEN-LAST:event_jButtonUpdateNameActionPerformed

    private boolean validateName(String name) {
        if (name.contains(" ")) {
            println("Name can not contain spaces.");
            return false;
        }
        return true;
    }

    private void lockServerDetails(boolean lock) {
        jRadioButtonServer.setEnabled(!lock);
        jRadioButtonClient.setEnabled(!lock);
        jTextFieldHostname.setEnabled(!lock && !jRadioButtonServer.isSelected());
        jTextFieldPort.setEnabled(!lock);
        jButtonUpdateName.setEnabled(lock);
    }

    /**
     * Resets form when an unexpected disconnect happens
     */
    public void disconnected() {
        lockServerDetails(false);
        jToggleButtonOnline.setSelected(false);
    }

    public void print(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTextAreaChat.append(text);

                JScrollBar scrollBar = jScrollPane1.getVerticalScrollBar();
                boolean scrollBarAtBottom = isScrollBarFullyExtended(scrollBar);
                if (scrollBarAtBottom) {
                    scrollToBottom(jTextAreaChat);
                }
            }
        });
    }

    public static boolean isScrollBarFullyExtended(JScrollBar vScrollBar) {
        BoundedRangeModel model = vScrollBar.getModel();
        int bottomPos = model.getExtent() + model.getValue() + 10;
        int maxPos = model.getMaximum();
        return bottomPos >= maxPos;
    }

    public static void scrollToBottom(JComponent component) {
        Rectangle visibleRect = component.getVisibleRect();
        visibleRect.y = component.getHeight() - visibleRect.height;
        component.scrollRectToVisible(visibleRect);
    }

    public void println(final String text) {
        print(text + "\n");
    }

    public void setUsersOnline(final String[] users) {
        if (users != null && users.length > 0) {
            jListUsers.setModel(new javax.swing.AbstractListModel() {
                String[] strings = users;

                public int getSize() {
                    return strings.length;
                }

                public Object getElementAt(int i) {
                    return strings[i];
                }
            });
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupModeType;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonUpdateName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JList jListUsers;
    private javax.swing.JRadioButton jRadioButtonClient;
    private javax.swing.JRadioButton jRadioButtonServer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextAreaChat;
    private javax.swing.JTextField jTextFieldHostname;
    private javax.swing.JTextField jTextFieldMessage;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JToggleButton jToggleButtonOnline;
    // End of variables declaration//GEN-END:variables
}
