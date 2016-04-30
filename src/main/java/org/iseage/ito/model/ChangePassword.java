package org.iseage.ito.model;

public class ChangePassword {

    private String currentPassword;
    private String newPassword;

    public ChangePassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
    
    public String getCurrentPassword() {
		return this.currentPassword;
	}
	
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getNewPassword() {
		return this.newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

    public ChangePassword() {}

}
