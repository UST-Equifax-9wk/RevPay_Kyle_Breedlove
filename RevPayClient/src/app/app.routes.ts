import { Routes } from '@angular/router';
import { ShowTransactionsComponent } from './show-transactions/show-transactions.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { RecoverAccountComponent } from './recover-account/recover-account.component';
import { CreateTransactionComponent } from './create-transaction/create-transaction.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoanComponent } from './loan/loan.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AddCardComponent } from './add-card/add-card.component';
import { LogoutComponent } from './logout/logout.component';
import { RequestPaymentComponent } from './request-payment/request-payment.component';

export const routes: Routes = [
    {path: 'show-transactions', component: ShowTransactionsComponent},
    {path: 'user-login', component: UserLoginComponent},
    {path: 'register-user', component: RegisterUserComponent},
    {path: 'navbar', component: NavbarComponent},
    {path: 'create-transaction', component: CreateTransactionComponent},
    {path: 'recover-account', component: RecoverAccountComponent},
    {path: 'landing-page', component: LandingPageComponent},
    {path: 'loan', component: LoanComponent},
    {path: 'admin', component: AdminPageComponent},
    {path: 'add-card', component: AddCardComponent},
    {path: 'logout', component: LogoutComponent},
    {path: 'request-payment', component: RequestPaymentComponent}

];
