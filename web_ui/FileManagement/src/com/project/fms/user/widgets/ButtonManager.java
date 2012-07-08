package com.project.fms.user.widgets;

import com.project.fms.user.data.MessagesLibrary;
import com.smartgwt.client.widgets.IButton;

public class ButtonManager extends IButton {

	// public static String IMAGE_UNLOCK = "unlock.gif";
	// public static String IMAGE_LOCKED = "locked.gif";
	// public static String IMAGE_APPROVED = "approved.png";
	// public static String IMAGE_REDO = "redo.png";
	// public static String IMAGE_COMPLETED = "completed.png";

	public ButtonManager() {
		super();
		super.setWidth(65);
		super.setHeight(18);
	}

	public void setUploadButtonIcon(String buttonStatus, String roleType) {
		try {
			setWidth(65);
			setHeight(18);
			setTitle("Upload");
			Integer status = Integer.parseInt(buttonStatus);
			switch (status) {
			case 0:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)) {
					setIcon(MessagesLibrary.IMAGE_UNLOCK);
					setDisabled(false);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 1:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setIcon(MessagesLibrary.IMAGE_UNLOCK);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)) {
					setIcon(MessagesLibrary.IMAGE_APPROVED);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 2:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 3:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setIcon(MessagesLibrary.IMAGE_UNLOCK);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)
						|| roleType
								.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setIcon(MessagesLibrary.IMAGE_APPROVED);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 4:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)) {
					setIcon(MessagesLibrary.IMAGE_APPROVED);
					setDisabled(true);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 5:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_QA)) {
					setIcon(MessagesLibrary.IMAGE_UNLOCK);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)
						|| roleType
								.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)
						|| roleType
								.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setIcon(MessagesLibrary.IMAGE_APPROVED);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 6:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(false);
				} else if (roleType
						.equalsIgnoreCase(MessagesLibrary.ROLE_TRANSCRIBER)
						|| roleType
								.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setIcon(MessagesLibrary.IMAGE_APPROVED);
					setDisabled(true);
				} else if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_QA)) {
					setIcon(MessagesLibrary.IMAGE_REDO);
					setDisabled(true);
				} else {
					setIcon(MessagesLibrary.IMAGE_LOCKED);
					setDisabled(true);
				}
				break;
			case 7:
				setIcon(MessagesLibrary.IMAGE_COMPLETED);
				setDisabled(true);
				break;
			}

		} catch (Exception exE) {
			// redirect to home page.
		}
	}

	public void setSendBackButtonIcon(String buttonStatus, String roleType) {
		try {
			Integer status = Integer.parseInt(buttonStatus);
			setIcon(MessagesLibrary.IMAGE_SENDBACK);
			setHeight(18);
			setWidth(85);
			setTitle("Send Back");
			switch (status) {
			case 0:
				setDisabled(true);
				break;
			case 1:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setDisabled(false);
				} else {
					setDisabled(true);
				}
				break;
			case 2:
				setDisabled(true);
				break;
			case 3:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setDisabled(false);
				} else {
					setDisabled(true);
				}
				break;
			case 4:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_EDITOR)) {
					setDisabled(false);
				} else {
					setDisabled(true);
				}
				break;
			case 5:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_QA)) {
					setDisabled(false);
				} else {
					setDisabled(true);
				}
				break;
			case 6:
				if (roleType.equalsIgnoreCase(MessagesLibrary.ROLE_PROOFER)) {
					setDisabled(false);
				} else {
					setDisabled(true);
				}
				break;
			case 7:
				setIcon(MessagesLibrary.IMAGE_COMPLETED);
				setDisabled(true);
				break;
			}

		} catch (Exception exE) {
			// redirect to home page.
		}
	}
}
