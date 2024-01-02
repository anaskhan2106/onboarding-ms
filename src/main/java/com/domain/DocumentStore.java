package com.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="document_store")
public class DocumentStore {

    @Id
    @Column(name = "id", length = 45)
    private int id;

    @Column(name = "document_id", length = 45)
    private int documentId;

    @Column(name = "document_name", length = 255)
    private String documentName;

    @Column(name = "document_type", length = 255)
    private String documentType;

    @Column(name = "uploaded_by", length = 255)
    private String uploadedBy;

    @Column(name = "uploaded_ts", length = 15)
    private Calendar uploadedTs;

    @Column(name = "comments", length = 255)
    private String comments;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    private DriverProfile driverProfile;

    public DocumentStore() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Calendar getUploadedTs() {
        return uploadedTs;
    }

    public void setUploadedTs(Calendar uploadedTs) {
        this.uploadedTs = uploadedTs;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DriverProfile getDriverProfile() {
        return this.driverProfile;
    }

    public void setDriverProfile(DriverProfile driverProfile) {
        this.driverProfile = driverProfile;
    }
}
//create getters and setters
