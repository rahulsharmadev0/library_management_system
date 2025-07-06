package model;

import routes.AppRouteName;

public record Option(String title, String description, AppRouteName routeName) {
}
