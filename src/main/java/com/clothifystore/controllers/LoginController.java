
package com.clothifystore.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.clothifystore.models.User;
import com.clothifystore.services.UserService;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private UserService userService = new UserService();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = userService.authenticate(email, password);
        if (user != null) {
            showAlert("Login successful for user: " + user.getEmail());
            // Load the dashboard based on the user role
        } else {
            showAlert("Invalid credentials");
        }
    }

    @FXML
    private void handleForgotPassword() {
        // Logic to send OTP and reset password
        showAlert("Forgot password flow triggered.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
