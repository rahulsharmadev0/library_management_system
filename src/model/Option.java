package model;

import view.Command;
import view.menu.AppRoute;

public record Option(String title, String description, AppRoute command) {
}
