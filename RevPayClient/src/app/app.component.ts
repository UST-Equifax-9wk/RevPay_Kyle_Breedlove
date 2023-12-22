import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { RegisterUserComponent } from './register-user/register-user.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserLoginComponent } from './user-login/user-login.component';
//import { ShowTransactionsComponent } from './show-transactions/show-transactions.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RegisterUserComponent, NavbarComponent, UserLoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RevPayClient';
}
