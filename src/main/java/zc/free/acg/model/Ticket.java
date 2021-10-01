package zc.free.acg.model;

import java.util.Date;

public class Ticket {
    private Integer id;

    private Integer userId;

    private Integer exId;

    private Date createAt;

    private String ticketCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExId() {
        return exId;
    }

    public void setExId(Integer exId) {
        this.exId = exId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode == null ? null : ticketCode.trim();
    }
}