package deu.ac.kr.csw.chatting.user.model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * id, name, avatar, online is IUser interface
 * id = email
 */
public class User implements IUser {

    private String id;
    private String name;
    private String avatar;
    private boolean online;
    private String fcm;
    private String statusMessage;

    public String getFcm() {
        return fcm;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public User(String id, String name, String avatar, boolean online, String fcm, String statusMessage) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
        this.fcm = fcm;
        this.statusMessage = statusMessage;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return online;
    }
}
