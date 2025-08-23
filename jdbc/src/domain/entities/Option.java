package domain.entities;

import ui.AppRoute;

public record Option(String title, String description, AppRoute routeName) {
}
