/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javachat.network.message;

import java.io.Serializable;

/**
 *
 * @author matheus
 */
public class NamePacket implements Serializable{
    private String oldName;
    private String newName;
    private String[] users;

    public NamePacket() {}

    public NamePacket(String oldName, String newName, String[] users) {
        this.oldName = oldName;
        this.newName = newName;
        this.users = users;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
