import { Routes } from '@angular/router';
import { ShowTransactionsComponent } from './show-transactions/show-transactions.component';
import { NavbarComponent } from './navbar/navbar.component';

export const routes: Routes = [
    {path: 'show-transactions', component: ShowTransactionsComponent},
    {path: 'navbar', component: NavbarComponent}
];
