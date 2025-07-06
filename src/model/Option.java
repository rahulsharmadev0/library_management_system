package model;

import routes.AppRoute;

public record Option(String title, String description, AppRoute routeName) {
}
