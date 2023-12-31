package deu.ac.kr.csw.chatting.chat.model;


import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

import deu.ac.kr.csw.chatting.user.model.User;


public class Message implements IMessage,
        MessageContentType.Image, /*this is for default image messages implementation*/
        MessageContentType /*and this one is for custom content type (in this case - voice message)*/ {

    private String id;
    private String text;
    private Date createdAt;
    private Boolean unread;
    private Boolean systemGenerated = false;
    private User user;
    private Image image;
    private Voice voice;

    public Message(String id, User user, String text) {
        this(id, user, text, new Date());
    }

    public Message(String id, User user, String text, Date createdAt) {
        this(id, user, text, createdAt, false, false);
    }

    public Message(String id, User user, String text, Date createdAt,  Boolean systemGenerated) {
        this(id, user, text, createdAt, false, systemGenerated);
    }

    public Message(String id, User user, String text, Date createdAt, Boolean unread, Boolean systemGenerated) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.createdAt = createdAt;
        this.unread = unread;
        this.systemGenerated = systemGenerated;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public String getImageUrl() {
        return image == null ? null : image.url;
    }

    @Override
    public Boolean isUnread() {
        return unread;
    }

    public Voice getVoice() {
        return voice;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Boolean getUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public void setSystemGenerated(Boolean systemGenerated) {
        this.systemGenerated = systemGenerated;
    }

    public static class Image {

        private String url;

        public Image(String url) {
            this.url = url;
        }
    }

    public static class Voice {

        private String url;
        private int duration;

        public Voice(String url, int duration) {
            this.url = url;
            this.duration = duration;
        }

        public String getUrl() {
            return url;
        }

        public int getDuration() {
            return duration;
        }
    }

    @Override
    public Boolean isSystemMessage() {
        return systemGenerated;
    }
}
