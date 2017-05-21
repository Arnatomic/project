/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.gestio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;

/**
 *
 * @author Mr. Robot
 */
@Entity
public class UserOnline implements Serializable{
    @Id
    @Column(name = "connection_code")
    private long connectionCode;
    @Column(name = "user_id", nullable = true)
    private int userId;
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "connection_time")
    private Date connectionTime;
    
    @ElementCollection(fetch= FetchType.LAZY)    
    @CollectionTable(name="user_ids",
            joinColumns = @JoinColumn(name="connectionCode"))
    @Column(name="connection_code", nullable=false,unique = true)    
    @OrderColumn(name="order")
    private static List<Long> usersOnline = new ArrayList<>();
    
    protected UserOnline(){}
    
    public UserOnline(int userIdm, String userName){
    
        setUserId(userId);
        setUserName(userName);
        setConnectionOptions();
        
        
    }

    public long getConnectionCode() {
        return connectionCode;
    }

    private void setConnectionOptions() {
        long connectionCode = 0;
        while(connectionCode <= 0 || usersOnline.contains(connectionCode)){
            connectionCode= new Random().nextLong();
        }
        this.connectionCode = connectionCode;
        this.connectionTime = new Date();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
