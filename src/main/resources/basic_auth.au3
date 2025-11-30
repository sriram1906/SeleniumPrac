; Wait for the authentication dialog to appear
WinWaitActive("Authentication Required")

; Type username: admin
Send("admin")

; Press Tab to move to password field
Send("{TAB}")

; Type password: admin
Send("admin")

; Press Enter to submit
Send("{ENTER}")