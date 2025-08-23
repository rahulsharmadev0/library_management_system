package com.library_management_system.app.ui;

import java.util.function.Supplier;

import com.library_management_system.app.ui.book_inventory_management.*;
import com.library_management_system.app.ui.book_issuing_returning.*;
import com.library_management_system.app.ui.member_management.*;
import com.library_management_system.app.ui.utils.FeatureUnavailableCommand;
import com.library_management_system.app.ui.adapters.commands.*;

public enum AppRoute {
    BookIssuingMenu(BookIssuingMenu::new),
    MainMenu(com.library_management_system.app.ui.MainMenu::new),
    BookInventoryMenu(BookInventoryMenu::new),
    MemberManagementMenu(MemberManagementMenu::new),
    SearchBookMenu(SearchBookMenu::new),
    SearchMemberMenu(SearchMemberMenu::new),
    SortBookMenu(SortBookMenu::new),
    PlaceHolderCommand(FeatureUnavailableCommand::new),
    AddBookCommand(AddBookCommand::new),
    ListAllBooksCommand(ListAllBooksCommand::new),
    DeleteBookCommand(DeleteBookCommand::new),
    UpdateBookCommand(UpdateBookCommand::new),
    RegisterNewMemberCommand(RegisterNewMemberCommand::new),
    ListAllMembersCommand(ListAllMembersCommand::new),
    UpdateMemberCommand(UpdateMemberCommand::new),
    DeleteMemberCommand(DeleteMemberCommand::new),
    SearchMemberCommandByEmail(SearchMemberCommand.ByEmail::new),
    SearchMemberCommandById(SearchMemberCommand.ById::new),
    SearchMemberCommandByName(SearchMemberCommand.ByName::new),
    SearchMemberCommandByPhone(SearchMemberCommand.ByPhone::new),
    SearchBookCommandByTitle(SearchBookCommand.ByTitle::new),
    SearchBookCommandByAuthor(SearchBookCommand.ByAuthor::new),
    SearchBookCommandByISBN(SearchBookCommand.ByISBN::new),
    SortBookCommandByTitle(SortBookCommand.ByTitle::new),
    SortBookCommandByAuthor(SortBookCommand.ByAuthor::new),
    SortBookCommandByPages(SortBookCommand.ByPages::new);

    private final Supplier<Command> comSupplier;

    AppRoute(Supplier<Command> comSupplier){
        this.comSupplier = comSupplier;
    }

    public Command get() {
        return this.comSupplier.get();
    }

}


