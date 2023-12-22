import { Component } from '@angular/core';
import { CurrentUserService } from '../current-user.service';
@Component({
  selector: 'app-show-transactions',
  standalone: true,
  imports: [],
  templateUrl: './show-transactions.component.html',
  styleUrl: './show-transactions.component.css'
})
export class ShowTransactionsComponent {
  constructor(currentUser:CurrentUserService){
    this.currentUser=currentUser;
    this.username = this.currentUser.getUsername();
    console.log("show transactions constructor");
  }
  currentUser: CurrentUserService;
  username:string;
  // ngDoCheck(){
  //   this.username=this.currentUser.getUsername();
  //   console.log("ngdoCheck show transactions");
  // }
}
