import { Component } from '@angular/core';
import { ShowTransactionsComponent } from '../show-transactions/show-transactions.component';
import { RouterModule, RouterOutlet } from '@angular/router';
import { CurrentUserService } from '../current-user.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterOutlet, RouterModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  currentUser: CurrentUserService;
  loggedIn:boolean;
  admin:boolean;
  constructor(current:CurrentUserService){
    this.currentUser=current;
    this.loggedIn=(this.currentUser.username!="none");
    this.admin=this.currentUser.admin;
  }

  ngDoCheck(){
    this.loggedIn=(this.currentUser.username!="none");
    this.admin=this.currentUser.admin
  }
}
